package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters, Setters, Equals, HashCode, ToString khong can phai tu viet cac methode nay nua
@NoArgsConstructor // Tao ra 1 constructor khong co tham so
@AllArgsConstructor // Tao ra 1 constructor co tham so voi tat ca cac truong
public class SanPhamDTO {
    private String ten;
    private Double gia;
    private String moTa;
}
