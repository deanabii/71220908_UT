package uas;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uas.model.Book;
import uas.service.BookService;

public class AppTest {

    private BookService service;

    @BeforeEach
    public void setUp() {
        service = new BookService();
        service.addBook("Buku A", "Penulis A", 2);
        service.addBook("Buku B", "Penulis B", 1);
        service.addBook("Buku C", "Penulis C", 1);
    }

    @Test
    public void testPinjamBukuTersedia() {
        try {
            service.loanBook("Buku A");
            Book book = service.findBookByTitle("Buku A");
            assertEquals(1, book.getStock()); // Awalnya 2, sekarang 1
        } catch (Exception e) {
            fail("Tidak seharusnya gagal meminjam buku yang tersedia");
        }
    }

    @Test
    public void testPinjamBukuTidakAda() {
        Exception exception = assertThrows(Exception.class, () -> {
            service.loanBook("Buku Tidak Ada");
        });
        assertEquals("Buku tidak ditemukan", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideBookTitles")
    public void testPinjamBanyakBuku(String title) throws Exception {
        Book book = service.findBookByTitle(title);
        int initialStock = book.getStock();
        service.loanBook(title);
        assertEquals(initialStock - 1, book.getStock());
    }

    private static Stream<String> provideBookTitles() {
        return Stream.of("Buku A", "Buku B", "Buku C");
    }
}
