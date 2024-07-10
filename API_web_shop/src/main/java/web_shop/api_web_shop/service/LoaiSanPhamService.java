package web_shop.api_web_shop.service;

import web_shop.api_web_shop.dto.LoaiSanPhamDTO;

import java.util.List;

public interface LoaiSanPhamService {
    /**
     * getAll
     * gibt alle Kategorie Data Transfer Object zurueck
     * @return List<LoaiSanPhamDTO>
     */
    List<LoaiSanPhamDTO> getAll();

    /**
     * create
     * erstellt ein Kategorie Data Transfer Object
     * @param loaiSanPhamDTO
     */
    void create(LoaiSanPhamDTO loaiSanPhamDTO);

    /**
     * update
     * aktualisiert ein Kategorie Data Transfer Object
     * @param loaiSanPhamDTO
     * @param id
     */
    void update(LoaiSanPhamDTO loaiSanPhamDTO, Long id);

    /**
     * delete
     * loescht ein Kategorie Data Transfer Object
     * @param id
     */
    void delete(Long id);

    /**
     * getById
     * gibt ein Kategorie Data Transfer Object zurueck
     * @param id
     * @return LoaiSanPhamDTO
     */
    LoaiSanPhamDTO getById(Long id);
}
