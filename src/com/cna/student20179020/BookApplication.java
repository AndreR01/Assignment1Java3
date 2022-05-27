package com.cna.student20179020;

import java.util.Scanner;


/**
 * Java 3 CP3566 Spring 2022
 *
 * Book application
 *
 */
public class BookApplication {

    public static void main(String args[]) {

        var db = new BookDatabaseManager();
        var libraryManager = new LibraryManager(db);


        boolean keepPlaying = true;
        while (keepPlaying) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("Please choose a number for the associated options:");
            System.out.println("1. Print all the books from the database (showing the authors)");
            System.out.println("2. Print all the authors from the database (showing the books)");
            System.out.println("3. Add a book to the database for an existing author");
            System.out.println("4. Add a new author");
            System.out.println("5. Quit");
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    for (Book book : libraryManager.getBookList()) {
                        book.printBookInfo(System.out);
                    }
                    System.out.println();
                    break;
                case "2":
                    for (Author author : libraryManager.getAuthorList()) {
                        author.printAuthorsInfo(System.out);
                    }
                    System.out.println();
                    break;
                case "3":
//                    db.AddBook(isbn);
//                    db.AddAuthorToBook(isbn, authorid);
                    break;
                case "4":
                    db.AddAuthor("Andre", "R");
                    libraryManager.reloadFromDataSource();
                    break;
                case "5":
                    System.out.println("Thank you for using the database. Goodbye");
                    keepPlaying = false;
                    break;


            }
        }
    }
}