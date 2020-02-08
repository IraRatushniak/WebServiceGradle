package dbConnection;

import entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseConnectionImpl implements DatabaseConnection {
    private EntityManager manager;
    private EntityManagerFactory factory;

    @Override
    public void openConnection() {
        factory = Persistence.createEntityManagerFactory("primary");
        this.manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }

    @Override
    public void closeConnection() {
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }

    @Override
    public List<Book> getBook(String name) {
        return manager.
                createQuery("select b from Book b where b.name=:name", Book.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Book> getAllBooks() {
        return manager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public boolean saveBook(String name, String author) {
        Book book = new Book();
        book.setAuthor(author);
        book.setName(name);
        manager.persist(book);
        return true;
    }

    @Override
    public boolean updateBook(String oldName, String newName, String newAuthor) {
        Book book = manager.createQuery("select b from Book b where b.name=:name", Book.class)
                .setParameter("name", oldName)
                .getSingleResult();
        book.setAuthor(newAuthor);
        book.setName(newName);
        manager.persist(book);
        return true;
    }

    @Override
    public boolean removeBook(String name) {
        Book book = manager.createQuery("select b from Book b where b.name=:name", Book.class)
                .setParameter("name", name)
                .getSingleResult();
        manager.remove(book);
        return true;
    }

    public Boolean isBookInTable(Book book) {
        return manager.contains(book);
    }
}
