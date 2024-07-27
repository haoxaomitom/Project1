/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.SanPham;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAO extends TrangChuKHDAO<HoaDon, String> {

    public void insert(HoaDon model) {
        String sql = "Insert Into HOADON (MAKH, NGAYTHANHTOAN , MASP, TENSP, DONGIA, SOLUONG, TONGTIEN)values (?, ?, ?, ?, ?, ?, ?) ";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getNgayThanhToan(),
                model.getMaSP(),
                model.getTenSP(),
                model.getDonGia(),
                model.getSoLuong(),
                model.getTongTien());

    }

    public void update(HoaDon model) {
        String sql = "Update HOADON set MAKH=?,NGAYTHANHTOAN=?, MASP=?, TENSP=?, DONGIA=?, SOLUONG=?, TONGTIEN=? where MaHD=?";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getNgayThanhToan(),
                model.getMaSP(),
                model.getTenSP(),
                model.getDonGia(),
                model.getSoLuong(),
                model.getTongTien(),
                model.getMaHD());
    }

    public void delete(String Makh) {
        String sql = "Delete from HOADON where MaHD=?";
        XJdbc.update(sql, Makh);
    }

    public List<HoaDon> selectAll() {
        String sql = "Select * from HOADON";
        return this.selectBySql(sql);
    }

    public HoaDon selectById(String MaHD) {
        String sql = "SELECT * FROM HOADON WHERE MaHD=?";
        List<HoaDon> list = this.selectBySql(sql, MaHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public String getMaHD(String MaHD) {
        String sql = "SELECT * FROM HOADON WHERE TENSP=?";
        List<HoaDon> list = this.selectBySql(sql, MaHD);
        return list.size() > 0 ? list.get(0).getMaHD() : null;
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HoaDon entity = new HoaDon();
                    entity.setMaHD("" + rs.getString("MaHD"));
                    entity.setMaKH("" + rs.getString("MaKH"));
                    entity.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                    entity.setMaSP(rs.getString("MaSP"));
                    entity.setTenSP(rs.getString("TenSP"));
                    entity.setDonGia(rs.getDouble("DonGia"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    entity.setTongTien(rs.getDouble("TongTien"));
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
