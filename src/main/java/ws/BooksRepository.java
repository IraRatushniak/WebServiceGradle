package ws;

import entity.Book;

import javax.jws.WebMethod;
import java.util.List;

public interface BooksRepository{

        public List<Book> getBook(String name);

        public List<Book> getAllBooks();

        public boolean updateBook(String oldName, String newName,  String newAuthor);

        public boolean saveBook(String name, String author);

        public boolean removeBook(String name);
}
