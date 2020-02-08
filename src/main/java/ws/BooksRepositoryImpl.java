package ws;

import dbConnection.DatabaseConnectionImpl;
import entity.Book;

import java.util.List;

public class BooksRepositoryImpl implements BooksRepository {
    DatabaseConnectionImpl databaseConnection = new DatabaseConnectionImpl();

    @Override
    public List<Book> getBook(String name) {
        databaseConnection.openConnection();
        List<Book> book = databaseConnection.getBook(name);
        databaseConnection.closeConnection();
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        databaseConnection.openConnection();
        List<Book> books = databaseConnection.getAllBooks();
        databaseConnection.closeConnection();
        return books;
    }

    @Override
    public boolean updateBook(String oldName, String newName, String newAuthor) {
        databaseConnection.openConnection();
        boolean result = databaseConnection.updateBook(oldName, newName, newAuthor);
        databaseConnection.closeConnection();
        return result;
    }

    @Override
    public boolean saveBook(String name, String author) {
        databaseConnection.openConnection();
        boolean result = databaseConnection.saveBook(name, author);
        databaseConnection.closeConnection();
        return result;
    }

    @Override
    public boolean removeBook(String name) {
        databaseConnection.openConnection();
        boolean result = databaseConnection.removeBook(name);
        databaseConnection.closeConnection();
        return result;
    }
}
