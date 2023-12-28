package com.example.catalogservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;


    @Test
    @DisplayName("testSerialize")
    void testSerialize() throws IOException {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        JsonContent<Book> jsonContent = json.write(book);
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());


    }



    @Test
    @DisplayName("testDeserialize")
    void testDeserialize() throws IOException {
        var content = """
{
"isbn": "1234567890",
"title": "Title",
"author": "Author",
"price": 9.90
}
""";
        Assertions.assertThat(json.parse(content)).usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
        Assertions.assertThat(json.parse(content))
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
    }
}
