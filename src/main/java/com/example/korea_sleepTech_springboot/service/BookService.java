package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.request.BookCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.BookRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.C_Book;
import com.example.korea_sleepTech_springboot.entity.C_Category;
import com.example.korea_sleepTech_springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public ResponseDto<BookRespDto> createBook(BookCreateReqDto reqDto) {
//        ResponseDto<BookRespDto> bookRespDto = null;
        try {
            C_Book newBook = new C_Book(null, reqDto.getWriter(), reqDto.getTitle(), reqDto.getContent(), reqDto.getCategory());

            C_Book savedBook = bookRepository.save(newBook);

            BookRespDto response = new BookRespDto(
                    savedBook.getWriter(),
                    savedBook.getTitle(),
                    savedBook.getCategory()
            );

            return ResponseDto.setSuccess("새로운 책이 정상적으로 등록되었습니다.", response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("게시글 등록 중 오류가 발생하였습니다: " + e.getMessage());
        }

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
            C_Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다: " + id));

            bookRepository.delete(book);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseDto<List<BookRespDto>> getBooksByTitleContaining(String keyword) {
        List<BookRespDto> books = null;
        try {
            List<C_Book> booksByTitleContaining = bookRepository.findByTitleContaining(keyword);

            books = booksByTitleContaining.stream()
                    .map(book -> new BookRespDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("책 검색을 정상적으로 완료하였습니다.", books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("책을 검색하는 동안 문제가 발생하였습니다.");
        }
    }

    public ResponseDto<List<BookRespDto>> getBooksByCategory(C_Category category) {
        List<BookRespDto> books = null;
        try {
            List<C_Book> booksByCategory = bookRepository.findByCategory(category);

            books = booksByCategory.stream()
                    .map(book -> new BookRespDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("카테고리 별 책 조회가 성공하였습니다.", books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("카테고리별 책을 검색하는 동안 문제가 발생하였습니다.");
        }

    }

    public ResponseDto<List<BookRespDto>> getBooksByCategoryAndWriter(C_Category category, String writer) {
        List<BookRespDto> books = null;
        List<C_Book> searchingBook = null;

        try {
            if (category == null) {
                searchingBook = bookRepository.findByWriter(writer);

            } else {
                searchingBook = bookRepository.findByCategoryAndWriter(category, writer);
            }

            books = searchingBook.stream()
                    .map(book -> new BookRespDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("조회가 완료되었습니다.", books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("카테고리와 작성자로 조회하는 동안 문제가 발생하였습니다.");
        }

    }
}
