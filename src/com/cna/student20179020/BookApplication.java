package com.cna.student20179020;

import java.util.LinkedList;
import java.util.Scanner;


/**
 * Java 3 CP3566 Spring 2022
 * Book application provides a simple console interface to interact with the database
 *
 * @author Andre
 */
public class BookApplication {

    /**
     * Main method to prompt user input from menu options.
     *
     * @param args
     */
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
                    int edition = Integer.parseInt(input.nextLine());
                    System.out.println("Please enter the copyright of the book: ");
                    String copyright = input.nextLine();
                    System.out.println("Insert the author ID for the author you would like to add to this book: ");
//                    LinkedList<Integer> authorIDS = getAuthorsIDSFromUser();
//                    libraryManager.addBook(new Book(isbn, title, edition, copyright), authorIDS);
                    break;
                case "4":
                    System.out.println("Please enter the author's first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter the author's last name");
                    String lastName = input.nextLine();
                    libraryManager.addAuthor(firstName, lastName);
                    break;
                case "5":
                    System.out.println("Thank you for using the database. Goodbye");
                    keepUsing = false;
                    break;
            }
        }
    }

//    private static LinkedList<Integer> getAuthorsIDSFromUser() {
//        var authorIDS = new LinkedList<Integer>();
//        return authorIDS;
//    }
}