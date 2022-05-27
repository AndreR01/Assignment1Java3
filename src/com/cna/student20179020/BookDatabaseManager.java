package com.cna.student20179020;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookDatabaseManager {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:4000/books";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "A@123456";

//TODO USE A PREPARED STATEMENT TO HANDLE THE NEW BOOK AND NEW AUTHOR CREATION
    public void AddAuthor(String firstName, String lastName) {
        try {
            ResultSet resp = getData("insert into authors (`firstName`,`lastName`) values (`" + firstName + "`, `" + lastName + "`);");
        } catch (Exception e) {
            System.out.println("AddAuthor: " + e);
        }
    }

//TODO USE A PREPARED STATEMENT TO HANDLE THE NEW BOOK AND NEW AUTHOR CREATION
//    public void AddBook(String isbn, String title, int edition, String copyright) {
//    }


    private ResultSet getData(String query) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        conn.close();
        return rs;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet data = getData("Select * from titles");
            while (data.next()) {
                Book book = Book.buildBook(data);
                //book.setAuthorList(GetAuthorsForBook(book.isbn));
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new LinkedList<Author>();
        try {
            ResultSet data = getData("Select * from authors");
            while (data.next()) {
                Author author = Author.buildAuthor(data);
                //author.setBookList(GetBooksForAuthor(author.authorID));
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("Author: " + e);
        }
        return authors;
    }

    public List<AuthorISBN> getAllISBN() {
        List<AuthorISBN> authorISBN = new LinkedList<AuthorISBN>();
        try {
            ResultSet data = getData("Select * from authorisbn");
            while (data.next()) {

                //author.setBookList(GetBooksForAuthor(author.authorID));
                authorISBN.add(new AuthorISBN(data.getInt(1), data.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("ISBN: " + e);
        }
        return authorISBN;
    }
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
//

//    public List<Author> GetAuthorsForBook(String isbn){
//        var query = "SELECT authors.authorID, authors.firstName, authors.lastName from authorisbn JOIN authors on authorisbn.authorID = authors.authorID WHERE authorisbn.isbn = "+ isbn;
//        List<Author> authors = new LinkedList<Author>();
//        try {
//            ResultSet data = getData(query);
//
//            while (data.next()){
//                authors.add(Author.buildAuthor(data));
//            }
//        }  catch (Exception e) {
//            System.out.println(e);
//        }
//        return authors;
//    }
//


//    public List<Book> GetBooksForAuthor(int authorId){
//        String query = "SELECT titles.isbn, titles.title, titles.editionNumber, titles.copyright FROM authorisbn JOIN titles ON authorisbn.isbn = titles.isbn WHERE authorisbn.authorID = " + authorId;
//        List<Book> books = new LinkedList<Book>();
//        try {
//            ResultSet data = getData(query);
//            while (data.next()){
//                books.add(Book.buildBook(data));
//            }
//        }  catch (Exception e) {
//            System.out.println(e);
//        }
//        return books;
//    }
