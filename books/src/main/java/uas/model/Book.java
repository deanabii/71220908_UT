package uas.model; 

public class Book {
    private String title;
    private String author;
    private int stock;

    public Book(String title, String author, int stock) {
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public void loan() throws Exception {
        if (stock <= 0) {
            throw new Exception("Stok habis");
        }
        stock--;
    }

    public void returnBook() {
        stock++;
    }

    public int getStock() {
        return stock;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}