/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import DAO.SanPhamDAO;
import Utils.Auth;
import static Utils.Auth.user;
import Utils.AuthKH;
import static Utils.AuthKH.userkh;
import Utils.AuthNV;
import static Utils.AuthNV.usernv;
import Utils.MsgBox;
import Utils.XImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author soaicss
 */
public class CTSP extends javax.swing.JFrame {

    SanPhamDAO dao = new SanPhamDAO();
    Properties properties = new Properties();
    String maSP;

    public CTSP() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Chi Tiết Sản Phẩm " + lblTenSP.getText());
    }

    void config() {
        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            properties.store(fos, "Cấu hình sản phẩm");
            System.out.println("Lưu thành công");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lưu thất bại");
        }
    }

    public String convertDoubleToString(double number) {
        return String.format("%.0f", number);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        largeImg = new javax.swing.JLabel();
        btn256GB = new javax.swing.JButton();
        btn1TB = new javax.swing.JButton();
        btn512GB = new javax.swing.JButton();
        btnTianNatural = new javax.swing.JButton();
        btnTitanBlack = new javax.swing.JButton();
        btnTitanWhite = new javax.swing.JButton();
        btnTitanBlue = new javax.swing.JButton();
        btnMuaNgay = new javax.swing.JButton();
        btnMuaTraGop = new javax.swing.JButton();
        btnTraGopQuaThe = new javax.swing.JButton();
        lblTenSP = new javax.swing.JLabel();
        lblGiaCu = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblPercent = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTTCH = new javax.swing.JButton();
        btnXemThemCHCT = new javax.swing.JButton();
        SmallImg1 = new javax.swing.JLabel();
        SmallImg2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        SmallImg3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SmallImg4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnBackHome = new javax.swing.JButton();
        lblTenDT1 = new javax.swing.JLabel();
        lblMau = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setText("X Phone");

        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/IPhone 15 Pro.jpeg"))); // NOI18N

        btn256GB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn256GB.setText("256GB");
        btn256GB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn256GBActionPerformed(evt);
            }
        });

        btn1TB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn1TB.setText("1TB");
        btn1TB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1TBActionPerformed(evt);
            }
        });

        btn512GB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn512GB.setText("512GB");
        btn512GB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn512GBActionPerformed(evt);
            }
        });

        btnTianNatural.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTianNatural.setText("Titan tự nhiên");
        btnTianNatural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTianNaturalActionPerformed(evt);
            }
        });

        btnTitanBlack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTitanBlack.setText("Đen Titan");
        btnTitanBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTitanBlackActionPerformed(evt);
            }
        });

        btnTitanWhite.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTitanWhite.setText("Trắng Titan");
        btnTitanWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTitanWhiteActionPerformed(evt);
            }
        });

        btnTitanBlue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTitanBlue.setText("Xanh Titan");
        btnTitanBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTitanBlueActionPerformed(evt);
            }
        });

        btnMuaNgay.setBackground(new java.awt.Color(102, 255, 102));
        btnMuaNgay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMuaNgay.setText("Mua ngay");
        btnMuaNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuaNgayActionPerformed(evt);
            }
        });

        btnMuaTraGop.setBackground(new java.awt.Color(255, 51, 51));
        btnMuaTraGop.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMuaTraGop.setText("Mua trả góp");
        btnMuaTraGop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuaTraGopActionPerformed(evt);
            }
        });

        btnTraGopQuaThe.setBackground(new java.awt.Color(255, 51, 51));
        btnTraGopQuaThe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTraGopQuaThe.setText("Trả góp qua thẻ");
        btnTraGopQuaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraGopQuaTheActionPerformed(evt);
            }
        });

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTenSP.setText("IPhone 15 Pro Max 512GB");

        lblGiaCu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblGiaCu.setText("34.990.000đ");

        lblGia.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblGia.setText("34.490.000");

        jLabel6.setText("Giá cũ:");

        lblPercent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPercent.setForeground(new java.awt.Color(255, 51, 51));
        lblPercent.setText("-1%");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Cấu hình điện thoại IPhone 15 Pro Max");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Màn hình:", "OLED, 6.7\", Super Retina XDR"},
                {"Hệ điều hành:", "IOS 17"},
                {"Camera sau:", "Chính 48 MP & Phụ 12 MP, 12 MP"},
                {"Camera trước:", "12 MP"},
                {"Chip:", "Apple A17 Pro 6 nhân"},
                {"Ram:", "8GB"},
                {"Dung lượng lưu trữ:", "128GB / 256GB / 512 GB"},
                {"SIM:", "1 Nano SIM & 1 eSIM, Hỗ trợ 5G"},
                {"Pin, Sạc:", "4422 mAh, 20 W"}
            },
            new String [] {
                "Chức năng", "Chi tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoscrolls(false);
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Thông tin sản phẩm");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iphone-15-pro-1.jpg"))); // NOI18N

        btnTTCH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTTCH.setText("Xem thêm thông tin chi tiết ->");
        btnTTCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTCHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(btnTTCH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnTTCH)
                .addContainerGap())
        );

        btnXemThemCHCT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXemThemCHCT.setText("Xem thêm cấu hình chi tiết ->");
        btnXemThemCHCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemThemCHCTActionPerformed(evt);
            }
        });

        SmallImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iphone-15-pro-max-blue-thumbnew-100x100.jpg"))); // NOI18N
        SmallImg1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SmallImg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SmallImg1MouseClicked(evt);
            }
        });

        SmallImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iphone-15-pro-max-black-thumbnew-100x100.jpg"))); // NOI18N
        SmallImg2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SmallImg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SmallImg2MouseClicked(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Titan Xanh");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Titan đen");

        SmallImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iphone-15-pro-max-white-thumbnew-100x100.jpg"))); // NOI18N
        SmallImg3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SmallImg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SmallImg3MouseClicked(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Titan trắng");

        SmallImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iphone-15-pro-max-gold-thumbnew-100x100100.jpg"))); // NOI18N
        SmallImg4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SmallImg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SmallImg4MouseClicked(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Titan tự nhiên");

        btnBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconVer2/arrow-back.png"))); // NOI18N
        btnBackHome.setText("Quay lại trang chủ");
        btnBackHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackHomeActionPerformed(evt);
            }
        });

        lblTenDT1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTenDT1.setText("Điện thoại");

        lblMau.setText(".");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("đ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBackHome))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SmallImg1)
                            .addComponent(SmallImg2)
                            .addComponent(SmallImg3)
                            .addComponent(SmallImg4)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnMuaNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnTianNatural, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn256GB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTitanBlack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn512GB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn1TB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTitanBlue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTitanWhite)))
                        .addGap(185, 185, 185))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnMuaTraGop, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTraGopQuaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(largeImg))
                        .addGap(149, 149, 149)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblGia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblGiaCu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPercent))
                                    .addComponent(jLabel8)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(168, 168, 168))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(btnXemThemCHCT, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(lblTenSP))
                            .addComponent(lblMau, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(831, 831, 831)
                    .addComponent(lblTenDT1)
                    .addContainerGap(1027, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTenSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGia)
                            .addComponent(lblGiaCu)
                            .addComponent(jLabel6)
                            .addComponent(lblPercent)
                            .addComponent(jLabel2))
                        .addGap(1, 1, 1)
                        .addComponent(lblMau)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(btnXemThemCHCT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBackHome)
                                .addGap(21, 21, 21)
                                .addComponent(SmallImg1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SmallImg2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SmallImg3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SmallImg4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(largeImg)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn256GB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn512GB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn1TB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTitanWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTianNatural, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnTitanBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnTitanBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(52, 52, 52)
                                .addComponent(btnMuaNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnMuaTraGop, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTraGopQuaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(lblTenDT1)
                    .addContainerGap(934, Short.MAX_VALUE)))
        );

        jMenu1.setText("Sản phẩm bán chạy");

        jMenuItem5.setText("IPhone 15 Pro Max");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Samsung S23 Ultra");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("XiaoMi Note 14");
        jMenu1.add(jMenuItem7);

        jMenuItem1.setText("Oppo Find X5");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hãng điện thoại");

        jMenuItem3.setText("IPhone");
        jMenu2.add(jMenuItem3);

        jMenuItem9.setText("SamSung");
        jMenu2.add(jMenuItem9);

        jMenuItem11.setText("Xiaomi");
        jMenu2.add(jMenuItem11);

        jMenuItem15.setText("Redme");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem17.setText("Realme");
        jMenu2.add(jMenuItem17);

        jMenuItem16.setText("Oppo");
        jMenu2.add(jMenuItem16);

        jMenuItem13.setText("Nokia");
        jMenu2.add(jMenuItem13);

        jMenuItem10.setText("Asus");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Hỗ trợ");

        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        jMenu4.add(mniDangXuat);
        jMenu4.add(jSeparator1);

        jMenuItem4.setText("Thông tin ứng dụng");
        jMenu4.add(jMenuItem4);

        jMenuItem18.setText("Hướng dẫn sử dụng");
        jMenu4.add(jMenuItem18);

        jMenuBar1.add(jMenu4);

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

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void btnTianNaturalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTianNaturalActionPerformed
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Iphone 15 pm natural 500x500.jpg")));
        properties.setProperty("Mau", btnTianNatural.getText());
        config();
    }//GEN-LAST:event_btnTianNaturalActionPerformed

    private void btnXemThemCHCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemThemCHCTActionPerformed
        new CHCTJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnXemThemCHCTActionPerformed

    private void SmallImg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SmallImg1MouseClicked
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/15pmxanh-500x500.png")));
    }//GEN-LAST:event_SmallImg1MouseClicked

    private void SmallImg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SmallImg3MouseClicked
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/IPhone 15 Pro.jpeg")));
    }//GEN-LAST:event_SmallImg3MouseClicked

    private void SmallImg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SmallImg2MouseClicked
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Iphone 15 pm den 500x500.jpg")));
    }//GEN-LAST:event_SmallImg2MouseClicked

    private void SmallImg4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SmallImg4MouseClicked
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Iphone 15 pm natural 500x500.jpg")));
    }//GEN-LAST:event_SmallImg4MouseClicked

    private void btnTitanBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTitanBlackActionPerformed
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Iphone 15 pm den 500x500.jpg")));
        properties.setProperty("Mau", btnTitanBlack.getText());
        config();
    }//GEN-LAST:event_btnTitanBlackActionPerformed

    private void btnTitanBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTitanBlueActionPerformed
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/15pmxanh-500x500.png")));
        properties.setProperty("Mau", btnTitanBlue.getText());
        config();
    }//GEN-LAST:event_btnTitanBlueActionPerformed

    private void btnTitanWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTitanWhiteActionPerformed
        largeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/IPhone 15 Pro.jpeg")));
        properties.setProperty("Mau", btnTitanWhite.getText());
        config();
    }//GEN-LAST:event_btnTitanWhiteActionPerformed

    private void btnTTCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTCHActionPerformed
        new TTSPJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnTTCHActionPerformed

    private void btnBackHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackHomeActionPerformed
        TrangChuKH ctkh = new TrangChuKH();
        ctkh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackHomeActionPerformed

    private void btnMuaNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuaNgayActionPerformed
        if (user.getChucVu().equals("Khách hàng")) {
            new ThanhToanJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không được mua sản phẩm");
        }

    }//GEN-LAST:event_btnMuaNgayActionPerformed

    private void btnMuaTraGopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuaTraGopActionPerformed
        MsgBox.alert(this, "Tính năng chưa khả dụng, vui lòng quay lại sau");
    }//GEN-LAST:event_btnMuaTraGopActionPerformed

    private void btnTraGopQuaTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraGopQuaTheActionPerformed
        MsgBox.alert(this, "Tính năng chưa khả dụng, vui lòng quay lại sau");
    }//GEN-LAST:event_btnTraGopQuaTheActionPerformed

    private void btn256GBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn256GBActionPerformed
        maSP = "A3107";
        lblTenSP.setText(dao.getTenSPByMaSP(maSP));
        lblTenSP.setText("IPhone 15 Pro Max 256GB");
        lblGia.setText(dao.getGiaByTenSP(lblTenSP.getText()));
        lblGiaCu.setText(dao.getGiaCuByTenSP(lblTenSP.getText()));
        lblPercent.setText("-15%");
        properties.setProperty("tenSP", lblTenSP.getText());
        config();
    }//GEN-LAST:event_btn256GBActionPerformed

    private void btn512GBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn512GBActionPerformed
        maSP = "A3106";
        lblTenSP.setText(dao.getTenSPByMaSP(maSP));
        lblTenSP.setText("IPhone 15 Pro Max 512GB");
        lblGia.setText(dao.getGiaByTenSP(lblTenSP.getText()));
        lblGiaCu.setText(dao.getGiaCuByTenSP(lblTenSP.getText()));
        lblPercent.setText("-1%");
        properties.setProperty("tenSP", lblTenSP.getText());
        config();
    }//GEN-LAST:event_btn512GBActionPerformed

    private void btn1TBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1TBActionPerformed
        maSP = "A3105";
        lblTenSP.setText(dao.getTenSPByMaSP(maSP));
//        lblTenSP.setText("IPhone 15 Pro Max 1TB");
        lblGia.setText(dao.getGiaByTenSP(lblTenSP.getText()));
        lblGiaCu.setText(dao.getGiaCuByTenSP(lblTenSP.getText()));
        lblPercent.setText("-3%");
        properties.setProperty("tenSP", lblTenSP.getText());
        config();
    }//GEN-LAST:event_btn1TBActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        if (MsgBox.confirm(this, "Bạn có muốn đăng xuất chứ ?")) {
            AuthKH.clear();
            AuthNV.clear();
            Auth.clear();
            DangNhapJFrame dn = new DangNhapJFrame();
            dn.setVisible(true);
            this.setVisible(false);
            properties.setProperty("tenSP", lblTenSP.getText());
            try (FileOutputStream fos = new FileOutputStream("config.properties")) {
                properties.store(fos, "Cấu hình sản phẩm");
                System.out.println("Lưu thành công");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Lưu thất bại");
            }
        }
    }//GEN-LAST:event_mniDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(CTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CTSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SmallImg1;
    private javax.swing.JLabel SmallImg2;
    private javax.swing.JLabel SmallImg3;
    private javax.swing.JLabel SmallImg4;
    private javax.swing.JButton btn1TB;
    private javax.swing.JButton btn256GB;
    private javax.swing.JButton btn512GB;
    private javax.swing.JButton btnBackHome;
    private javax.swing.JButton btnMuaNgay;
    private javax.swing.JButton btnMuaTraGop;
    private javax.swing.JButton btnTTCH;
    private javax.swing.JButton btnTianNatural;
    private javax.swing.JButton btnTitanBlack;
    private javax.swing.JButton btnTitanBlue;
    private javax.swing.JButton btnTitanWhite;
    private javax.swing.JButton btnTraGopQuaThe;
    private javax.swing.JButton btnXemThemCHCT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel largeImg;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblGiaCu;
    private javax.swing.JLabel lblMau;
    private javax.swing.JLabel lblPercent;
    private javax.swing.JLabel lblTenDT1;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JMenuItem mniDangXuat;
    // End of variables declaration//GEN-END:variables
}
