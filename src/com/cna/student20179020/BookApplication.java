package com.cna.student20179020;

import java.sql.*;
import java.util.Scanner;

public class BookApplication {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:4000/books";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "A@123456";

    public static void main(String args[]) {
//        Connection conn = null;
//        Statement stmt =  null;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting");
            System.out.println("Connected");
            boolean keepPlaying = true;
            while (keepPlaying) {
                Scanner input = new Scanner(System.in);
                ResultSet rs = stmt.executeQuery("select * from authors");
                System.out.println("Please choose a number for the associated options:");
                System.out.println("1. Print all the books from the database (showing the authors)");
                System.out.println("2. Print all the authors from the database (showing the books)");
                System.out.println("3. Add a book to the database for an existing author");
                System.out.println("4. Add a new author");
                System.out.println("5. Quit\n");
                String choice = input.nextLine();
                switch (choice) {
                    case "1":
                        rs = stmt.executeQuery("select * from titles");
                        break;
                    case "2":
                        rs = stmt.executeQuery("select * from authors");
                        break;
                    case "3":
                        rs = stmt.executeQuery("select * from authors");
                        break;
                    case "4":
                        rs = stmt.executeQuery("select * from authors");
                        break;
                    case "5":
                        System.out.println("Thank you for using the database. Goodbye");
                        break;


                }
            conn.close();
            }
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString(2));
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}