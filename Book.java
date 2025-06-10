import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String id;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | " +
               (isIssued ? "Issued" : "Available");
    }
}
