package rafdatabase.model.dol;

/**
 * Created by j2arr on 8/25/2016.
 */
public class Book {

    private int id;
    private String ISBN;
    private String title;
    private String author;
    private float price;

    public Book() {}

    public Book(String ISBN, String title, String author, float price) {
        this();

        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(int id, String ISBN, String title, String author, float price) {
        this(ISBN, title, author, price);

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[Id: " + id + ", " +
                "ISBN: " + ISBN + ", " +
                "Title: " + title + ", " +
                "Author: " + author + ", " +
                "Price: " + price + "]";
    }
}
