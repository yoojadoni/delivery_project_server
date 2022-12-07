package com.example.demo.controller;

import com.example.demo.common.StatusCodeEnum;
import com.example.demo.dto.MenuDTO;
import com.example.demo.dto.ResponseMenuDTO;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class APIMenuController {

    @Autowired
    MenuService menuService;

    /**
     * 메뉴 단건조회
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/menu/{id}")
    public ResponseEntity getMenu(@PathVariable Long id) throws Exception {
        MenuDTO menuDTO = menuService.findByMenuIdToDTO(id);
        return ResponseEntity.ok().body(menuDTO);
    }

    @GetMapping("/menu")
    public ResponseEntity getMenuList(Pageable pageable) throws Exception {
        Page<MenuDTO> menuDTOPage = menuService.findAllWithPagealbeToDTO(pageable);
        return ResponseEntity.ok().body(menuDTOPage);
    }

    @PostMapping("/menu")
    public ResponseEntity createMenu(@RequestBody MenuDTO menuDTO) throws Exception {
        MenuDTO menuDto = menuService.saveFromDto(menuDTO);
        return ResponseEntity.ok().body(menuDto);
    }

    @PatchMapping("/menu")
    public ResponseEntity updateMenu(@RequestBody MenuDTO menuDTO) throws Exception {
        MenuDTO menuDto = menuService.updateMenuFromDto(menuDTO);
        return ResponseEntity.status(StatusCodeEnum.UPDATE_SUCCESS.getCode()).body(menuDTO);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity deleteMenu(@PathVariable Long id) throws Exception {
        menuService.deleteMenuFromDTO(id);
        return ResponseEntity.status(StatusCodeEnum.DELETE_SUCCESS.getCode()).build();
    }

}
