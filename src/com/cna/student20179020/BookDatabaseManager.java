package com.cna.student20179020;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Java 3 CP3566 Spring 2022
 * BookDatabaseManager manages
 *
 * @author Andre
 */
public class BookDatabaseManager {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:4000/books";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "A@123456";


    /**
     * Establish connection to the database.
     * @param query
     * @return rs
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private ResultSet getData(String query) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn = getConnection();
        ResultSet rs = conn.prepareStatement(query).executeQuery();
        conn.close();
        return rs;
    }

    /**
     * Creates a linked list of books.
     * NOTE: this method is dangerous if the database is large. For this example it isn't.
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
     * @param firstName
     * @param lastName
     */
    public void AddAuthor(String firstName, String lastName) {
        try (
                Connection conn = getConnection();
        ) {
            String sqlQuery = "insert into authors values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
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
    public void AddBook(Book book, List<Integer> authorIDS) {
        try (
                Connection conn = getConnection();
        ) {
            String sqlQuery = "insert into titles (isbn, title, editionNumber, copyright) values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());
            ResultSet resultSet = preparedStatement.executeQuery();
            String insertAuthorISBNQuery = "INSERT INTO authorISBN (authorID,isbn) VALUES (?,?)";
            PreparedStatement preparedStatementAuthorISBN = conn.prepareStatement((insertAuthorISBNQuery));
            for (int authorID:
                 authorIDS) {
            preparedStatementAuthorISBN.setInt(1, authorID);
            preparedStatementAuthorISBN.setString(2, book.getIsbn());
            preparedStatementAuthorISBN.addBatch();
            }
            int[] resultSetAuthorISBN = preparedStatementAuthorISBN.executeBatch();
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
     * Get a connection to the Books database.
     *
     * @return DriverManager.getConnection(DATABASE_URL, USER, PASS)
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }
}