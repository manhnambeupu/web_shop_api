package web_shop.api_web_shop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web_shop.api_web_shop.dto.ResponseDTO;
import web_shop.api_web_shop.dto.SanPhamDTO;
import web_shop.api_web_shop.service.SanPhamService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @Tag(name = "Get All", description = "Get All Product ")
    // Rest API get(che giau duong link tang tinh bao mat), post, put, delete
    @GetMapping// lay du lieu
    public ResponseDTO<List<SanPhamDTO>> getAll() {
        return ResponseDTO.<List<SanPhamDTO>>builder()
                .data(sanPhamService.getAll())
                .status(200)
                .message("Success")
                .build();
    /*cach2, khong dung builder
    ResponseDTO< List<MauSacDTO> > responseDTO = new ResponseDTO<>();
    responseDTO.setData(mauSacService.getAll());
    responseDTO.setStatus(200);
    responseDTO.setMessage("Success");
    return responseDTO;
     */
    }

    @Tag(name = "Get With Requirement", description = "Get Requested Product ")
    @GetMapping("/search")// lay du lieu
    public ResponseDTO<List<SanPhamDTO>> search(@RequestParam(value="ten", required = false)  String ten,
                                                @RequestParam(value="ma", required = false)  String ma,
                                                @RequestParam(value="idLoaiSanPham", required = false)  Long idLoaiSanPham,
                                                @RequestParam(value="page", required = false )  Integer page,
                                                @RequestParam(value="size", required = false)  Integer size)
    {
        return ResponseDTO.<List <SanPhamDTO> >builder()
                .data(sanPhamService.search(ten, ma, idLoaiSanPham, page, size))
                .status(200)
                .message("Success search")
                .build();
    }


    @Tag(name = "Get with Id", description = "Get product with requested id")
    @GetMapping("/{id}")// lay du lieu theo id
    public ResponseDTO<SanPhamDTO> getById(@PathVariable Long id) {
        return ResponseDTO.<SanPhamDTO>builder()
                .data(sanPhamService.getById(id))
                .status(200)
                .message("Success Get By Id")
                .build();
    }


    @Tag(name = "Create Product", description = "Create a product and save it in the database")
    @PostMapping// tao ra 1 ban ghi san pham
    // @ModelAttribute: de co the show du lieu anh o dang json
    public ResponseDTO <Void> create(@ModelAttribute @Valid SanPhamDTO sanPhamDTO) throws IOException {
        sanPhamService.create(sanPhamDTO);
        return ResponseDTO. <Void> builder()
                .status(201)
                .message("Success Create Product")
                .build();
    }

    @Tag(name = "Update product information", description = "Update product information based on that product id")
    @PutMapping("/{id}")// cap nhat ban ghi san pham
    // Frontend gui du lieu o dang form-Data
    // chinh vi vay ta can dung @ModelAttribute de lay du lieu tu form-data
    // con dung @RequestBody thi se khong lay duoc du lieu tu form-data
    public ResponseDTO <Void> update(@PathVariable Long id, @ModelAttribute @Valid SanPhamDTO dto) throws IOException {
        sanPhamService.update(dto, id);
        return ResponseDTO. <Void> builder()
                .status(200)
                .message("Success")
                .build();
    }

    @Tag(name = "Delete product", description = "Delete product based on that product id")
    @DeleteMapping("/{id}")// xoa ban ghi san pham
    public ResponseDTO <Void> delete(@PathVariable Long id) {
        sanPhamService.delete(id);
        return ResponseDTO. <Void> builder()
                .status(204)
                .message("Success delete")
                .build();
    }




}
