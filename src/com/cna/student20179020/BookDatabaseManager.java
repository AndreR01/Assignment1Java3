package com.cna.student20179020;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDatabaseManager {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:4000/books";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "A@123456";

//TODO Use LibrayrManager to manage the cyclical relationship
//    public static LibraryManager loadLibraryManager(){
//        //TODO Build a library up correctly with all authors and books and their relationships
//        List<Author> authorList = new LinkedList<>();
//        List<Book> bookList = getAllBooks();
//        return new LibraryManager(authorList, booklist);

    private ResultSet getData(String query) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        conn.close();
        return rs;
    }

    public List<Author> GetAuthorsForBook(String isbn){
        var query = "SELECT authors.authorID, authors.firstName, authors.lastName from authorisbn JOIN authors on authorisbn.authorID = authors.authorID WHERE authorisbn.isbn = "+ isbn;
        List<Author> authors = new LinkedList<Author>();
        try {
            ResultSet data = getData(query);

            while (data.next()){
                authors.add(Author.buildAuthor(data));
            }
        }  catch (Exception e) {
            System.out.println(e);
        }
        return authors;
    }

    public List<Book> GetBooksForAuthor(int authorId){
        String query = "SELECT titles.isbn, titles.title, titles.editionNumber, titles.copyright FROM authorisbn JOIN titles ON authorisbn.isbn = titles.isbn WHERE authorisbn.authorID = " + authorId;
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet data = getData(query);
            while (data.next()){
                books.add(Book.buildBook(data));
            }
        }  catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }
    public List<Book> loadBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet data = getData("Select * from titles");
            while (data.next()){
                Book book = Book.buildBook(data);
                book.setAuthorList(GetAuthorsForBook(book.isbn));
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    public List<Author> loadAuthors(){
        List<Author> authors = new LinkedList<Author>();
        try {
            ResultSet data = getData("Select * from authors");
            while (data.next()){
                Author author = Author.buildAuthor(data);
                author.setBookList(GetBooksForAuthor(author.authorID));
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return authors;
    }
//TODO old code
//    private List<Book> bookList;
//    private List<Author> authorList;
//    public BookDatabaseManager(){}
//    public static Book getBookByISBN
//    public List<Book> getBookList() {
//        return bookList;
//    }
//    public List<Author> getAuthorList() {
//        return authorList;
//    }
//    private void loadDatabase(){}
//    private void loadBooks(){}
//    private void loadAuthors(){}
}
