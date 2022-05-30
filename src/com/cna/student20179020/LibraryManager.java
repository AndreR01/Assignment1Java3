package com.cna.student20179020;

import java.util.LinkedList;
import java.util.List;

public class LibraryManager {
    private List<Author> authorList;
    private List<Book> bookList;

    private BookDatabaseManager databaseManager;

    public LibraryManager (BookDatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        reloadFromDataSource();
    }

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

    public List<Author> getAuthorList(){
        return authorList;
    }

    public List<Book> getBookList(){
        return bookList;
    }
}


