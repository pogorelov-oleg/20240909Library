package com.pogorelov.top.library.classes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс библиотеки. Хранит в себе все книги, читательские билеты, логи. Содерит методы по добавлению книг,
 * добавлению читательских билетов, выдаче и возврату книг читателю и проверке должников.
 */
public class Library {
    private final String libraryName;
    private HashMap<Integer, Book> books;//K - bookID
    private HashMap<Integer, LibraryCard> libraryCards; //K - cardID
    private HashMap<Integer, Log> logs;//K - logID

    /**
     * Конструктор класса.
     */
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.libraryCards = new HashMap<>();
        this.logs = new HashMap<>();
    }

    /**
     * Метод добавляет новую книгу в HashMap books.
     */
    public void addBook(Book book) {
        books.put(book.getBookID(), book);
        book.setInStock(true);

        Log log = new Log();
        log.setLogMessage("Добавлена новая книга:\n" + book);
        logs.put(log.getLogID(), log);
    }

    /**
     * Метод добавляет новый читательский билет HashMap libraryCards.
     */
    public void addLibraryCard(Student student) {
        LibraryCard libraryCard = new LibraryCard(student);
        libraryCards.put(libraryCard.getLibraryCardID(), libraryCard);

        Log log = new Log();
        log.setLogMessage("Выдан новый читательский билет:\n" + libraryCard);
        logs.put(log.getLogID(), log);
    }

    /**
     * Метод выдает книгу читателю.
     */
    public void lendBook(Student student, int bookID, int period) {
        GregorianCalendar dateOfReturn;
        int libraryCardID = student.getLibraryCardId();

        if (libraryCardID == 0) {
            System.out.println("ОШИБКА! Отсутствует читательский билет.\n");
            return;
        }

        if (books.get(bookID).isInStock()) {
            books.get(bookID).setInStock(false);

            dateOfReturn = new GregorianCalendar();
            dateOfReturn.add(Calendar.DAY_OF_MONTH, period);

            libraryCards.get(libraryCardID).addBooksOnHand(bookID, new GregorianCalendar(), dateOfReturn);

            Log log = new Log();
            log.setLogMessage("Читатель (читательский билет №" + libraryCardID + ") взял книгу:\n" + books.get(bookID)
                    + "\nДата возврата: " + dateOfReturn.getTime());
            logs.put(log.getLogID(), log);
        } else System.out.println("ОШИБКА! В данный момент этой книги нет в библиотеке\n");
    }

    /**
     * Метод для возврата книги в библиотеку.
     */
    public void returnBook(Student student, int bookID) {
        int libraryCardID = student.getLibraryCardId();
        books.get(bookID).setInStock(true);
        libraryCards.get(libraryCardID).removeBooksOnHand(bookID);

        Log log = new Log();
        log.setLogMessage("Читатель (читательский билет №" + libraryCardID + ") вернул книгу:\n" + books.get(bookID));
        logs.put(log.getLogID(), log);
    }

    /**
     * Метод выводит в консоль информацию о не сданных вовремя книгах.
     */
    public void searchForDebtors() {
        GregorianCalendar todayDate = new GregorianCalendar();
        boolean thereAreDebtors = false;
        for (Map.Entry<Integer, LibraryCard> entry1 : libraryCards.entrySet()) {
            Integer key1 = entry1.getKey(); //Номер читательского билета
            LibraryCard value1 = entry1.getValue(); //Экземпляр читательского билета
            for (Map.Entry<Integer, GregorianCalendar[]> entry2 : value1.getBooksOnHand().entrySet()) {
                Integer key2 = entry2.getKey(); //Инвентарный номер книги
                GregorianCalendar[] value2 = entry2.getValue(); //[]date
                if (value2[1].before(todayDate)) {
                    thereAreDebtors = true;
                    System.out.print("Читатель (читательский билет №" + key1
                            + ") не вернул книгу:\n" + books.get(key2) + "\n");
                }
            }
        }
        if (!thereAreDebtors) System.out.println("Должников нет");
    }

    /**
     * Возвращает HashMap logs.
     */
    public HashMap<Integer, Log> getLogs() {
        return logs;
    }

    /**
     * Возвращает название библиотеки.
     */
    @Override
    public String toString() {
        return libraryName;
    }
}
