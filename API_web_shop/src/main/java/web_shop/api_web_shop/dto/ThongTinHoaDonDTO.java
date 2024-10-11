package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinHoaDonDTO {
    private HoaDonDTO hoaDonDTO;
    private List<HoaDonChiTietDTO>  chiTietHoaDonDTOList;
}
