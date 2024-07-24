package web_shop.api_web_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.LoaiSanPhamDTO;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.service.LoaiSanPhamService;

import java.util.List;

@RestController
@RequestMapping("/api/loaisanpham")
@RequiredArgsConstructor
public class LoaiSanPhamController {
    private final LoaiSanPhamService loaiSanPhamService;

    // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
    @GetMapping // lay du lieu
    public ResponseDTO< List<LoaiSanPhamDTO> > getAll(){
        return ResponseDTO. < List<LoaiSanPhamDTO> >builder() //  dung builder de tao mot doi tuong ResponseDTO co kieu
                                                            //  la mot List<LoaiSanPhamDTO>
                .data(loaiSanPhamService.getAll())
                .status(200)
                .message("Success")
                .build();

        /*cach2, khong dung builder
        ResponseDTO< List<LoaiSanPhamDTO> > responseDTO = new ResponseDTO<>();
        responseDTO.setData(loaiSanPhamService.getAll());
        responseDTO.setStatus(200);
        responseDTO.setMessage("Success");
        return responseDTO;
         */
    }

    @PostMapping // tao ra 1 ban ghi loai san pham
    // RequestBody: lay du lieu cua nguoi dung gui
    // @Valid: kiem tra xem du lieu co dung dinh dang hay khong
    public void create(@RequestBody @Valid LoaiSanPhamDTO loaiSanPhamDTO){
        loaiSanPhamService.create(loaiSanPhamDTO);
    }

    @GetMapping("/{id}") // lay ban ghi loai san pham theo id
    public ResponseDTO <LoaiSanPhamDTO> getById(@PathVariable Long id){
        return ResponseDTO. <LoaiSanPhamDTO>builder()
                .data(loaiSanPhamService.getById(id))
                .status(200)
                .message("Success")
                .build();
    }

    @PostMapping("/{id}") // cap nhat ban ghi loai san pham
    public void update(@RequestBody @Valid LoaiSanPhamDTO loaiSanPhamDTO, @PathVariable Long id){
        loaiSanPhamService.update(loaiSanPhamDTO, id);
    }

    @PostMapping("/{id}") // xoa ban ghi loai san pham
    public void delete(@PathVariable Long id){
        loaiSanPhamService.delete(id);
    }


}
