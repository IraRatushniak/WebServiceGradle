import ws.BooksServiceImpl;

import javax.xml.ws.Endpoint;

public class BooksServicePublisher {

    public static void main(String[] args) {
        System.setProperty("javax.xml.bind.JAXBContext",
                "com.sun.xml.internal.bind.v2.ContextFactory");
        Endpoint.publish(
                "http://localhost:8080/booksservice",
                new BooksServiceImpl());
    }
}