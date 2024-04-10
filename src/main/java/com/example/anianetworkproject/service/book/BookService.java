package com.example.anianetworkproject.service.book;

import com.example.anianetworkproject.controller.book.dto.CreateBookResponseDto;
import com.example.anianetworkproject.controller.book.dto.GetBookDto;
import com.example.anianetworkproject.infrastructure.entity.BookEntity;
import com.example.anianetworkproject.infrastructure.repository.BookRepository;
import com.example.anianetworkproject.service.book.error.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll() {
        var books = bookRepository.findAll();

        return books.stream().map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDto getOne(long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return mapBook(book);
    }

    private GetBookDto mapBook(BookEntity book) {
        return new GetBookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearPublished(),
                book.getAvailableCopies() > 0
        );
    }

    public CreateBookResponseDto create(BookEntity book) {

        var bookEntity = new BookEntity();

        book.setIsbn(book.getIsbn());
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setPublisher(book.getPublisher());
        book.setYearPublished(book.getYearPublished());
        book.setAvailableCopies(book.getAvailableCopies());

        var newBook = bookRepository.save(bookEntity);

        return new CreateBookResponseDto(
                newBook.getId(),
                newBook.getIsbn(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getPublisher(),
                newBook.getYearPublished(),
                newBook.getAvailableCopies()
        );
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        bookRepository.deleteById(id);
    }
}

