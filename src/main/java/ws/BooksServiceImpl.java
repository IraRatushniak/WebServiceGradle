package ws;

import entity.Book;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

import org.apache.log4j.Logger;
import util.MissingBookNameException;

@WebService(endpointInterface = "ws.BooksService")
public class BooksServiceImpl implements BooksService {
    private BooksRepositoryImpl booksRepository = new BooksRepositoryImpl();

    @WebMethod
    @Override
    public List<Book> getBook(String name) {
        if (name == null || name.isEmpty()) {
            throw new MissingBookNameException();
        } else if (booksRepository.getBook(name).isEmpty()) {
            throw new IllegalArgumentException("Book with name " + name + "is not present in database");
        }
        return booksRepository.getBook(name);
    }

    @WebMethod
    @Override
    public List<Book> getAllBooks() {
        return booksRepository.getAllBooks();
    }

    @WebMethod
    @Override
    public boolean saveBook(String name, String author) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid book name");
        }
        booksRepository.saveBook(name, author);
        return true;
    }

    @WebMethod
    @Override
    public boolean updateBook(String oldName, String newName, String newAuthor) {
        if (oldName == null || newName == null) {
            return false;
        }
        booksRepository.updateBook(oldName, newName, newAuthor);
        return true;
    }

    @WebMethod
    @Override
    public boolean removeBook(String name) {
        if (name == null) {
            return false;
        }
        booksRepository.removeBook(name);
        return true;
    }
}
