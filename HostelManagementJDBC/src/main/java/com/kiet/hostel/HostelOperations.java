
package com.kiet.hostel;

import java.sql.*;
import java.util.Scanner;

public class HostelOperations {

    Connection con;
    Scanner sc;

    // Constructor
    public HostelOperations(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void insertStudents() {

        String sql = "INSERT INTO HostelStudent VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 1; i <= 3; i++) {

                System.out.println("\nEnter Student " + i + " Details");

                System.out.print("Student ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Student Name: ");
                String name = sc.nextLine();

                System.out.print("Roll Number: ");
                String roll = sc.nextLine();

                System.out.print("Hostel Name: ");
                String hostel = sc.nextLine();

                System.out.print("Room Number: ");
                String room = sc.nextLine();

                System.out.print("Fee: ");
                double fee = sc.nextDouble();
                sc.nextLine();

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, roll);
                ps.setString(4, hostel);
                ps.setString(5, room);
                ps.setDouble(6, fee);

                try {

                    ps.executeUpdate();

                    System.out.println("Student Inserted Successfully!");

                } catch (SQLIntegrityConstraintViolationException e) {

                    System.out.println("Duplicate Student ID!");

                }
            }

            ps.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public void retrieveStudents() {

        System.out.print("Enter Hostel Name: ");
        String hostel = sc.nextLine();

        String sql = "SELECT * FROM HostelStudent WHERE hostel_name = ? AND fee > ? ORDER BY fee DESC";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, hostel);
            ps.setDouble(2, 50000);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\nStudent ID: " + rs.getInt("student_id"));
                System.out.println("Student Name: " + rs.getString("student_name"));
                System.out.println("Roll Number: " + rs.getString("roll_number"));
                System.out.println("Hostel Name: " + rs.getString("hostel_name"));
                System.out.println("Room Number: " + rs.getString("room_number"));
                System.out.println("Fee: " + rs.getDouble("fee"));
            }

            if (!found) {
                System.out.println("No matching students found!");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateFee() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Fee: ");
        double fee = sc.nextDouble();

        String sql = "UPDATE HostelStudent SET fee = ? WHERE student_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, fee);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Fee Updated Successfully!");
            } else {
                System.out.println("Student ID not found!");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRoom() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        String searchSql = "SELECT * FROM HostelStudent WHERE student_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(searchSql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nStudent Found!");
                System.out.println("Student Name: " + rs.getString("student_name"));
                System.out.println("Current Room: " + rs.getString("room_number"));

                System.out.print("Enter New Room Number: ");
                String room = sc.nextLine();

                String updateSql = "UPDATE HostelStudent SET room_number = ? WHERE student_id = ?";

                PreparedStatement updatePs = con.prepareStatement(updateSql);

                updatePs.setString(1, room);
                updatePs.setInt(2, id);

                updatePs.executeUpdate();

                System.out.println("\nRoom Updated Successfully!");

                // Display updated student details
                String displaySql = "SELECT * FROM HostelStudent WHERE student_id = ?";

                PreparedStatement displayPs = con.prepareStatement(displaySql);
                displayPs.setInt(1, id);

                ResultSet updatedRs = displayPs.executeQuery();

                if (updatedRs.next()) {
                    System.out.println("\nUpdated Student Details:");
                    System.out.println("Student ID: " + updatedRs.getInt("student_id"));
                    System.out.println("Student Name: " + updatedRs.getString("student_name"));
                    System.out.println("Hostel Name: " + updatedRs.getString("hostel_name"));
                    System.out.println("Room Number: " + updatedRs.getString("room_number"));
                    System.out.println("Fee: " + updatedRs.getDouble("fee"));
                }

                updatedRs.close();
                displayPs.close();
                updatePs.close();

            } else {
                System.out.println("Student ID not found! Room cannot be updated.");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void displayTotalAndDelete() {

        // Display total fees
        String sumSql = "SELECT SUM(fee) AS total_fee FROM HostelStudent";

        try {
            PreparedStatement sumPs = con.prepareStatement(sumSql);
            ResultSet sumRs = sumPs.executeQuery();

            if (sumRs.next()) {
                System.out.println("Total Hostel Fees: " +
                        sumRs.getDouble("total_fee"));
            }

            sumRs.close();
            sumPs.close();

            // Ask for Student ID
            System.out.print("\nEnter Student ID to delete: ");
            int id = sc.nextInt();

            // Check whether student exists and get fee
            String searchSql = "SELECT fee FROM HostelStudent WHERE student_id = ?";

            PreparedStatement searchPs = con.prepareStatement(searchSql);
            searchPs.setInt(1, id);

            ResultSet rs = searchPs.executeQuery();

            if (rs.next()) {

                double fee = rs.getDouble("fee");

                if (fee > 0) {

                    String deleteSql =
                            "DELETE FROM HostelStudent WHERE student_id = ?";

                    PreparedStatement deletePs =
                            con.prepareStatement(deleteSql);

                    deletePs.setInt(1, id);

                    int rows = deletePs.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Student Deleted Successfully!");
                    } else {
                        System.out.println("Student Deletion Failed!");
                    }

                    deletePs.close();

                } else {
                    System.out.println("Student fee is not greater than zero. Cannot delete!");
                }

            } else {
                System.out.println("Student ID not found!");
            }

            rs.close();
            searchPs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
