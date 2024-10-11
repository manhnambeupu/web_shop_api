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

    @GetMapping("/{id}") // lay ban ghi loai san pham theo id
    public ResponseDTO <LoaiSanPhamDTO> getById(@PathVariable Long id){
        return ResponseDTO. <LoaiSanPhamDTO>builder()
                .data(loaiSanPhamService.getById(id))
                .status(200)
                .message("Success")
                .build();
    }

    @PostMapping // tao ra 1 ban ghi loai san pham
    // RequestBody: lay du lieu cua nguoi dung gui
    // @Valid: kiem tra xem du lieu co dung dinh dang hay khong
    public ResponseDTO<Void> create(@RequestBody @Valid LoaiSanPhamDTO loaiSanPhamDTO){
        loaiSanPhamService.create(loaiSanPhamDTO);
        return ResponseDTO. <Void>builder()
                .status(200)
                .message("Success create loai san pham")
                .build();
    }

    @PostMapping("/{id}") // cap nhat ban ghi loai san pham
    public ResponseDTO <Void> update(@RequestBody @Valid LoaiSanPhamDTO loaiSanPhamDTO, @PathVariable Long id){
        loaiSanPhamService.update(loaiSanPhamDTO, id);
        return ResponseDTO. <Void>builder()
                .status(200)
                .message("Success update loai san pham")
                .build();
    }

    @DeleteMapping("/{id}") // xoa ban ghi loai san pham
    public ResponseDTO <Void> delete(@PathVariable Long id){
        loaiSanPhamService.delete(id);
        return ResponseDTO. <Void>builder()
                .status(204)
                .message("Success delete loai san pham")
                .build();
    }


}
