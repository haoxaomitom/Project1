/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import DAO.SanPhamDAO;
import Entity.SanPham;
import Utils.Auth;
import Utils.AuthKH;
import Utils.AuthNV;
import static Utils.AuthNV.usernv;
import Utils.MsgBox;
import Utils.XImage;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLSPDialog extends javax.swing.JFrame {

    /**
     * Creates new form QLSPDialog
     */
    SanPhamDAO dao = new SanPhamDAO();
    int row = -1;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fileHinh;

    public QLSPDialog() {
        initComponents();
        fillTable();
        init();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Quản lí sản phẩm");
    }

    private void init() {
        setLocationRelativeTo(null);
        this.fillTable();
        lblUsername.setText("Username: " + usernv.getMaNV());
        lblTen.setText("Xin chào: " + usernv.getTenNV());
        lblEmail.setText("Email: " + usernv.getEmail());
    }

    SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setMau(txtMau.getText());
        sp.setHangSX(cboHangSX.getSelectedItem().toString());
        sp.setHinh(fileHinh);
        sp.setGiaBan(parseDouble(txtGiaBan.getText()));
        sp.setGiaGoc(parseDouble(txtGiaGoc.getText()));
        sp.setSoLuong(parseInt(txtSoLuong.getText()));
        sp.setNgayNhap(txtNgayNhap.getDate());
        return sp;
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = dao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getMau(),
                    sp.getHangSX(),
                    String.format("%.0f", sp.getGiaGoc()),
                    String.format("%.0f", sp.getGiaBan()),
                    sp.getSoLuong(),
                    sp.getNgayNhap(),
                    sp.getHinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

//    public void fillForm() {
//        row = tblSanPham.getSelectedRow();
//        txtMaSP.setText(tblSanPham.getValueAt(row, 0).toString());
//        txtTenSP.setText(tblSanPham.getValueAt(row, 1).toString());
//        txtMau.setText(tblSanPham.getValueAt(row, 2).toString());
//        cboHangSX.setSelectedItem(tblSanPham.getValueAt(row, 3).toString());
//        txtGiaGoc.setText(tblSanPham.getValueAt(row, 4).toString());
//        txtGiaBan.setText(tblSanPham.getValueAt(row, 5).toString());
//        txtSoLuong.setText(tblSanPham.getValueAt(row, 6).toString());
//        txtNgayNhap.setDate((Date) tblSanPham.getValueAt(row, 7));
//        load_Hinh(tblSanPham.getValueAt(row, 8).toString());
//    }
    void setForm(SanPham sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        txtMau.setText(sp.getMau());
        cboHangSX.setSelectedItem(sp.getHangSX());
        txtGiaGoc.setText(String.format("%.0f", sp.getGiaGoc()));
        txtGiaBan.setText(String.format("%.0f", sp.getGiaBan()));
        txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
        txtNgayNhap.setDate(sp.getNgayNhap());
        load_Hinh(sp.getHinh());
    }

    void edit() {
        String manv = (String) tblSanPham.getValueAt(this.row, 0);
        SanPham sp = dao.selectById(manv);
        this.setForm(sp);
    }

    void update() {
        SanPham sp = this.getForm();
        try {
            dao.update(sp); // cập nhật
            this.fillTable(); // đổ lại bảng
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        }
    }

    void delete() {
        String masp = (String) tblSanPham.getValueAt(this.row, 0);
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa Sản Phẩm có mã: " + txtMaSP.getText() + " này?")) {
            try {
                dao.delete(masp);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clearForm() {
        SanPham sp = new SanPham();
        this.setForm(sp);
    }
    void selectCurrentRow() {
        tblSanPham.getSelectionModel().setSelectionInterval(row, row);
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
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.edit();
            selectCurrentRow();
        }
    }

    void last() {
        this.row = tblSanPham.getRowCount() - 1;
        this.edit();
        selectCurrentRow();
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
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã Sản Phẩm !");
        } else {
            int columnIndex = 0; // Đặt columnIndex là 0 nếu muốn tìm kiếm trong cột Mã SV
            int rowIndex = getRowByValue(tblSanPham, maSVToFind, columnIndex);
            if (rowIndex != -1) {
                // Di chuyển đến vị trí tìm thấy trong bảng
                tblSanPham.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
                tblSanPham.scrollRectToVisible(tblSanPham.getCellRect(rowIndex, 0, true));
            } else {
                JOptionPane.showMessageDialog(this, "Mã Sản Phẩm không tồn tại !");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtMau = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaGoc = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtNgayNhap = new com.toedter.calendar.JDateChooser();
        btnClear = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbHinh = new javax.swing.JLabel();
        btnThemDoi = new javax.swing.JButton();
        btnXoaHinh = new javax.swing.JButton();
        cboHangSX = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniBack = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniThoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 100));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorRemoved(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Màu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Hãng sản xuất:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá bán:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giá gốc:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số lượng:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Ngày nhập:");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtMau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtGiaGoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtNgayNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayNhap.setDateFormatString("dd/MM/yyyy");
        txtNgayNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnClear.setText("Nhập mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconVer3/twocirclingarrows1_120592.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconVer3/trash_bin_icon-icons.com_67981.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Quản lý Sản Phẩm");

        lblTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTen.setText("Xin chào: null");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username: null");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email: null");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hình", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        lbHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHinh.setText("Nhấn để thêm hình");
        lbHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThemDoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemDoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThemDoi.setText("Thêm / Đổi hình");
        btnThemDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDoiActionPerformed(evt);
            }
        });

        btnXoaHinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconVer3/trash_bin_icon-icons.com_67981.png"))); // NOI18N
        btnXoaHinh.setText("Xóa hình");
        btnXoaHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(335, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemDoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(283, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(btnThemDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        cboHangSX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboHangSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apple", "Samsung", "Xiaomi", "Redmi", "Realme", "Oppo", "Nokia", "Asus" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan)
                                    .addComponent(txtMau)
                                    .addComponent(txtTenSP)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(16, 16, 16)
                                .addComponent(txtMaSP))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGiaGoc)
                                            .addComponent(txtSoLuong)
                                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))))
                                .addGap(0, 1, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(lblTen)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaGoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Màu", "Hãng sản xuất", "Giá gốc", "Giá bán", "Số lượng", "Ngày nhập", "Hình"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Các nút điều khiển khác"));

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

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Exit.png"))); // NOI18N
        btnExit.setText("Thoát ứng dụng");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
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

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm tên sản phẩm:");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLast)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim)
                        .addGap(50, 50, 50)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, Short.MAX_VALUE))
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1AncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorRemoved

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblSanPham.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

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

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        this.update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

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

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        performSearch();
    }//GEN-LAST:event_btnTimActionPerformed

    private void lbHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHinhMouseClicked
        Hinh();
    }//GEN-LAST:event_lbHinhMouseClicked

    private void btnThemDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDoiActionPerformed
        Hinh();
    }//GEN-LAST:event_btnThemDoiActionPerformed

    private void btnXoaHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHinhActionPerformed
        lbHinh.setIcon(null);
        lbHinh.setText("Nhấn để thêm hình");
    }//GEN-LAST:event_btnXoaHinhActionPerformed

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
            java.util.logging.Logger.getLogger(QLSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSPDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSPDialog().setVisible(true);
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
    private javax.swing.JButton btnThemDoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaHinh;
    private javax.swing.JComboBox<String> cboHangSX;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel lbHinh;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenuItem mniBack;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniInfo;
    private javax.swing.JMenuItem mniThoat;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaGoc;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMau;
    private com.toedter.calendar.JDateChooser txtNgayNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

    private void insert() {
        SanPham sp = this.getForm();
        try {
            dao.insert(sp);
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            fillTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!\nThêm sản phẩm thất bại!");
            e.printStackTrace();

        }
    }

//    private void edit() {
//        SanPham sp = this.getForm();
//        try {
//            dao.update(sp);
//        } catch (Exception e) {
//        }
//    }
    private void load_Hinh(String hinh) {
        ImageIcon img = new ImageIcon("src\\img\\" + hinh);
        Image im = img.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(230, 260, 0));
        lbHinh.setIcon(icon);
        lbHinh.setText(null);
    }

    private void Hinh() {
        JFileChooser filechooser = new JFileChooser("src\\img\\");
        int result = filechooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            String fullPath = file.getAbsolutePath();
            fileHinh = filechooser.getSelectedFile().getName();
            load_Hinh(fileHinh);
            try {
                Path src = Paths.get(fullPath);
                Path dest = Paths.get("src\\img\\" + fileHinh);
//                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                e.printStackTrace();
            }
        }
    }
}
