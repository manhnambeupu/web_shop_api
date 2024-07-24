package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.SanPhamChiTietDTO;

import java.io.IOException;
import java.util.List;

public interface SanPhamChiTietService {

    List<SanPhamChiTietDTO> getAll();

    SanPhamChiTietDTO getById(Long id) throws IOException;

    void create (SanPhamChiTietDTO sanPhamChiTietDTO)throws IOException;

    void update (SanPhamChiTietDTO sanPhamChiTietDTO, Long id) throws IOException;

    void delete (Long id);
}
