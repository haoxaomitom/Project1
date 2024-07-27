/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.XJdbc;
import Entity.NhanVien;
import Entity.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends TrangChuKHDAO<NhanVien, String> {

    public void insert(NhanVien model) {
        String sql = "Insert Into NhanVien (MANV, TENNV ,CHUCVU, LUONG, NGAYSINH, GIOITINH, DIACHI, SODT, EMAIL)values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getTenNV(),
                model.getChucVu(),
                model.getLuong(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getDiaChi(),
                model.getSoDT(),
                model.getEmail());
    }

    public void update(NhanVien model) {
        String sql = "Update NhanVien set TENNV=?, CHUCVU=?, LUONG=?, NGAYSINH=?, GIOITINH=?, DIACHI=?, SODT=?, EMAIL=? where MaNV=?";
        XJdbc.update(sql,
                model.getTenNV(),
                model.getChucVu(),
                model.getLuong(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getDiaChi(),
                model.getSoDT(),
                model.getEmail(),
                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "Delete from NhanVien where MaNV=?";
        XJdbc.update(sql, MaNV);
    }

    public List<NhanVien> selectAll() {
        String sql = "Select * from NhanVien";
        return this.selectBySql(sql);
    }

    public NhanVien selectById(String Username) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, Username);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<NhanVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV("" + rs.getString("MaNV"));
                    entity.setTenNV(rs.getString("TenNV"));
                    entity.setChucVu(rs.getString("ChucVu"));
                    entity.setLuong(rs.getDouble("Luong"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setSoDT(rs.getString("SoDT"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setDiaChi(rs.getString("DiaChi"));
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
