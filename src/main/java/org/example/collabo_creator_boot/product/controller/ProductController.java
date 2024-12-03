package org.example.collabo_creator_boot.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.collabo_creator_boot.common.PageRequestDTO;
import org.example.collabo_creator_boot.common.PageResponseDTO;
import org.example.collabo_creator_boot.creator.dto.MyPageDTO;
import org.example.collabo_creator_boot.product.dto.ProductListDTO;
import org.example.collabo_creator_boot.product.dto.ProductReadDTO;
import org.example.collabo_creator_boot.product.dto.ProductRegisterDTO;
import org.example.collabo_creator_boot.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<ProductListDTO>> getCreatorProductList(
            @RequestParam("creatorId") String creatorId,
            @RequestParam(value = "searchQuery", required = false) String searchQuery,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "categoryNo", required = false) Long categoryNo,
            @ModelAttribute PageRequestDTO pageRequestDTO) {

        PageResponseDTO<ProductListDTO> response = productService.getCreatorProductList(
                creatorId, pageRequestDTO, searchQuery, status, categoryNo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{productNo}")
    public ResponseEntity<ProductReadDTO>productDetails(@PathVariable(name = "productNo")Long productNo){
        return ResponseEntity.ok(productService.readProductDetails(productNo));
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRegisterDTO productRegisterDTO) {
        Long productId = productService.registerProduct(productRegisterDTO);
        return ResponseEntity.ok(productId);
    }

    @PutMapping("/modify/{productNo}")
    public ResponseEntity<String> updateProduct(
            @RequestParam("creatorId") String creatorId,
            @PathVariable("productNo") Long productNo,
            @Valid @RequestBody ProductReadDTO productReadDTO) {
        try {
            // 서비스 호출하여 업데이트 수행
            productService.updateProduct(creatorId, productNo, productReadDTO);
            return ResponseEntity.ok("상품 정보가 저장되었습니다.");
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("상품 정보 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
