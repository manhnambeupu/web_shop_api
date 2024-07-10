package web_shop.api_web_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
