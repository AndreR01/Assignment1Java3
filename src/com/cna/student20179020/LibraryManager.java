package com.cna.student20179020;

import java.util.List;

public class LibraryManager {
    private List<Author> authorList;
    private List<Book> booklist;

    public LibraryManager(List<Author> authorList, List<Book> bookList) {
        this.authorList = authorList;
        this.booklist = bookList;
    }
    public List<Author> getAuthorList(){
        return authorList;
    }

    public List<Book> getBooklist(){
        return booklist;
    }

    //TODO add a book to the list and to the underlying Database (and it's authors)
    //TODO add an author to the list and to the underlying Database (and it's authors (books??)

}


