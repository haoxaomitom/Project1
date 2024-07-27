/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

/**
 *
 * @author Admin
 */
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import Entity.TaiKhoan;
import UI.DangNhapJFrame;
import UI.QuanLiJFrame;
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

public class QLTKDialog extends javax.swing.JFrame {

    /**
     * Creates new form QLTKDialog
     */
    int row = -1;
    TaiKhoanDAO dao = new TaiKhoanDAO();
    NhanVienDAO nvdao = new NhanVienDAO();
    KhachHangDAO khdao = new KhachHangDAO();

    public QLTKDialog() {
        initComponents();
        fillTable();
        init();
        setIconImage(XImage.getAppIcon());
        setTitle("X Phone - Quản lí tài khoản");
    }

    private void init() {
        setLocationRelativeTo(null);
        fillTable();
        String chucvu = usernv.getChucVu();
        lblUsername.setText("Username: " + usernv.getMaNV());
        lblTen.setText("Xin chào: " + usernv.getTenNV());
        lblEmail.setText("Email: " + usernv.getEmail());
    }

    TaiKhoan getForm() {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTK(txtTenTK.getText());
        tk.setMatKhau(new String(txtMatKhau.getPassword()));
        tk.setChucVu(cboChucVu.getSelectedItem().toString());
        return tk;
    }

    NhanVien getMaNV() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtTenTK.getText());
        nv.setChucVu(cboChucVu.getSelectedItem().toString());
        return nv;
    }

    KhachHang getMaKH() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtTenTK.getText());
        return kh;
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblQLTK.getModel();
        model.setRowCount(0);
        try {
            List<TaiKhoan> list = dao.selectAll();
            for (TaiKhoan tk : list) {
                Object[] row = {
                    tk.getTenTK(),
                    maskPassword(tk.getMatKhau()),
                    tk.getChucVu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

//    void fillForm() {
//        row = tblQLTK.getSelectedRow();
//        txtTenTK.setText(tblQLTK.getValueAt(row, 0).toString());
//        txtMatKhau.setText(tblQLTK.getValueAt(row, 1).toString());
//        cboChucVu.setSelectedItem(tblQLTK.getValueAt(row, 2).toString());
//    }
    void setForm(TaiKhoan tk) {
        txtTenTK.setText(tk.getTenTK());
        txtMatKhau.setText(tk.getMatKhau());
        cboChucVu.setSelectedItem(tk.getChucVu());
    }

    private void insert() {
        TaiKhoan tk = this.getForm();
        NhanVien nv = this.getMaNV();
        KhachHang kh = this.getMaKH();
        String chucVu = cboChucVu.getSelectedItem().toString();
        String tenTK = txtTenTK.getText();
        try {
            if (chucVu.equals("Khách hàng")) {

                khdao.insert(kh);
            } else {

                nvdao.insert(nv);
            }
            dao.insert(tk);
            MsgBox.alert(this, "Thêm tài khoản thành công!");
            fillTable();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private String maskPassword(String password) {
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append('*');
        }
        return maskedPassword.toString();
    }

    void update() {
        String chucVu = cboChucVu.getSelectedItem().toString();
        String manv = txtTenTK.getText();
        TaiKhoan tk = this.getForm();
        NhanVien nv = this.getMaNV();
        KhachHang kh = this.getMaKH();
        try {
            if (chucVu.equals("Khách hàng")) {
                nvdao.delete(manv);
                khdao.insert(kh);
            } else {
                nvdao.insert(nv);
                khdao.delete(manv);
            }
            dao.update(tk); // cập nhật
            this.fillTable(); // đổ lại bảng
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        String chucVu = cboChucVu.getSelectedItem().toString();
        TaiKhoan tk = this.getForm();
        NhanVien nv = this.getMaNV();
        KhachHang kh = this.getMaKH();
        String makh = (String) tblQLTK.getValueAt(this.row, 0);
        String manv = txtTenTK.getText();
        if (manv.equals(usernv.getMaNV())) {
            MsgBox.alert(this, "Bạn không thể tự xóa chính bạn !");
        } else {
            if (MsgBox.confirm(this, "Bạn thực sự muốn xóa Nhân Viên có mã: " + txtTenTK.getText() + " này?")) {
                try {
                    if (chucVu.equals("Khách hàng")) {
                        khdao.delete(makh);
                    } else {
                        nvdao.delete(manv);
                    }
                    dao.delete(makh);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
                }
            }
        }
    }

    void clearForm() {
        TaiKhoan tk = new TaiKhoan();
        this.setForm(tk);
    }

    void edit() {
        String tentk = (String) tblQLTK.getValueAt(this.row, 0);
        TaiKhoan tk = dao.selectById(tentk);
        this.setForm(tk);
    }

    void selectCurrentRow() {
        tblQLTK.getSelectionModel().setSelectionInterval(row, row);
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
        if (this.row < tblQLTK.getRowCount() - 1) {
            this.row++;
            this.edit();
            selectCurrentRow();
        }
    }

    void last() {
        this.row = tblQLTK.getRowCount() - 1;
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
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm");
        } else {
            int columnIndex = 0; // Đặt columnIndex là 0 nếu muốn tìm kiếm trong cột Mã SV
            int rowIndex = getRowByValue(tblQLTK, maSVToFind, columnIndex);
            if (rowIndex != -1) {
                // Di chuyển đến vị trí tìm thấy trong bảng
                tblQLTK.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
                tblQLTK.scrollRectToVisible(tblQLTK.getCellRect(rowIndex, 0, true));
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenTK = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        cboChucVu = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLTK = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên tài khoản:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mật khẩu:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Chức vụ:");

        txtTenTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách hàng", "Nhân viên", "Trưởng phòng" }));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnClear.setText("Nhập mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Quản lý Tài Khoản");

        lblTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTen.setText("Xin chào: null");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username: null");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email: null");

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm tên tài khoản:");

        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTim)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTK)
                            .addComponent(txtMatKhau)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 275, Short.MAX_VALUE))))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnCapNhat)
                    .addGap(18, 18, 18)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lblTen)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(189, 189, 189)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(485, 485, 485)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(485, Short.MAX_VALUE)))
        );

        tblQLTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Mật khẩu", "Chức vụ"
            }
        ));
        tblQLTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLTKMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLTK);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblQLTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLTKMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = tblQLTK.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblQLTKMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        this.update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (MsgBox.confirm(this, "Thoát ứng dụng sẽ đóng tất cả và sẽ đăng xuất")) {
            System.exit(0);
            AuthKH.clear();
            AuthNV.clear();
            Auth.clear();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        QuanLiJFrame ql = new QuanLiJFrame();
        ql.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        performSearch();
    }//GEN-LAST:event_btnTimActionPerformed

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

    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            performSearch();
        }
    }//GEN-LAST:event_txtTimKeyPressed

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
            java.util.logging.Logger.getLogger(QLTKDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLTKDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLTKDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLTKDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLTKDialog().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTable tblQLTK;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

}
