package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTietTaiQuayDTO {
    private Long gioHangId;
    private Long sanPhamChiTietId;
    private Long soLuong;

}
