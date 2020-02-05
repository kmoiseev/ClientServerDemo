package com.kmoiseev.demo.springserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseValidator {

    static void assertResponseBodyIsNotEmpty(ResponseEntity<?> responseEntity) {
        assertNotNull(responseEntity.getBody(), "Response must not be empty");
    }

    static void assertResponseBodyIsEmpty(ResponseEntity<?> responseEntity) {
        assertNull(responseEntity.getBody(), "Response must be empty");
    }

    static void assertStatusCodeEquals(ResponseEntity<?> responseEntity, HttpStatus expected) {
        assertEquals(expected, responseEntity.getStatusCode(), "Response status code must be " + expected.toString());
    }
}
