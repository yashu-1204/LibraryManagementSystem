import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private final String FILE_NAME = "library_data.ser";

    public Library() {
        books = loadData();
    }

    public void addBook(String id, String title, String author) {
        books.add(new Book(id, title, author));
        saveData();
        System.out.println("Book added successfully!");
    }

    public void issueBook(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                if (!book.isIssued()) {
                    book.issue();
                    saveData();
                    System.out.println("Book issued successfully!");
                    return;
                } else {
                    System.out.println("Book already issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                if (book.isIssued()) {
                    book.returnBook();
                    saveData();
                    System.out.println("Book returned successfully!");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    @SuppressWarnings("unchecked")
    private List<Book> loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Book>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data.");
            return new ArrayList<>();
        }
    }
}
