package service.Impl;

import model.Book;
import service.BookService;

import java.util.*;

public class BookServiceImpl implements BookService{
    List<Book> books = new ArrayList<>();

    @Override
    public String createBooks(List<Book> books) {
        this.books.addAll(books);
        return "WAS SUCCESSFUL!!!";
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return null;
    }

    @Override
    public String removeBookById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                this.books.remove(book);
            }
        }
        return "WAS SUCCESSFUL!!!";
    }

    @Override
    public List<Book> sortBooksByPriceInDescendingOrder() {
        Collections.sort(books);
        for (Book book : books) {
            System.out.println(
                    "\nprice = " + book.getPrice()
            );
        }
        return null;
    }

    @Override
    public List<Book> filterBooksByPublishedYear() {
        return null;
    }

    @Override
    public List<Book> getBookByInitialLetter() {
        return null;
    }


    @Override
    public Book maxPriceBook() {
        Optional<Book> max = books.stream().max(Comparator.comparing(Book :: getPrice));
        System.out.println(max);
        return null;
    }
}
