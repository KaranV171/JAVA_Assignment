
package com.kiet.hostel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return;
        }

        Scanner sc = new Scanner(System.in);

        HostelOperations obj = new HostelOperations(con, sc);

        int choice;

        do {
            System.out.println("\n===== HOSTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert 3 Students");
            System.out.println("2. Retrieve Students by Hostel and Fee");
            System.out.println("3. Update Student Fee");
            System.out.println("4. Search Student and Update Room");
            System.out.println("5. Display Total Fees and Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    obj.insertStudents();
                    break;

                case 2:
                    obj.retrieveStudents();
                    break;

                case 3:
                    obj.updateFee();
                    break;

                case 4:
                    obj.updateRoom();
                    break;

                case 5:
                    obj.displayTotalAndDelete();
                    break;

                case 6:
                    System.out.println("Exiting Hostel Management System...");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 6);

        try {
            con.close();
            System.out.println("Database Connection Closed.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
