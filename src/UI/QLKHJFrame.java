/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import DAO.KhachHangDAO;
import DAO.TaiKhoanDAO;
import Entity.KhachHang;
import Utils.Auth;
import Utils.AuthKH;
import Utils.AuthNV;
import static Utils.AuthNV.usernv;
import Utils.MsgBox;
import Utils.XImage;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author soaic
 */
public class QLKHJFrame extends javax.swing.JFrame {

    int row = -1;
    KhachHangDAO dao = new KhachHangDAO();
    TaiKhoanDAO tkdao = new TaiKhoanDAO();

    public QLKHJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Quản lí Khách Hàng");
        init();
    }

    void init() {
        lblUsername.setText("Username: " + usernv.getMaNV());
        lblTen.setText("Xin chào: " + usernv.getTenNV());
        lblEmail.setText("Email: " + usernv.getEmail());
        System.out.println("Phiên đăng nhập: " + Auth.user.getChucVu());
        fillTable();
        this.updateStatus();
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKH.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.selectAll();
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getHoTen(),
                    kh.getGioiTinh() ? "Nam" : "Nữ", //câu điều kiện dành cho kiểu boolean
                    kh.getNgaySinh(),
                    kh.getSoDT(),
                    kh.getEmail(),
                    kh.getDiaChi()

                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

//    public static boolean isValidEmail(String email) {
//        // Biểu thức chính quy cho địa chỉ email
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//
//        // Tạo đối tượng Pattern từ biểu thức chính quy
//        Pattern pattern = Pattern.compile(emailRegex);
//
//        // Tạo đối tượng Matcher để kiểm tra địa chỉ email
//        Matcher matcher = pattern.matcher(email);
//
//        // Kiểm tra xem địa chỉ email có hợp lệ hay không
//        return matcher.matches();
//    }
    void insert() {
        KhachHang kh = this.getForm();
        String makh = (String) tblKH.getValueAt(this.row, 0);
        String email = txtEmail.getText();
        String SDT = txtSDT.getText();
        String TenKH = txtTenKH.getText();
        String MaKH = txtMaKH.getText();
        if (MaKH.equals(makh)) {
            MsgBox.alert(this, "Mã Khách hàng này đã tồn tại!");
        } else if (SDT.length() != 10) {
            MsgBox.alert(this, "Hiện chỉ hỗ trợ số điện thoại 10 số!");
            txtSDT.requestFocus();
        } else if (SDT.isEmpty() || SDT.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại!");
            txtSDT.requestFocus();
        } else if (email.isEmpty() || email.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập email!");
            txtEmail.requestFocus();
        } else if (TenKH.isEmpty() || TenKH.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại!");
            txtTenKH.requestFocus();
        } else {
            try {
                dao.insert(kh); // thêm mới
                this.fillTable(); // đỗ lại bảng
                this.clearForm(); // xóa trắng form
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                e.printStackTrace();
            }
        }
    }

    void update() {
        KhachHang kh = this.getForm();
        String email = txtEmail.getText();
        String SDT = txtSDT.getText();
        String TenKH = txtTenKH.getText();

        if (SDT.length() != 10) {
            MsgBox.alert(this, "Hiện chỉ hỗ trợ số điện thoại 10 số!");
            txtSDT.requestFocus();
        } else if (SDT.isEmpty() || SDT.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại!");
            txtSDT.requestFocus();
        } else if (email.isEmpty() || email.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập email!");
            txtEmail.requestFocus();
        } else if (TenKH.isEmpty() || TenKH.equals("")) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại!");
            txtTenKH.requestFocus();
        } else {
            try {
                dao.update(kh); // cập nhật
                this.fillTable(); // đổ lại bảng
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    void delete() {
        String makh = (String) tblKH.getValueAt(this.row, 0);
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa Khách Hàng này?")) {
            try {
                dao.delete(makh);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clearForm() {
        KhachHang kh = new KhachHang();
        this.setForm(kh);
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
        String makh = (String) tblKH.getValueAt(this.row, 0);
        KhachHang kh = dao.selectById(makh);
        this.setForm(kh);
        this.updateStatus();
    }

    void setForm(KhachHang kh) {
        txtMaKH.setText(kh.getMaKH());
        txtTenKH.setText(kh.getHoTen());
        rdoNam.setSelected(kh.getGioiTinh());
        rdoNu.setSelected(!kh.getGioiTinh());
        txtSDT.setText(kh.getSoDT());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        txtNgaySinh.setDate(kh.getNgaySinh());
    }

    KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setHoTen(txtTenKH.getText());
        kh.setEmail(txtEmail.getText());
        kh.setNgaySinh(txtNgaySinh.getDate());
        kh.setGioiTinh(rdoNam.isSelected());
        kh.setSoDT(txtSDT.getText());
        kh.setDiaChi(txtDiaChi.getText());
        return kh;
    }

    void selectCurrentRow() {
        tblKH.getSelectionModel().setSelectionInterval(row, row);
    }

    void first() {
        this.row = 0;
        this.edit();
        selectCurrentRow();
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
            selectCurrentRow();
        }
    }

    void next() {
        if (this.row < tblKH.getRowCount() - 1) {
            this.row++;
            this.edit();
            selectCurrentRow();
        }
    }

    void last() {
        this.row = tblKH.getRowCount() - 1;
        this.edit();
        selectCurrentRow();
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblKH.getRowCount() - 1);
        // Trạng thái form
        txtMaKH.setEditable(!edit);
//        btnThem.setEnabled(!edit);
        btnCapNhat.setEnabled(edit);
        btnXoa.setEnabled(edit);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    private int getRowByValue(JTable table, Object value, int column) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, column).equals(value)) {
                return i;
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy giá trị trong bảng
    }

    private void performSearch() {
        String maSVToFind = txtTim.getText();
        if (maSVToFind.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm");
        } else {
            int columnIndex = 0; // Đặt columnIndex là 0 nếu muốn tìm kiếm trong cột Mã SV
            int rowIndex = getRowByValue(tblKH, maSVToFind, columnIndex);
            if (rowIndex != -1) {
                // Di chuyển đến vị trí tìm thấy trong bảng
                tblKH.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
                tblKH.scrollRectToVisible(tblKH.getCellRect(rowIndex, 0, true));
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTen = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniBack = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniThoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniInfo = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem8.setText("jMenuItem8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "Ngày Sinh", "Số điện thoại", "Email", "Địa Chỉ"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Khách Hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giới Tính;");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Sinh:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số Điện Thoại:");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtMaKH.setEditable(false);
        txtMaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên Khách Hàng:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ:");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm khách hàng:");

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKeyPressed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconVer2/arrow-back.png"))); // NOI18N
        btnBack.setText("Quay về trang Quản Lí");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Exit.png"))); // NOI18N
        btnExit.setText("Thoát ứng dụng");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTen.setText("Xin chào: null");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username: null");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email: null");

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnClear.setText("Nhập mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtNgaySinh.setDateFormatString("dd/MM/yyyy");
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail)
                                            .addComponent(txtSDT)
                                            .addComponent(txtTenKH)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(rdoNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoNu))
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(lblTen)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(86, 86, 86)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Các nút điều khiển khác"));

        btnFirst.setText("<< ");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLast)
                .addContainerGap(772, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenu1.setText("File");

        mniBack.setText("Quay về trang quản lí");
        mniBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBackActionPerformed(evt);
            }
        });
        jMenu1.add(mniBack);
        jMenu1.add(jSeparator3);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(mniDangXuat);
        jMenu1.add(jSeparator4);

        mniThoat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mniThoat.setText("Thoát ứng dụng");
        mniThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThoatActionPerformed(evt);
            }
        });
        jMenu1.add(mniThoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hỗ trợ");
        jMenu2.add(jSeparator1);

        mniInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        mniInfo.setText("Thông tin ứng dụng");
        mniInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniInfoActionPerformed(evt);
            }
        });
        jMenu2.add(mniInfo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        this.update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = tblKH.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        QuanLiJFrame ql = new QuanLiJFrame();
        ql.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (MsgBox.confirm(this, "Thoát ứng dụng sẽ đóng tất cả và sẽ đăng xuất")) {
            System.exit(0);
            AuthKH.clear();
            AuthNV.clear();
            Auth.clear();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void mniBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBackActionPerformed
        QuanLiJFrame ql = new QuanLiJFrame();
        ql.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniBackActionPerformed

    private void mniThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThoatActionPerformed
        if (MsgBox.confirm(this, "Thoát ứng dụng sẽ đóng tất cả và sẽ đăng xuất")) {
            System.exit(0);
            AuthKH.clear();
            AuthNV.clear();
            Auth.clear();
        }
    }//GEN-LAST:event_mniThoatActionPerformed

    private void mniInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniInfoActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        if (MsgBox.confirm(this, "Bạn có muốn đăng xuất chứ ?")) {
            AuthKH.clear();
            AuthNV.clear();
            Auth.clear();
            DangNhapJFrame dn = new DangNhapJFrame();
            dn.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        performSearch();
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            performSearch();
        }
    }//GEN-LAST:event_txtTimKeyPressed

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
            java.util.logging.Logger.getLogger(QLKHJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKHJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKHJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKHJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKHJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenuItem mniBack;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniInfo;
    private javax.swing.JMenuItem mniThoat;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
