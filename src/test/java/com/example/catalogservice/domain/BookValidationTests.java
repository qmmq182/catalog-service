package com.example.catalogservice.domain;

public class BookValidationTests {



    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        
    }
}
