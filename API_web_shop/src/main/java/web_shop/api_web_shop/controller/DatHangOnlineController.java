package web_shop.api_web_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.HoaDonDTO;
import web_shop.api_web_shop.dto.ThongTinHoaDonDTO;
import web_shop.api_web_shop.service.DatHangOnlineService;

@RestController
@RequestMapping("/api/dathang")
@RequiredArgsConstructor
public class DatHangOnlineController {

    private final DatHangOnlineService datHangOnlineService;

    @GetMapping("/{id}")
    public ResponseEntity<ThongTinHoaDonDTO> getById(@PathVariable Long id){
        return datHangOnlineService.getById(id);
    }

    @PostMapping("/creat")
    public ResponseEntity<String> create(@Valid @RequestBody HoaDonDTO hoaDonDTO) {
        return datHangOnlineService.create(hoaDonDTO);
    }

    @PutMapping("/updateStatus")
    //@RequestParam  api/dathang/updateStatus?id=1&trangThai=1
    //@RequestBody  api/dathang/updateStatus/1/1
    public  ResponseEntity<String> updateStatus(@RequestParam Long id, @RequestParam Integer trangThai) {
        return datHangOnlineService.updateStatus(id, trangThai);
    }


}
