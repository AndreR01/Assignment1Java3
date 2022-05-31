package com.cna.student20179020;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Java 3 CP3566 Spring 2022
 * Library manager
 *
 * @author Andre
 */
public class LibraryManager {
    private List<Author> authorList;
    private List<Book> bookList;

    private BookDatabaseManager databaseManager;

    /**
     * Constructor to initialize a Library Manager object.
     * @param databaseManager
     */
    public LibraryManager (BookDatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        reloadFromDataSource();
    }

    /**
     * Links books and authors together to be managed in the BookDatabase Manager.
     */
    public void reloadFromDataSource() {

        authorList = databaseManager.getAllAuthors();
        bookList = databaseManager.getAllBooks();
        List<AuthorISBN> authorISBN = databaseManager.getAllISBN();

        for (Author author : authorList) {
            List<String> bookISBNs = new LinkedList<>();
            for (AuthorISBN ai : authorISBN) {
                if(ai.authorID == author.authorID){
                    bookISBNs.add(ai.isbn);
                }
            }
            for (Book b : bookList){
            if(bookISBNs.contains(b.isbn)){
                author.getBookList().add(b);
                b.getAuthorList().add(author);
                }
            }
        }
    }

    /**
     * Get the author list.
     * @return authorList
     */
    public List<Author> getAuthorList(){
        return authorList;
    }

    /**
     * Get the book list.
     * @return bookList
     */
    public List<Book> getBookList(){
        return bookList;
    }

    /**
     * Add book to the database and refresh table
     * @param book
     * @param authorIDS
     */
    public void addBook(Book book, List<Integer> authorIDS){
        databaseManager.AddBook(book, authorIDS);
        reloadFromDataSource();
    }

    /**
     * Add author to the database and refresh table
     * @param firstName
     * @param lastName
     */
    public Author addAuthor(String firstName, String lastName){
        databaseManager.AddAuthor(firstName, lastName);
        reloadFromDataSource();
        Iterator<Author> iterator = authorList.iterator();
        while (iterator.hasNext()) {
            Author author = iterator.next();
            if (author.getFirstName().equals(firstName) && author.getLastName().equals(lastName)) {
                return author;
            }
        }
        Author author = new Author(0, "J", "Doe");
        return author;
    }
}


