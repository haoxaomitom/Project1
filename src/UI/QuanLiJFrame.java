/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.QLSPDialog;
import UI.QLTKDialog;
import Utils.Auth;
import static Utils.AuthNV.usernv;
import Utils.XImage;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author soaic
 */
public class QuanLiJFrame extends javax.swing.JFrame {

    /**
     * Creates new form QuanLiJDialog
     */
    public QuanLiJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        init();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Đăng Nhập");
        
    }

    void init() {
        this.startDongHo();
        lblUsername.setText("Username: " + usernv.getMaNV());
        lblTen.setText("Xin chào: " + usernv.getTenNV());
        lblEmail.setText("Email: " + usernv.getEmail());
        System.out.println("Phiên đăng nhập: "+Auth.user.getChucVu());
    }

    void startDongHo() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss a");
        new Timer(1000, (ActionEvent e) -> {
            lblTime.setText(formater.format(new Date()));
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnDoiMK = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnThoat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        btnQLTK = new javax.swing.JButton();
        btnQLKH = new javax.swing.JButton();
        btnQLNV = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLHD = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email: null");

        lblTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTen.setText("Xin chào: null");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username: null");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X Phone");

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.setPreferredSize(new java.awt.Dimension(150, 50));
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setPreferredSize(new java.awt.Dimension(150, 50));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(115, 115, 115)
                .addComponent(lblTen)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat.setText("Thoát ứng dụng");
        btnThoat.setPreferredSize(new java.awt.Dimension(150, 50));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel4.add(btnThoat, new java.awt.GridBagConstraints());

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));

        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnQLTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQLTK.setText("Quản lí Tài Khoản");
        btnQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTKActionPerformed(evt);
            }
        });

        btnQLKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQLKH.setText("Quản lí Khách Hàng");
        btnQLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLKHActionPerformed(evt);
            }
        });

        btnQLNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQLNV.setText("Quản lí Nhân Viên");
        btnQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNVActionPerformed(evt);
            }
        });

        btnQLSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQLSP.setText("Quản lí Sản Phẩm");
        btnQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSPActionPerformed(evt);
            }
        });

        btnQLHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQLHD.setText("Quản lí Hóa Đơn");
        btnQLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnQLKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(btnQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("00:00:00");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Chào mừng bạn quay trở lại !");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblTime)
                .addGap(22, 22, 22)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        Auth.clear();
        this.setVisible(false);
        DangNhapJFrame dn = new DangNhapJFrame();
        dn.setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        Auth.clear();
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNVActionPerformed
        QLNhanVien nv = new QLNhanVien();
        nv.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btnQLNVActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        TrangChuKH tt = new TrangChuKH();
        tt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        new DoiMatKhauJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnQLKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLKHActionPerformed
        QLKHJFrame QLKH = new QLKHJFrame();
        QLKH.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQLKHActionPerformed

    private void btnQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTKActionPerformed
        QLTKDialog qltk = new QLTKDialog();
        qltk.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQLTKActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        QLSPDialog qlsp = new QLSPDialog();
        qlsp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHDActionPerformed
       HoaDonJFrame hd = new HoaDonJFrame();
       hd.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_btnQLHDActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLiJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLKH;
    private javax.swing.JButton btnQLNV;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnQLTK;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUsername;
    // End of variables declaration//GEN-END:variables
}
