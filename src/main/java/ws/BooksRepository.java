package ws;

import entity.Book;

import javax.jws.WebMethod;
import java.util.List;

public interface BooksRepository {

    List<Book> getBook(String name);

    List<Book> getAllBooks();

    boolean updateBook(String oldName, String newName, String newAuthor);

    boolean saveBook(String name, String author);

    boolean removeBook(String name);
}
