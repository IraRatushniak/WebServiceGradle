package ws;

import entity.Book;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
public interface BooksService {
    @WebMethod
    public List<Book> getBook(@WebParam(name = "bookName") String name);

    @WebMethod
    public List<Book> getAllBooks();

    @WebMethod
    public boolean updateBook(@WebParam(name = "oldBookName") String oldName,
                              @WebParam(name = "newBookName") String newName,
                              @WebParam(name = "newAuthorName") String newAuthor);

    @WebMethod
    public boolean saveBook(@WebParam(name = "bookName") String name,
                            @WebParam(name = "authorName") String author);

    @WebMethod
    public boolean removeBook(@WebParam(name = "bookName") String name);
}
