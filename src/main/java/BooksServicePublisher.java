import ws.BooksServiceImpl;

import javax.xml.ws.Endpoint;

public class BooksServicePublisher {

    public static void main(String[] args) {
        Endpoint.publish(
                "http://localhost:8080/booksservice",
                new BooksServiceImpl());
    }
}