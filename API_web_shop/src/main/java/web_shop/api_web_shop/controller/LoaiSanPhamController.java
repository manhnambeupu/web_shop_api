package web_shop.api_web_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web_shop.api_web_shop.dto.LoaiSanPhamDTO;
import web_shop.api_web_shop.service.LoaiSanPhamService;

import java.util.List;

@RestController
@RequestMapping("/api/loaisanpham")
@RequiredArgsConstructor
public class LoaiSanPhamController {
    private final LoaiSanPhamService loaiSanPhamService;

    // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
    @GetMapping // lay du lieu
    public List<LoaiSanPhamDTO> getAll(){
        return loaiSanPhamService.getAll();
    }

    @PostMapping // tao ra 1 ban ghi loai san pham
    public void create(LoaiSanPhamDTO loaiSanPhamDTO){
        loaiSanPhamService.create(loaiSanPhamDTO);
    }

    @GetMapping("/id") // lay ban ghi loai san pham theo id
    public LoaiSanPhamDTO getById(Long id){
        return loaiSanPhamService.getById(id);
    }

    @PostMapping("/id") // cap nhat ban ghi loai san pham
    public void update(LoaiSanPhamDTO loaiSanPhamDTO, Long id){
        loaiSanPhamService.update(loaiSanPhamDTO, id);
    }

    @PostMapping("/delete") // xoa ban ghi loai san pham
    public void delete(Long id){
        loaiSanPhamService.delete(id);
    }


}
