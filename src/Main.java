import enums.Gender;
import enums.Genre;
import enums.Language;
import model.Book;
import model.User;
import service.Impl.BookServiceImpl;
import service.Impl.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // Бардык Id лер уникальный болуш керек. Эгер уникальный болбосо озубуз тузгон UniqueConstraintException класс ыргытсын.
        // User дин email адресси уникальный болуш керек жана @ болуусу керек. Эгер уникальный болбосо UniqueConstraintException класс,
        // @ болбосо озубуз тузгон BadRequestException класс ыргытсын.
        // User дин телефон номери +996 дан башталып 13 символдон турсун. Болбосо BadRequestException класс ыргытсын.
        // Китептин баасы терс сан болбошу керек. Болбосо NegativeNumberException ыргытсын.
        // Китептин чыккан жылы келечек убакыт болбошу керек.Болбосо DateTimeException ыргытсын.
        // Китептин автору бош болбошу керек. Болбосо EmptyStackException ыргытсын.
        Book book = new Book(7L, "Ak-keme", Genre.HISTORICAL, new BigDecimal(76), "Чынгыз Айтматов", Language.KYRGYZ, LocalDate.ofYearDay(2004, 12));
        Book book1 = new Book(2L, "Психология", Genre.FANTASY, new BigDecimal(765), "Искак Разаков", Language.RUSSIAN, LocalDate.ofYearDay(2005, 8));
        Book book2 = new Book(6L, "Учкан куштар", Genre.ROMANCE, new BigDecimal(876), "Алыкул Осмонов", Language.ENGLISH, LocalDate.ofYearDay(2002, 4));
        Book book3 = new Book(4L, "Бугу эне", Genre.DETECTIVE, new BigDecimal(456), "Чынгыз эне", Language.KYRGYZ, LocalDate.ofYearDay(2011, 19));
        Book book4 = new Book(1L, "Бугу эне2", Genre.DETECTIVE, new BigDecimal(456), "Чынгыз эне", Language.KYRGYZ, LocalDate.ofYearDay(2015, 5));
        List<Book> books = new ArrayList<>(List.of(book, book1, book2, book3, book4));
        User user = new User(1L, "Kanykei", "Akjoltoi kyzy", "@kanykeiakjoltoikyzy", "+996706050119", Gender.FEMALE, new BigDecimal(1234), (List<Book>) book);
        User user1 = new User(4L, "Adil", "Aitbaev", "@adilaitbaev", "996504548723", Gender.MALE, new BigDecimal(567), (List<Book>) book3);
        User user2 = new User(7L, "Nuriza", "Muratova", "@nurizamuratova", "996505593419", Gender.FEMALE, new BigDecimal(8765), (List<Book>) book4);
        User user3 = new User(2L, "Aigerim", "Bektova", "@aigerimbektenova", "+996123456767", Gender.FEMALE, new BigDecimal(1234), (List<Book>) book1);
        User user4 = new User(6L, "Madina", "Bektenova", "@madinabektenova", "+996456341278", Gender.FEMALE, new BigDecimal(5677), (List<Book>) book2);
        List<User> users = new ArrayList<>(List.of(user, user1, user2, user3, user4));
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        while (true) {
            int number = new Scanner(System.in).nextInt();
            System.out.println("""
                    1 CREATE USER
                    2 FIND ALL USERS
                    3 FIND USER BY ID
                    4 REMOVE USER BY NAME
                    5 UPDATE USER
                    6 GROUP USER BY GENDER
                    7 BUY BOOKS
                    8 CREATE BOOKS
                    9 GET ALL BOOKS
                    10 GET BOOKS BY GENGE
                    11 REMOVE BOOKS BY ID
                    12 SORT BOOKS BY PRICE IN DESCENDING ORDER
                    13 FILTER BOOKS BY PUBLISHED YEAR
                    14 GET BOOK BY INITIAL LETTER
                    15 MAX PRICE BOOK
                      """);
            switch (number) {
                case 1 -> System.out.println(userServiceImpl.createUser(users));
                case 2 -> System.out.println(userServiceImpl.findAllUsers());
                case 3 -> {
                    System.out.print("Write the id: ");
                    long x = new Scanner(System.in).nextInt();
                    System.out.println(userServiceImpl.findUserById(x));

                }
                case 4 -> {
                    System.out.print("Write the name: ");
                    String word = new Scanner(System.in).next();
                    System.out.println(userServiceImpl.removeUserByName(word));
                }
                case 5 -> {
                    System.out.print("Write the id: ");
                    long s = new Scanner(System.in).nextInt();
                    userServiceImpl.updateUser(s);
                }
                case 6 -> userServiceImpl.groupUsersByGender();
                case 7 -> {
                    System.out.print("Write the person name: ");
                    String x = new Scanner(System.in).nextLine();
                    userServiceImpl.buyBooks(x, bookServiceImpl.getAllBooks());
                }
                case 8 -> System.out.println(bookServiceImpl.createBooks(books));
                case 9 -> System.out.println(bookServiceImpl.getAllBooks());
                case 10 -> {
                    System.out.print("Write the genre: ");
                    String word = new Scanner(System.in).next();
                    System.out.println(bookServiceImpl.getBooksByGenre(word));
                }
                case 11 -> {
                    System.out.print("Write the id: ");
                    long x = new Scanner(System.in).nextInt();
                    System.out.println(bookServiceImpl.removeBookById(x));

                }
                case 12 -> System.out.println(bookServiceImpl.maxPriceBook());
            }
            case 13 -> System.out.println(bookServiceImpl.filterBooksByPublishedYear());
            case 14 -> System.out.println(bookServiceImpl.getBookByInitialLetter());
            case 15 -> System.out.println(bookServiceImpl.maxPriceBook());
                default -> throw new Exception("There is no such thing");
            }
        }
    }
