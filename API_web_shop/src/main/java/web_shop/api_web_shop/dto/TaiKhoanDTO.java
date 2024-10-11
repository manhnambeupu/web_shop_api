package web_shop.api_web_shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web_shop.api_web_shop.Valid_Ultil.EnumValue;
import web_shop.api_web_shop.entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanDTO {

    private Long id;
    private String ma;

    @Email(message = "Email invalid")
    private String email;

    private String matKhau;
    private String hoVaTen;

    @NotNull(message = "Role must be not null")
    @EnumValue(name = "role", enumClass = Role.class)
    private String role;

    private Long tongHoaDon;
    private Double tongTien;
    private Integer hangTaiKhoan;
    private Integer trangThai;
}