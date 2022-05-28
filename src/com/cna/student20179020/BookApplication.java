package com.cna.student20179020;

import java.util.Scanner;


/**
 * Java 3 CP3566 Spring 2022
 * Book application provides a simple console interface to interact with the database
 *
 * @author Andre
 */
public class BookApplication {

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        BookDatabaseManager databaseManager = new BookDatabaseManager();
        LibraryManager libraryManager = new LibraryManager(databaseManager);

        boolean keepUsing = true;
        while (keepUsing) {
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
                    System.out.println("Please enter the books isbn: ");
                    String isbn = input.nextLine();
                    System.out.println("Please enter the title of the book: ");
                    String title = input.nextLine();
                    System.out.println("Please enter the edition of the book: ");
                    int edition = input.nextInt();
                    System.out.println("Please enter the copyright of the book: ");
                    String copyright = input.nextLine();
                    //TODO copyright isn't making it into the constructor
                    databaseManager.AddBook(new Book(isbn,title, edition, copyright));
                    //databaseManager.AddAuthorToBook(isbn, authorid);
                    break;
                case "4":
                    System.out.println("Please enter the author's first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter the author's last name");
                    String lastName = input.nextLine();
                    //TODO is it ok to pass 0 to the constructor. authorID is autoincrement
                    Author newAuthor = new Author(0, firstName, lastName);
                    databaseManager.AddAuthor(newAuthor);
                    libraryManager.reloadFromDataSource();
                    break;
                case "5":
                    System.out.println("Thank you for using the database. Goodbye");
                    keepUsing = false;
                    break;


            }
        }
    }
}