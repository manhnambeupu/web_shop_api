package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietDTO {
    private Long id;
    private Double donGia;
    private Long soLuong;
    private Double thanhTien;

    private SanPhamChiTietDTO sanPhamChiTiet;

    private HoaDonDTO hoaDon;
}
