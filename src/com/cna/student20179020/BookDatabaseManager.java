package com.cna.student20179020;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookDatabaseManager {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:4000/books";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "A@123456";


    private ResultSet getData(String query) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn = getConnection();
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        conn.close();
        return rs;
    }

    /**
     * Retrieves all of the books from the database into a linkedlist
     * NOTE: this method is dangerous if the database is large. For this example is isn't.
     * @return books
     */
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet data = getData("Select * from titles");
            while (data.next()) {
                books.add(new Book(data.getString(1), data.getString(2), data.getInt(3), data.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    /**
     * Adds an author into the database.
     *
     * @param author
     */
    //TODO USE A PREPARED STATEMENT TO HANDLE THE NEW BOOK AND NEW AUTHOR CREATION
    public void AddAuthor(Author author) {
        try (
                Connection conn = getConnection();
        ) {
            String sqlQuery = "insert into authors values (default,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("AddAuthor: " + e);
        }
    }

    /**
     * Add a book to the database for an existing author.
     *
     * @param book
     */
    public void AddBook(Book book) {
        try (
                Connection conn = getConnection();
        ) {
            String sqlQuery = "insert into titles values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("AddAuthor: " + e);
        }
    }

    /**
     * Creates a list of authors from a result set on the authors table.
     *
     * @return authors
     */
    public List<Author> getAllAuthors() {
        List<Author> authors = new LinkedList<Author>();
        try {
            ResultSet data = getData("Select * from authors");
            while (data.next()) {
                authors.add(new Author(data.getInt(1), data.getString(2), data.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("Author: " + e);
        }
        return authors;
    }

    /**
     * Creates a list of authorISBN's from a result set on the authorISBN table.
     *
     * @return authorISBN
     */
    public List<AuthorISBN> getAllISBN() {
        List<AuthorISBN> authorISBN = new LinkedList<AuthorISBN>();
        try {
            //TODO This was "from authors"
            ResultSet data = getData("Select * from authorisbn");
            while (data.next()) {
                authorISBN.add(new AuthorISBN(data.getInt(1), data.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("ISBN: " + e);
        }
        return authorISBN;
    }

    /**
     * Get a connection to the Books database
     *
     * @return DriverManager.getConnection(DATABASE_URL, USER, PASS)
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }
}

//Author author = Author.buildAuthor(data);
//TODO insert sql statements here in place of Author.buildAuthor
// repeat for getAllBooks and getAllISBN
//author.setBookList(GetBooksForAuthor(author.authorID));

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
