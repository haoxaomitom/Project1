package DAO;

import Entity.KhachHang;
import Utils.XJdbc;
import Entity.TaiKhoan;
import static Utils.Auth.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO extends TrangChuKHDAO<TaiKhoan, String> {

    public void insert(TaiKhoan model) {
        String sql = "INSERT INTO TAIKHOAN (TenTK, MaNV, MaKH, matkhau, ChucVu) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getTenTK(),
                model.getMaNV(),
                model.getMaKH(),
                model.getMatKhau(),
                model.getChucVu());
    }

    public void update(TaiKhoan model) {
        String sql = "UPDATE TAIKHOAN SET MaNV=?, MaKH=?, matkhau=?, Chucvu=? WHERE TenTK=?";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getMaKH(),
                model.getMatKhau(),
                model.getChucVu(),
                model.getTenTK());
    }

    public void delete(String TenTK) {
        String sql = "DELETE FROM TAIKHOAN WHERE TenTK=?";
        XJdbc.update(sql,TenTK);
    }

    public List<TaiKhoan> selectAll() {
        String sql = "SELECT * FROM TaiKhoan";
        return this.selectBySql(sql);
    }
    
//    public TaiKhoan Email(String email){
//        String sql = "SELECT TaiKhoan.TenTK, KhachHang.Email AS KhachHangEmail, NhanVien.Email AS NhanVienEmail FROM TaiKhoan LEFT JOIN KhachHang ON TaiKhoan.MaKH = KhachHang.MaKH LEFT JOIN NhanVien ON TaiKhoan.MaNV = NhanVien.MaNV WHERE KhachHang.TenTK = ? OR NhanVien.TenTK = ?";
//        List<TaiKhoan> list = this.selectBySql(sql, email);
//        return list.size() > 0 ? list.get(0) : null;
//    }
    

    public TaiKhoan selectById(String username) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenTK=?";
        List<TaiKhoan> list = this.selectBySql(sql,username);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<TaiKhoan> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE TenTK LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    TaiKhoan entity = new TaiKhoan();
                    entity.setTenTK(rs.getString("TenTK"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setChucVu(rs.getString("Chucvu"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

}
