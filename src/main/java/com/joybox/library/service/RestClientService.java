package com.joybox.library.service;

import com.joybox.library.model.OpenLibrarySubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {
  @Autowired
  private RestTemplate restTemplate;

  public ResponseEntity<OpenLibrarySubject> getBooks() {
    OpenLibrarySubject response = restTemplate.getForObject("http://openlibrary.org/subjects/love.json", OpenLibrarySubject.class);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
