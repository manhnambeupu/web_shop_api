package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.GioHangChiTietDTO;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.service.GioHangChiTietService;

import java.util.List;

@RestController
@RequestMapping("/giohangchitiet")
@RequiredArgsConstructor
public class GioHangChiTietController {

    private final GioHangChiTietService gioHangChiTietService;

    @GetMapping
    public ResponseDTO<List<GioHangChiTietDTO>> getAll() {
        return ResponseDTO.<List<GioHangChiTietDTO>>builder()
                .data(gioHangChiTietService.getAll())
                .status(200)
                .message("Success")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<GioHangChiTietDTO> getById(@PathVariable Long id) {
        return ResponseDTO.<GioHangChiTietDTO>builder()
                .data(gioHangChiTietService.getById(id))
                .status(200)
                .message("Success")
                .build();
    }

    @PostMapping
    public ResponseDTO<GioHangChiTietDTO> create(@RequestBody GioHangChiTietDTO dto) {
        gioHangChiTietService.create(dto);
        return ResponseDTO.<GioHangChiTietDTO>builder()
                .status(200)
                .message("Success create gio hang chi tiet")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<GioHangChiTietDTO> update(@RequestBody GioHangChiTietDTO dto, @PathVariable Long id) {
        gioHangChiTietService.update(dto, id);
        return ResponseDTO.<GioHangChiTietDTO>builder()
                .status(200)
                .message("Success update gio hang chi tiet")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<GioHangChiTietDTO> delete(@PathVariable Long id) {
        gioHangChiTietService.delete(id);
        return ResponseDTO.<GioHangChiTietDTO>builder()
                .status(200)
                .message("Success delete gio hang chi tiet")
                .build();
    }
}
