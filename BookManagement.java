import java.sql.*;
import java.util.Scanner;

public class BookManagement {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "ajay_user";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- Book Management Menu ---");
                System.out.println("1. Insert Book Details");
                System.out.println("2. Display All Books");
                System.out.println("3. Display Book by Title");
                System.out.println("4. Display All Books by Author");
                System.out.println("5. Update Price of a Book");
                System.out.println("6. Delete a Book");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> insertBook(conn, sc);
                    case 2 -> displayAllBooks(conn);
                    case 3 -> displayBookByTitle(conn, sc);
                    case 4 -> displayBooksByAuthor(conn, sc);
                    case 5 -> updateBookPrice(conn, sc);
                    case 6 -> deleteBook(conn, sc);
                    case 7 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertBook(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        String sql = "INSERT INTO Book (Bookid, Title, Auther, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setDouble(4, price);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " book(s) inserted successfully.");
        }
    }

    private static void displayAllBooks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Book";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nBookID\tTitle\tAuthor\tPrice");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%.2f\n",
                        rs.getInt("Bookid"),
                        rs.getString("Title"),
                        rs.getString("Auther"),
                        rs.getDouble("Price"));
            }
        }
    }

    private static void displayBookByTitle(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Title to search: ");
        String title = sc.nextLine();
        String sql = "SELECT * FROM Book WHERE Title = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nBookID\tTitle\tAuthor\tPrice");
                    System.out.printf("%d\t%s\t%s\t%.2f\n",
                            rs.getInt("Bookid"),
                            rs.getString("Title"),
                            rs.getString("Auther"),
                            rs.getDouble("Price"));
                } else {
                    System.out.println("No book found with title: " + title);
                }
            }
        }
    }

    private static void displayBooksByAuthor(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Author name: ");
        String author = sc.nextLine();
        String sql = "SELECT * FROM Book WHERE Auther = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\nBookID\tTitle\tAuthor\tPrice");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d\t%s\t%s\t%.2f\n",
                            rs.getInt("Bookid"),
                            rs.getString("Title"),
                            rs.getString("Auther"),
                            rs.getDouble("Price"));
                }
                if (!found) System.out.println("No books found for author: " + author);
            }
        }
    }

    private static void updateBookPrice(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();

        String sql = "UPDATE Book SET Price = ? WHERE Bookid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Price updated successfully.");
            else System.out.println("Book not found.");
        }
    }

    private static void deleteBook(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Book WHERE Bookid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Book deleted successfully.");
            else System.out.println("Book not found.");
        }
    }
}