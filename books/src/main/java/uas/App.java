package uas;

import java.util.Scanner;

import uas.service.BookService;

public class App {
    public static void main(String[] args) {
        BookService service = new BookService();
        Scanner scanner = new Scanner(System.in);
        String input;

        service.addBook("Clean Code", "Uncle Bob", 3);
        service.addBook("The Pragmatic Programmer", "Andy Hunt", 2);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Lihat buku");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Kembalikan buku");
            System.out.println("4. Tambah buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            input = scanner.nextLine();

            try {
                switch (input) {
                    case "1":
                        for (var book : service.getAllBooks()) {
                            System.out.println("- " + book.getTitle() + "\n  Author: " + book.getAuthor() + " (Stok: " + book.getStock() + ")");
                        }
                        break;
                    case "2":
                        System.out.print("Judul buku: ");
                        String loanTitle = scanner.nextLine();
                        service.loanBook(loanTitle);
                        System.out.println("Berhasil dipinjam!");
                        break;
                    case "3":
                        System.out.print("Judul buku: ");
                        String returnTitle = scanner.nextLine();
                        service.returnBook(returnTitle);
                        System.out.println("Berhasil dikembalikan!");
                        break;
                    case "4":
                        System.out.print("Judul buku: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Penulis: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Stok: ");
                        int newStock = Integer.parseInt(scanner.nextLine());
                        service.addBook(newTitle, newAuthor, newStock);
                        System.out.println("Buku berhasil ditambahkan!");
                        break;
                    case "5":
                        scanner.close();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
