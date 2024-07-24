package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO {
    private Long id;
    private Long tongSanPham;
    private Long tongSoTien; // tong so tien cua tat ca cac gio hang chi tiet trong gio hang

    private TaiKhoanDTO taiKhoan;

    private List<GioHangChiTietDTO> gioHangChiTiets;
}
