package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.request.BookCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.BookRespDto;
import com.example.korea_sleepTech_springboot.entity.C_Book;
import com.example.korea_sleepTech_springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookRespDto createBook(BookCreateReqDto reqDto) {
        BookRespDto bookRespDto = null;
        try {
            C_Book newBook = new C_Book(null, reqDto.getWriter(), reqDto.getTitle(), reqDto.getContent(), reqDto.getCategory());

            C_Book savedBook = bookRepository.save(newBook);

            bookRespDto = new BookRespDto(
                    savedBook.getWriter(),
                    savedBook.getTitle(),
                    savedBook.getCategory()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookRespDto;
    }

    public List<BookRespDto> getAllBooks() {

        List<BookRespDto> responseDtos = null;
        try {
            List<C_Book> books = bookRepository.findAll();

            responseDtos = books.stream()
                    .map(book -> new BookRespDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDtos;
    }

    public BookRespDto getBookById(Long id) {
        BookRespDto bookRespDto = null;

        try {
            C_Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다: " + id));

            bookRespDto = new BookRespDto(
                    book.getWriter(),
                    book.getTitle(),
                    book.getCategory()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookRespDto;
    }

    public BookRespDto updateBook(Long id, BookUpdateReqDto updateReqDto) {
        BookRespDto bookRespDto = null;
        try {
            C_Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다: " + id));

            book.setContent(updateReqDto.getContent());
            book.setCategory(updateReqDto.getCategory());

            C_Book updatedBook = bookRepository.save(book);

            bookRespDto = new BookRespDto(
                    updatedBook.getWriter(),
                    updatedBook.getTitle(),
                    updatedBook.getCategory()
            );
            return bookRespDto;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookRespDto;
    }

    public void deleteBook(Long id) {
        try {
            C_Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다: " + id));

            bookRepository.delete(book);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
