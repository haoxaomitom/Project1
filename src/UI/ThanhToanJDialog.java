/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import Entity.HoaDon;
import static Utils.AuthKH.userkh;
import Utils.MsgBox;
import Utils.XImage;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;

/**
 *
 * @author soaic
 */
public class ThanhToanJDialog extends javax.swing.JDialog {

    HoaDonDAO dao = new HoaDonDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    Properties properties = new Properties();
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ThanhToanJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Thanh Toán");
        setLocationRelativeTo(null);
        init();
    }

    void init() {
        txtDiaChi.setText(userkh.getDiaChi());
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            System.out.println("Đọc thành công");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Đọc thất bại");
        }

        // Lấy giá trị của thuộc tính "tenSP"
        String tenSP = properties.getProperty("tenSP");
        String mau = properties.getProperty("Mau");
        lblTenSP.setText(tenSP);
        System.out.println("Tên sản phẩm: " + tenSP);
        lblMau.setText(mau);
        System.out.println("Màu: " + mau);
        lblGia.setText(spdao.getGiaByTenSP(tenSP));
        lblTongSP.setText(spdao.getGiaByTenSP(tenSP));
        lblShip.setText("20.000");
        Total();
        loadHinh(spdao.getIconSPByTenSP(lblTenSP.getText(), lblMau.getText()));
    }

    void Total() {
        // Lấy giá trị từ hai JLabel
        String tongSPStr = lblTongSP.getText().replace(".", "");
        String shipStr = lblShip.getText().replace(".", "");
        double tongSP = Double.parseDouble(tongSPStr);
        double ship = Double.parseDouble(shipStr);
        double tongGia = tongSP + ship;

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        lblTongGia.setText(decimalFormat.format(tongGia));
    }

    public String convertDoubleToString(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(number);
    }

    private void loadHinh(String hinh) {
        ImageIcon img = new ImageIcon("src\\img\\" + hinh);
        Image im = img.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(150, 150, 0));
        lblAnh.setIcon(icon);
        lblAnh.setText(null);
    }

    void insert() {
        HoaDon hd = this.getForm();
        String diaChi = txtDiaChi.getText();
        if (diaChi.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập địa chỉ!");
        } else {
            try {
                dao.insert(hd); // thêm mới
                MsgBox.alert(this, "Đặt hàng thành công, chi tiết hóa đơn sẽ được gửi qua email đã đăng kí. Cảm ơn bạn đã mua sản phẩm tại X Phone!");
                this.dispose();
                sendEmailAfterSuccessfulPayment(hd);
            } catch (Exception e) {
                MsgBox.alert(this, "Thanh toán thất bại, lỗi dữ liệu!");
                e.printStackTrace();
            }
        }
    }

    HoaDon getForm() {
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        HoaDon hd = new HoaDon();
        hd.setMaHD(dao.getMaHD(lblTenSP.getText()));
        hd.setMaKH(userkh.getMaKH());
        hd.setNgayThanhToan(sqlDate);
        hd.setMaSP(spdao.getMaSanPhamByTenSanPham(lblTenSP.getText()));
        hd.setTenSP(lblTenSP.getText());
        hd.setDonGia(Double.parseDouble(lblGia.getText().replace(".", "")));
        hd.setSoLuong(Integer.parseInt(lblSL.getText()));
        hd.setTongTien(Double.parseDouble(lblTongGia.getText().replace(".", "")));
        return hd;
    }

    public class EmailSender {

        private static final String FROM_EMAIL = "haondps34432@fpt.edu.vn"; // Địa chỉ email của bạn
        private static final String PASSWORD = "bhrn vtux suby njvd"; // Mật khẩu của bạn
        private static final String TO_EMAIL = userkh.getEmail(); // Địa chỉ email người nhận

        public static void sendEmail(String subject, String body) {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_EMAIL));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);
                System.out.println("Email sent successfully.");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

    void sendEmailAfterSuccessfulPayment(HoaDon hd) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String subject = "X Phone - Thanh toán thành công - Chi tiết hóa đơn";
        String body = "Cảm ơn bạn đã mua hàng tại X Phone, Bạn đã mua hàng thành công. Dưới đây là chi tiết hóa đơn:\n\n"
                + "Mã hóa đơn: " + hd.getMaHD() + "\n"
                + "Ngày thanh toán: " + hd.getNgayThanhToan() + "\n"
                + "Tên sản phẩm: " + hd.getTenSP() + "\n"
                + "Đơn giá: " + String.format("%.0f", hd.getDonGia()) + "đ" + "\n"
                + "Số lượng: " + hd.getSoLuong() + "\n"
                + "Phí vận chuyển: 20.000đ" + "\n"
                + "Tổng tiền: " + String.format("%.0f", hd.getTongTien()) + "đ";
        EmailSender.sendEmail(subject, body); // Gọi phương thức gửi email từ ví dụ trước đó
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblMau = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboThanhToan = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboPTVC = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTongSP = new javax.swing.JLabel();
        lblShip = new javax.swing.JLabel();
        lblTongGia = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnDatHang = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thanh toán");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Ảnh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        lblSL.setText("1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên sản phẩm:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá tiền:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phân loại:");

        jLabel5.setText("SL:");

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenSP.setText("null");

        lblMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMau.setText("null");

        lblGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGia.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("đ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSL)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMau)
                            .addComponent(lblTenSP)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblGia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMau))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblGia)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSL)
                            .addComponent(jLabel5))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Phương thức thanh toán:");

        cboThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thanh toán khi nhận hàng" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Phương thức vận chuyển:");

        cboPTVC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPTVC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhanh (GHN Express)" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Địa chỉ vận chuyển:");

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết thanh toán"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tổng sản phẩm:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Phí vận chuyển:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tổng số tiền thanh toán:");

        lblTongSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongSP.setText("0");

        lblShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShip.setText("0");

        lblTongGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongGia.setText("0");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("đ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("đ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("đ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblTongSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblShip)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))))
                .addContainerGap(489, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTongSP)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblShip)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTongGia)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13))
        );

        btnDatHang.setBackground(new java.awt.Color(255, 51, 51));
        btnDatHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDatHang.setText("Đặt hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboThanhToan, 0, 522, Short.MAX_VALUE)
                            .addComponent(cboPTVC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboPTVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        this.insert();
    }//GEN-LAST:event_btnDatHangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThanhToanJDialog dialog = new ThanhToanJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cboPTVC;
    private javax.swing.JComboBox<String> cboThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMau;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblShip;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblTongGia;
    private javax.swing.JLabel lblTongSP;
    private javax.swing.JTextField txtDiaChi;
    // End of variables declaration//GEN-END:variables
}
