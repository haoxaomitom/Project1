package Utils;

import Entity.KhachHang;


public class AuthKH {

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static KhachHang userkh = null;


    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        AuthKH.userkh = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return AuthKH.userkh != null;
    }
     /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
//    public static boolean isManager() {
//        return Auth.isLogin() && user.getVaiTro();
//    }
}
