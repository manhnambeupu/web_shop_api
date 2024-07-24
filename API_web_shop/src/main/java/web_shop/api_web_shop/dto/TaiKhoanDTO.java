package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web_shop.api_web_shop.entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanDTO {

    private Long id;
    private String ma;
    private String email;
    private String matKhau;
    private String hoVaTen;
    private Role role;
    private Long tongHoaDon;
    private Long tongTien;
    private Integer hangTaiKhoan;
    private Integer trangThai;
}