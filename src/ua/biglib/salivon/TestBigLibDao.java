package ua.biglib.salivon;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.biglib.salivon.dao.BookJpaController;

import ua.biglib.salivon.entity.Book;

@Repository
@Transactional
public class TestBigLibDao {

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(BookConfig.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBigLibDao test = (TestBigLibDao) context.getBean("testBigLibDao");
//        System.out.println(test.getEntityManager() + "--EntityManager");
//        System.out.println(test.getEntityManagerFactory() + "--EntityManagerFactory");

//        EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BigLibDaoPU2");
        emf=test.getEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        EntityManager em = test.getEntityManager();
//        EntityTransaction tx = em.getTransaction();
// Обеспечивает постоянство Book в базе данных
        BookJpaController bookCon = new BookJpaController(emf);
        bookCon = (BookJpaController)context.getBean("bookJpaController");
        Book book = new Book();
        book.setAuthor("Jul Vern");
        book.setCountPage(221);
        book.setGenre("romadfsdfn");
        book.setTitle("New");
        book.setPath("Vana");
//        tx.begin();
//        em.persist(book);
//        tx.commit();
        bookCon.create(book);
        Book book1 = new Book(12);
        book.setAuthor("Jul Vern");
        System.out.println(book);
        System.out.println(book.getAuthor());
        System.out.println(book1);
        System.out.println(book1.getAuthor());
        book1.setAuthor("gh");
        System.out.println(book1.getAuthor());
        System.out.println(bookCon.getBookCount());
        List<Book> books = bookCon.findBookEntities();
        for (Book book2 : books) {
            System.out.println(book2);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
