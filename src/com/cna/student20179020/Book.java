package com.cna.student20179020;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Book {

    public String isbn;
    public String title;
    public int editionNumber;
    public String copyright;
    private List<Author> authorList;

    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new LinkedList<>();
    }

    public Book(String isbn, String title, int editionNumber, String copyright, List<Author> authorList) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new LinkedList<>();
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public static Book buildBook(ResultSet set) throws SQLException {
        Book book = new Book(set.getString(1),set.getString(2),set.getInt(3),set.getString(4));
        return book;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }


}