package web_shop.api_web_shop.service;


import web_shop.api_web_shop.dto.SanPhamDTO;

import java.io.IOException;
import java.util.List;

public interface SanPhamService {

    List<SanPhamDTO> getAll();

    List<SanPhamDTO> search(String ten, String ma, Long idLoaiSanPham, Integer page, Integer size);

    SanPhamDTO getById(Long id);

    void create(SanPhamDTO sanPhamDTO) throws IOException;

    void update(SanPhamDTO sanPhamDTO, Long id) throws IOException;

    void delete(Long id);

}
