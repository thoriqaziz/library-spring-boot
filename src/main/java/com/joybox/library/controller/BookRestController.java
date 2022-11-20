package com.joybox.library.controller;

import com.joybox.library.model.Book;
import com.joybox.library.model.OpenLibrarySubject;
import com.joybox.library.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController {

  @Autowired
  private RestClientService restClientService;

  public static List<Book> books = new ArrayList<>();

  @GetMapping("/")
  public String root() {
    return "BookRestController is running";
  }

  @GetMapping("/list")
  public ResponseEntity<List<Book>> getBooks() {

    try {
      OpenLibrarySubject object = restClientService.getBooks().getBody();

      for (HashMap<String, Object> book : object.getWorks()) {
        List<HashMap<String, Object>> authors = (List<HashMap<String, Object>>) book.get("authors");
        books.add(new Book(book.get("title").toString(), authors.get(0).get("name").toString(), Integer.valueOf(book.get("edition_count").toString())));
      }

      return new ResponseEntity<>(books, HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/total")
  public Integer getTotalBooks() {
    return books.size();
  }
}
