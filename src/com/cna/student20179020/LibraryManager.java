package com.cna.student20179020;

import java.util.LinkedList;
import java.util.List;

public class LibraryManager {
    private List<Author> authorList;
    private List<Book> bookList;

    private BookDatabaseManager db;

    public LibraryManager (BookDatabaseManager databaseManager){
        db = databaseManager;
        reloadFromDataSource();
    }

    public void reloadFromDataSource() {

        authorList = db.getAllAuthors();
        bookList = db.getAllBooks();
        List<AuthorISBN> authorISBN = db.getAllISBN();

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

    public List<Author> getAuthorList(){
        return authorList;
    }

    public List<Book> getBookList(){
        return bookList;
    }

    //TODO add a book to the list and to the underlying Database (and it's authors)
    //TODO add an author to the list and to the underlying Database (and it's authors (books??)
}


