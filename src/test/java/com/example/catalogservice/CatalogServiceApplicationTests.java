package com.example.catalogservice;

import com.example.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1231231231", "Title", "Author", 9.90);

		webTestClient.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
					assertNotNull(actualBook);
					assertEquals(expectedBook.isbn(), actualBook.isbn());
					assertEquals("Title", actualBook.title());
				});

	}

	@Test
	void testInitBookRecord() {
		webTestClient.get()
				.uri("/books")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBodyList(Book.class).value(books -> {
					assertNotNull(books);
					assertTrue(() -> books.size() >= 1);


				});
	}

}
