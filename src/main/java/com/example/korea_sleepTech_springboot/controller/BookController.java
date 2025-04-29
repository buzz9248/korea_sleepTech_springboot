package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.BookRespDto;
import com.example.korea_sleepTech_springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK_API)
@RequiredArgsConstructor // final 필드를 매개변수로 가지는 생성자 생성 (+ 의존성 주입의 역할까지 자동 처리)
public class BookController {
    // Service 객체를 주입받아 저장하는 변수
    private final BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    // 1. 기본 CRUD
    // 1) CREATE - BOOK 생성
    @PostMapping
    public ResponseEntity<BookRespDto> createBook(@RequestBody BookCreateReqDto dto) {
        BookRespDto book = bookService.createBook(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // 2) READ - 전체 책 조회
    @GetMapping
    public ResponseEntity<List<BookRespDto>> getAllBooks() {
        List<BookRespDto> books = bookService.getAllBooks();

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }


    // 3) READ - 단건 책 조회 (특정 ID)
    @GetMapping("/{id}")
    public ResponseEntity<BookRespDto> getBookById(@PathVariable Long id) {
        BookRespDto book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 4) UPDATE - 책 수정 (특정 ID)
    @PutMapping("/{id}")
    public ResponseEntity<BookRespDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateReqDto dto) {
        BookRespDto book = bookService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 5) DELETE - 책 삭제 (특정 ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
