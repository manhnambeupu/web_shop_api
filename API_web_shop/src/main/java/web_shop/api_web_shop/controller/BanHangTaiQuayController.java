package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.HoaDonChiTietTaiQuayDTO;
import web_shop.api_web_shop.service.BanHangTaiQuayService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/Kasse")
public class BanHangTaiQuayController {
    private final BanHangTaiQuayService kasseService;

    @PostMapping("/genBill")
    public ResponseEntity<String> genBill(){
        return kasseService.genBill();
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody HoaDonChiTietTaiQuayDTO dto){
        return kasseService.addToCart(dto);
    }

    @GetMapping("/addToCartQR")
    public ResponseEntity<String> addToCartQR(@RequestParam String numProductk, @RequestParam Long idBill){
        return kasseService.addToCartQR(numProductk, idBill);
    }
}
