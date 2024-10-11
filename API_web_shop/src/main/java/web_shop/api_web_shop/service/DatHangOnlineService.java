package web_shop.api_web_shop.service;

import org.springframework.http.ResponseEntity;
import web_shop.api_web_shop.dto.HoaDonDTO;
import web_shop.api_web_shop.dto.ThongTinHoaDonDTO;

public interface DatHangOnlineService {
        ResponseEntity<String> create(HoaDonDTO hoaDonDTO);

        ResponseEntity<String> updateStatus (Long id, Integer  trangThai);

        ResponseEntity<ThongTinHoaDonDTO>  getById (Long id);
}
