package com.joybox.library;

import com.joybox.library.controller.BookRestController;
import com.joybox.library.model.Book;
import com.joybox.library.model.OpenLibrarySubject;
import com.joybox.library.service.RestClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class LibraryApplicationTests {

	@Mock
	private RestClientService restClientService;

	@InjectMocks
	private BookRestController bookRestController;


	@Test
	public void getBooks_shouldReturnMockedObject() {
		HashMap<String, Object> author = new HashMap<>();
		author.put("name", "John Doe");
		List<HashMap<String, Object>> authors = new ArrayList<>();
		authors.add(author);
		HashMap<String, Object> work = new HashMap<>();
		work.put("title", "Java Programming");
		work.put("edition_count", 2);
		work.put("authors", authors);
		List<HashMap<String, Object>> works = new ArrayList<>();
		works.add(work);

		OpenLibrarySubject object = new OpenLibrarySubject(
			"/subjects/lova",
			"love",
			"subject",
			15035,
			works
		);

		List<Book> books = new ArrayList<>();
		Mockito.when(restClientService.getBooks()).thenReturn(new ResponseEntity<>(object, HttpStatus.OK));

		ResponseEntity<List<Book>> response = bookRestController.getBooks();
		Assertions.assertNotNull(books);

		books = response.getBody();

		Assertions.assertEquals( 1, books.size());
		Assertions.assertEquals("Java Programming",books.get(0).getTitle());
		Assertions.assertEquals("John Doe", books.get(0).getAuthor());
		Assertions.assertEquals(2, books.get(0).getEditionNumber());
	}

	@Test
	public void getBooks_returnError() {
		Mockito.when(restClientService.getBooks())
				.thenThrow(new RestClientException("Error getting list subject"));

		ResponseEntity<List<Book>> response = bookRestController.getBooks();

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assertions.assertEquals(null, response.getBody());
	}

}
