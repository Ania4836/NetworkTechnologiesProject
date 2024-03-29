package com.example.anianetworkproject.controller;
import com.example.anianetworkproject.controller.dto.CreateBookResponseDto;
import com.example.anianetworkproject.controller.dto.GetBookDto;
import com.example.anianetworkproject.infrastructure.entity.BookEntity;
import com.example.anianetworkproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponseDto> create (@RequestBody BookEntity book) {
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
