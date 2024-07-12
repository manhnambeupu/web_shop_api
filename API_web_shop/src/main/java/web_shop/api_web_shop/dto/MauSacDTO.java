package web_shop.api_web_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters, Setters, Equals, HashCode, ToString khong can phai tu viet cac methode nay nua
@NoArgsConstructor // Tao ra 1 constructor khong co tham so
@AllArgsConstructor // Tao ra 1 constructor co tham so voi tat ca cac truong
public class MauSacDTO {

    @NotNull
    private Long id;

    @Size(min = 3, message = "Ten mau sac phai co it nhat 3 ky tu")
    @NotNull
    private String ten;
}
