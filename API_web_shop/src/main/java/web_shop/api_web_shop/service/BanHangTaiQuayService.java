package web_shop.api_web_shop.service;

import org.springframework.http.ResponseEntity;
import web_shop.api_web_shop.dto.HoaDonChiTietTaiQuayDTO;
import web_shop.api_web_shop.dto.PayInStoreRequestDTO;

public interface BanHangTaiQuayService {
    ResponseEntity<String> genBill(); // truyen thong tin tu token security neen ko can HoaDon DTO la tham so nua

    ResponseEntity<String> addToCart(HoaDonChiTietTaiQuayDTO dto);

    ResponseEntity<String> addToCartQR(String numProduct, Long idBill);

    ResponseEntity<String> payBill (PayInStoreRequestDTO dto);
}
