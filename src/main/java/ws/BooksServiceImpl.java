package ws;

import entity.Book;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

import org.apache.log4j.Logger;
import util.MissingBookNameException;

@WebService(endpointInterface = "ws.BooksService")
@HandlerChain(file = "handlers.xml")
public class BooksServiceImpl implements BooksService {
    private BooksRepositoryImpl booksRepository = new BooksRepositoryImpl();

    @WebMethod
    @Override
    public List<Book> getBook(String name) {
        if (name == null || name.isEmpty()) {
            throw new MissingBookNameException();
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
        if (name == null || name.isEmpty()) {
            throw new MissingBookNameException();
        }
        return booksRepository.saveBook(name, author);
    }

    @WebMethod
    @Override
    public boolean updateBook(String oldName, String newName, String newAuthor) {
        if (oldName == null || newName == null || oldName.isEmpty() || newAuthor.isEmpty()) {
            throw new MissingBookNameException();
        }
        return booksRepository.updateBook(oldName, newName, newAuthor);
    }

    @WebMethod
    @Override
    public boolean removeBook(String name) {
        if (name == null || name.isEmpty()) {
            throw new MissingBookNameException();
        }
        return booksRepository.removeBook(name);
    }
}
