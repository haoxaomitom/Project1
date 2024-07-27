package Utils;

import Entity.NhanVien;


public class AuthNV {

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien usernv = null;


    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        AuthNV.usernv = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return AuthNV.usernv != null;
    }
     /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
//    public static boolean isManager() {
//        return Auth.isLogin() && user.getVaiTro();
//    }
}
