package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTietDTO {
    private Long id;
    private Long soLuong;
    private Double thanhTien;

    private SanPhamChiTietDTO sanPhamChiTiet;

    private Long gioHangId;
    //private GioHangDTO gioHang;
}
