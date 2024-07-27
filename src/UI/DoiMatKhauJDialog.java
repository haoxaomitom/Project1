/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.TaiKhoan;
import Utils.Auth;
import static Utils.Auth.user;
import Utils.AuthKH;
import static Utils.AuthKH.userkh;
import Utils.AuthNV;
import static Utils.AuthNV.usernv;
import Utils.MsgBox;
import Utils.XImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author soaic
 */
public class DoiMatKhauJDialog extends javax.swing.JDialog {

    private static String verificationCode;
    TaiKhoanDAO dao = new TaiKhoanDAO();
    KhachHangDAO khdao = new KhachHangDAO();
    NhanVienDAO nvdao = new NhanVienDAO();
    private Timer timer;
    private int countdownTime;

    public DoiMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        init();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Đổi mật khẩu");
    }

    void init() {
        String username = user.getTenTK();
        TaiKhoan tk = dao.selectById(username);
        KhachHang kh = khdao.selectById(username);
        NhanVien nv = nvdao.selectById(username);
        ThongBaoJFrame tb = new ThongBaoJFrame();
        tb.setVisible(true);
        MsgBox.alert(this, "Chúng tôi sẽ gửi mã qua Mail đã đăng kí của bạn, hãy dùng mã đó để đổi mật khẩu");
        if (user.getChucVu().equals("Khách hàng")) {
            if (kh != null) {
                this.sendVerificationCode(userkh.getEmail());
            } else {
                MsgBox.alert(this, "Lỗi lấy thông tin khách hàng");
            }
        } else {
            if (nv != null) {
                this.sendVerificationCode(usernv.getEmail());
            } else {
                MsgBox.alert(this, "Lỗi lấy thông tin nhân viên");
            }
        }
        tb.setVisible(false);
    }

//    void checkRole(){
//        if (user.getChucVu().equals("Khách hàng")) {
//                if (kh != null) {
//                    AuthKH.userkh = kh;
//                    TrangChuKH ctkh = new TrangChuKH();
//                    ctkh.setVisible(true);
//                } else {
//                    MsgBox.alert(this, "Lỗi lấy thông tin khách hàng");
//                }
//            } else {
//                if (nv != null) {
//                    AuthNV.usernv = nv;
//                    QuanLiJDialog ql = new QuanLiJDialog();
//                    ql.setVisible(true);
//                } else {
//                    MsgBox.alert(this, "Lỗi lấy thông tin nhân viên");
//                }
//            }
//    }
    private boolean checkVerificationCode() {
        String userInputCode = txt6Number.getText();
        if (validateVerificationCode(userInputCode)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Mã xác nhận không hợp lệ. Không thể đặt lại mật khẩu.");
            return false;
        }
    }

    // Gửi mã xác nhận qua email
    private static void sendVerificationCode(String recipientEmail) {
        // Tạo và lưu mã xác nhận ngẫu nhiên
        generateVerificationCode();

        // Gửi email chứa mã xác nhận đến người dùng
        String subject = "X - Phone Xác nhận đặt lại mật khẩu";
        String body = "Mã xác nhận của bạn là: " + verificationCode;
        sendEmail(recipientEmail, subject, body);
    }

    // Kiểm tra tính hợp lệ của mã xác nhận
    private static boolean validateVerificationCode(String userInputCode) {
        return userInputCode.equals(verificationCode);
    }

    // Tạo và lưu mã xác nhận ngẫu nhiên
    private static void generateVerificationCode() {
        Random random = new Random();
        // Tạo mã xác nhận 6 chữ số ngẫu nhiên
        verificationCode = String.format("%06d", random.nextInt(1000000));
    }

    // Gửi email
    private static void sendEmail(String to, String subject, String body) {
        final String username = "haondps34432@fpt.edu.vn"; // Điền email của bạn
        final String password = "bhrn vtux suby njvd"; // Điền mật khẩu của bạn

        // Cài đặt các thuộc tính cho việc gửi email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên làm việc cho Session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            // Thiết lập địa chỉ người gửi
            message.setFrom(new InternetAddress(username));
            // Thiết lập địa chỉ người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Thiết lập tiêu đề email
            message.setSubject(subject);
            // Thiết lập nội dung email
            message.setText(body);
            // Gửi email
            Transport.send(message);
//            System.out.println("Email đã được gửi thành công, vui lòng kiểm tra email của bạn");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void doiMatKhau() {
//        String userInputCode = txt6Number.getText();
        String matKhauMoi = new String(txtMK.getPassword());
        String matKhauMoi2 = new String(txtMK2.getPassword());
        if (matKhauMoi.isEmpty() || matKhauMoi.equals("")) {
            MsgBox.alert(this, "Mật khẩu mới không được trống");
        } else if (!matKhauMoi.equals(matKhauMoi2)) {
            MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
        } else {
            String userInputCode = txt6Number.getText();
            if (validateVerificationCode(userInputCode)) {
                Auth.user.setMatKhau(matKhauMoi);
                dao.update(Auth.user);
                MsgBox.alert(this, "Đổi mật khẩu thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Mã xác nhận không hợp lệ. Không thể đặt lại mật khẩu.");
            }

        }
    }

    private void GuiLai() {
        btnGuiLai.setEnabled(false);
        // Thời gian đếm: 60 giây (60,000 milliseconds)
        int delay = 1000; // 1 giây

        // Gán thời gian còn lại bằng thời gian đếm
        countdownTime = 60;

        // Tạo một Timer với sự kiện cập nhật khi kết thúc đếm
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownTime--; // Giảm thời gian còn lại
                updateCountdownLabel(); // Cập nhật label hiển thị thời gian
                if (countdownTime == 0) {
                    btnGuiLai.setEnabled(true); // Hiển thị lại nút khi hết thời gian
                    ((Timer) e.getSource()).stop(); // Dừng timer sau khi kết thúc
                }
            }
        });

        // Bắt đầu timer
        timer.start();
    }

    private void updateCountdownLabel() {
        lblTime.setText("" + countdownTime);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMK = new javax.swing.JPasswordField();
        txtMK2 = new javax.swing.JPasswordField();
        btnDoiMK = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt6Number = new javax.swing.JTextField();
        btnGuiLai = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đổi mật khẩu");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu mới:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Xác nhận mật khẩu mới:");

        txtMK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtMK2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDoiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Log out.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nhập mã xác nhận (6 số):");

        txt6Number.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnGuiLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuiLai.setText("Gửi lại mã");
        btnGuiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiLaiActionPerformed(evt);
            }
        });

        lblTime.setText("00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMK2)
                    .addComponent(txtMK)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt6Number, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiLai)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnDoiMK)
                .addGap(54, 54, 54)
                .addComponent(btnThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt6Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiLai)
                    .addComponent(lblTime))
                .addGap(18, 18, 18)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        this.doiMatKhau();
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnGuiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiLaiActionPerformed
        MsgBox.alert(this, "Đã gửi thành công");
        this.GuiLai();
    }//GEN-LAST:event_btnGuiLaiActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhauJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DoiMatKhauJDialog dialog = new DoiMatKhauJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnGuiLai;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField txt6Number;
    private javax.swing.JPasswordField txtMK;
    private javax.swing.JPasswordField txtMK2;
    // End of variables declaration//GEN-END:variables
}
