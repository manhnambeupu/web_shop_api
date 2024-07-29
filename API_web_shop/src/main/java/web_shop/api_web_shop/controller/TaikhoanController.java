package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.dto.TaiKhoanDTO;
import web_shop.api_web_shop.service.TaiKhoanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/taikhoan")
public class TaikhoanController {

    private final TaiKhoanService taiKhoanService;

    @PostMapping
    public ResponseDTO<TaiKhoanDTO> create(@RequestBody TaiKhoanDTO dto) {
        taiKhoanService.create(dto);
        return ResponseDTO.<TaiKhoanDTO>builder()
                .status(200)
                .message("Success create tai khoan")
                .build();
    }
}
