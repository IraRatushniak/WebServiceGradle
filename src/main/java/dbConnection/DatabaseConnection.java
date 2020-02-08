package dbConnection;

import entity.Book;

import java.util.List;

public interface DatabaseConnection {
    public void openConnection();
    public void closeConnection();
    public List<Book> getBook(String name);
    public List<Book> getAllBooks();
    public boolean updateBook(String oldName, String newName,  String newAuthor);
    public boolean saveBook(String name, String author);
    public boolean removeBook(String name);
}
