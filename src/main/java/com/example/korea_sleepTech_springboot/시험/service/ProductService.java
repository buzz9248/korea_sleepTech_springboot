package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductCreateRequestDto reqDto) {
        ProductResponseDto responseDto = null;

        try {
            Product newProduct = new Product(null, reqDto.getName(), reqDto.getDescription(), reqDto.getPrice(), null, null);
            productRepository.save(newProduct);

            responseDto = new ProductResponseDto(
                    newProduct.getId(),
                    newProduct.getName(),
                    newProduct.getDescription(),
                    newProduct.getPrice()
            );
            return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public List<ProductResponseDto> findAllProducts() {

        List<ProductResponseDto> responseDtos = null;

        try {
            List<Product> products = productRepository.findAll();

            responseDtos = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDtos;
    }

    public ProductResponseDto findProductById(Long id) {
        ProductResponseDto responseDto = null;
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품 없음 id: " + id));
            responseDto = new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }


    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto reqDto) {
        ProductResponseDto responseDto = null;
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품 없음 id: " + id));

            if (reqDto.getName() != null) {
                product.setName(reqDto.getName());
            }
            if (reqDto.getDescription() != null) {
                product.setDescription(reqDto.getDescription());
            }
            if (reqDto.getPrice() != null) {
                product.setPrice(reqDto.getPrice());
            }

            product.setUpdatedAt(LocalDateTime.now());

            Product updatedProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public void deleteProduct(Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품 없음 id: " + id));
            productRepository.delete(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
