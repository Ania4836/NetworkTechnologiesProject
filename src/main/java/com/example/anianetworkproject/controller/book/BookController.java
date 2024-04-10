package com.example.anianetworkproject.controller.book;
import com.example.anianetworkproject.controller.book.dto.CreateBookResponseDto;
import com.example.anianetworkproject.controller.book.dto.GetBookDto;
import com.example.anianetworkproject.infrastructure.entity.BookEntity;
import com.example.anianetworkproject.service.book.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found successfully"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Book creation failed")
    })
    public ResponseEntity<CreateBookResponseDto> create (@RequestBody BookEntity book) {
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
