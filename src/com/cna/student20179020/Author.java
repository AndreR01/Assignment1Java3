package com.cna.student20179020;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Author {
    public int authorID;
    public String firstName;
    public String lastName;
    private List<Book> bookList;

    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }

//    public Author(int authorID, String firstName, String lastName, List<Book> bookList) {
//        this.authorID = authorID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.bookList = new LinkedList<>();
//    }


    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public static Author buildAuthor(ResultSet set) throws SQLException {
        var author = new Author(set.getInt(1),set.getString(2), set.getString(3));
        return author;
    }

    public void printAuthorsInfo(PrintStream printStream) {
        printStream.printf("\nAUTHOR ID: %d \t\t FIRST NAME: %-10s \t\t LAST NAME: %-10s",
                this.getAuthorID(), this.getFirstName(), this.getLastName());
        bookList.stream().forEach((k) -> {
            System.out.printf("\tISBN: %-12s TITLE: %-60s EDITION: %-5s", k.getIsbn(),k.getTitle(),k.getEditionNumber());
        });
    }
}
