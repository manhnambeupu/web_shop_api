package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.GioHangChiTietDTO;

import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTietDTO> getAll();

    void create(GioHangChiTietDTO dto);

    void update(GioHangChiTietDTO dto, Long id);

    void delete(Long id);

    GioHangChiTietDTO getById(Long id);
}
