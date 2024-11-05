package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInStoreResponseDTO {
    private String numBill;
    private String ngayTao;
    private String ngayThanhToan;
    private String name;
    private String phone;
}
