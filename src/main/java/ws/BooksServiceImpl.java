package ws;

import entity.Book;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

import org.apache.log4j.Logger;
import util.MissingBookNameException;

@WebService(endpointInterface = "ws.BooksService")
//@HandlerChain(file = "handlers.xml")
public class BooksServiceImpl implements BooksService {
    private BooksRepositoryImpl booksRepository = new BooksRepositoryImpl();

    @WebMethod
    @Override
    public List<Book> getBook(String name) {
        checkValueIsNotEmpty(name);
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
        checkValueIsNotEmpty(name);
        return booksRepository.saveBook(name, author);
    }

    @WebMethod
    @Override
    public boolean updateBook(String oldName, String newName, String newAuthor) {
        checkValueIsNotEmpty(oldName);
        checkValueIsNotEmpty(newName);
        return booksRepository.updateBook(oldName, newName, newAuthor);
    }

    @WebMethod
    @Override
    public boolean removeBook(String name) {
        checkValueIsNotEmpty(name);
        return booksRepository.removeBook(name);
    }

    private void checkValueIsNotEmpty(String value) {
        if (value == null || value.isEmpty()) {
            throw new MissingBookNameException();
        }
    }
}
