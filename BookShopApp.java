
package bookshopapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookShopApp extends JFrame {
    private final ArrayList<Book> bookInventory;
    private final JTextField titleField;
    private final JTextField authorField;
    private final JTextField copiesField;
    private final JTextArea resultArea;

    public BookShopApp() {
        // Initialize book inventory (you can load this from a database or file)
        bookInventory = new ArrayList<>();
        bookInventory.add(new Book("John Doe", "Sample Book 1", 19.99, "Sample Publisher", 10));
        bookInventory.add(new Book("Jane Smith", "Sample Book 2", 29.99, "Sample Publisher", 5));

        // Set up the JFrame
        setTitle("Hamen's Book Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Number of Copies:"));
        copiesField = new JTextField();
        inputPanel.add(copiesField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener((ActionEvent e) -> {
            searchBook();
        });
        inputPanel.add(searchButton);

        // Create result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the JFrame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void searchBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        int requestedCopies = Integer.parseInt(copiesField.getText());

        Book foundBook = null;
        for (Book book : bookInventory) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            resultArea.setText("Book not found.");
        } else if (foundBook.getStockPosition() >= requestedCopies) {
            double totalCost = foundBook.getPrice() * requestedCopies;
            resultArea.setText("""
                               Book Details:
                               Title: """ + foundBook.getTitle() + "\n" +
                    "Author: " + foundBook.getAuthor() + "\n" +
                    "Price: $" + foundBook.getPrice() + "\n" +
                    "Publisher: " + foundBook.getPublisher() + "\n" +
                    "In Stock: " + foundBook.getStockPosition() + " copies\n" +
                    "Total Cost: $" + totalCost);
        } else {
            resultArea.setText("Required copies not in stock.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookShopApp());
    }
}

class Book {
    private final String author;
    private final String title;
    private final double price;
    private final String publisher;
    private final int stockPosition;

    public Book(String author, String title, double price, String publisher, int stockPosition) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.publisher = publisher;
        this.stockPosition = stockPosition;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getStockPosition() {
        return stockPosition;
    }
}


