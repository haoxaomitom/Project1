/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import DAO.HoaDonDAO;
import Entity.HoaDon;
import Utils.Auth;
import Utils.AuthKH;
import Utils.AuthNV;
import java.util.Date;
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
public class HoaDonJFrame extends javax.swing.JFrame {

    HoaDonDAO dao = new HoaDonDAO();
    int row = -1;

    public HoaDonJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        init();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Quản lí Hóa Đơn");
    }

    void init() {
        this.fillTable();
        lblUsername.setText("Username: " + usernv.getMaNV());
        lblTen.setText("Xin chào: " + usernv.getTenNV());
        lblEmail.setText("Email: " + usernv.getEmail());
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
        model.setRowCount(0);
        try {
            List<HoaDon> list = dao.selectAll();
            for (HoaDon hd : list) {
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaKH(),
                    hd.getMaSP(),
                    hd.getTenSP(),
                    hd.getNgayThanhToan(),
                    String.format("%.0f", hd.getDonGia()),
                    hd.getSoLuong(),
                    String.format("%.0f", hd.getTongTien())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void clearForm() {
        HoaDon hd = new HoaDon();
        this.setForm(hd);
        this.updateStatus();    
    }

    void insert() {
        HoaDon hd = this.getForm();
        try {
            dao.insert(hd); // thêm mới
            this.fillTable(); // đỗ lại bảng
            this.clearForm(); // xóa trắng form
            MsgBox.alert(this, "Tạo hóa đơn thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Tạo hóa đơn thất bại!");
        }
    }
    void update() {
        HoaDon hd = this.getForm();
        try {
            dao.update(hd); // cập nhật
            this.fillTable(); // đổ lại bảng
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        String MaHD = (String) tblHD.getValueAt(this.row, 0);
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa Hóa Đơn có mã: " + txtMaHD.getText() + "này?")) {
            try {
                dao.delete(MaHD);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void setForm(HoaDon hd) {
        txtMaHD.setText(hd.getMaHD());
        txtMaKH.setText(hd.getMaKH());
        txtMaSP.setText(hd.getMaSP());
        txtTenSP.setText(hd.getTenSP());
        txtDate.setDate(hd.getNgayThanhToan());
        txtDonGia.setText(String.format("%.0f", hd.getDonGia()));
        txtSoLuong.setText(String.valueOf(hd.getSoLuong()));
        txtTongTien.setText(String.format("%.0f", hd.getTongTien()));
    }

    HoaDon getForm() {
        HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHD.getText());
        hd.setMaKH(txtMaKH.getText());
        hd.setMaSP(txtMaSP.getText());
        hd.setTenSP(txtTenSP.getText());
        hd.setNgayThanhToan(txtDate.getDate());
        hd.setDonGia(Double.parseDouble(txtDonGia.getText()));
        hd.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
        return hd;
    }

    void edit() {
        String MaHD = (String) tblHD.getValueAt(this.row, 0);
        HoaDon hd = dao.selectById(MaHD);
        this.setForm(hd);
        this.updateStatus();
    }

    void selectCurrentRow() {
        tblHD.getSelectionModel().setSelectionInterval(row, row);
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
        if (this.row < tblHD.getRowCount() - 1) {
            this.row++;
            this.edit();
            selectCurrentRow();
        }
    }

    void last() {
        this.row = tblHD.getRowCount() - 1;
        this.edit();
        selectCurrentRow();
    }
        void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblHD.getRowCount() - 1);
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
            int rowIndex = getRowByValue(tblHD, maSVToFind, columnIndex);
            if (rowIndex != -1) {
                // Di chuyển đến vị trí tìm thấy trong bảng
                tblHD.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
                tblHD.scrollRectToVisible(tblHD.getCellRect(rowIndex, 0, true));
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtMaSP = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTen = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Quản lý Hóa Đơn");

        tblHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã khách hàng", "Mã sản phẩm", "Tên sản phẩm", "Ngày thanh toán", "Đơn giá", "Số lượng", "Tổng tiền"
            }
        ));
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Mã hóa đơn:");

        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new java.awt.Color(204, 204, 204));
        txtMaHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Mã khách hàng:");

        txtMaKH.setEditable(false);
        txtMaKH.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDate.setDateFormatString("dd-MM-yyyy");
        txtDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMaSP.setEditable(false);
        txtMaSP.setBackground(new java.awt.Color(204, 204, 204));
        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Tổng tiền:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Số lượng:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Đơn giá:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Mã sản phẩm");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Ngày thanh toán:");

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Tên sản phẩm:");

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                            .addComponent(txtMaSP)
                            .addComponent(txtTongTien)
                            .addComponent(txtDonGia)
                            .addComponent(txtSoLuong)
                            .addComponent(txtMaKH)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(23, 23, 23)
                        .addComponent(txtTenSP)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(120, Short.MAX_VALUE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(278, Short.MAX_VALUE)))
        );

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Document.png"))); // NOI18N
        btnClear.setText("Xóa trắng form");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

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

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm Mã hóa đơn:");

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

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnThem.setText("Thêm mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1249, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblTen)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsername)
                        .addGap(18, 18, 18)
                        .addComponent(lblEmail)
                        .addGap(80, 80, 80)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim))
                        .addGap(44, 44, 44)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = tblHD.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblHDMouseClicked
    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        this.update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

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

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            performSearch();
        }
    }//GEN-LAST:event_txtTimKeyPressed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        performSearch();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonJFrame().setVisible(true);
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
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JTable tblHD;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
