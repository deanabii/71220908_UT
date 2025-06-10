package uas.service; 

import java.util.ArrayList;
import java.util.List;

import uas.model.Book;

public class BookService {
    private final List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, int stock) {
        books.add(new Book(title, author, stock));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) return book;
        }
        return null;
    }

    public void loanBook(String title) throws Exception {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.loan();
        } else {
            throw new Exception("Buku tidak ditemukan");
        }
    }

    public void returnBook(String title) throws Exception {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.returnBook();
        } else {
            throw new Exception("Buku tidak ditemukan");
        }
    }
}