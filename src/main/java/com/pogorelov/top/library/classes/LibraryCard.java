package com.pogorelov.top.library.classes;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Создает экземпляр читательского билета.
 */
public class LibraryCard {
    private static int generalCardID;
    private final int libraryCardID;
    private final String libraryCardHolderName;
    private final int libraryCardHolderGroupID;
    private HashMap<Integer, GregorianCalendar[]> booksOnHand; //K - bookID, V - []{дата выдачи, дата возврата}

    /**
     * Конструктор класса.
     */
    public LibraryCard(Student student) {
        generalCardID++;
        this.libraryCardID = generalCardID;
        this.libraryCardHolderName = student.getFullName();
        this.libraryCardHolderGroupID = student.getGroupID();
        booksOnHand = new HashMap<>();
        student.setLibraryCardId(this.libraryCardID);
    }

    /**
     * Метод добавляет книги, взятые в библиотеке, в HashMap booksOnHand
     */
    public void addBooksOnHand(int bookID, GregorianCalendar dateOfIssue, GregorianCalendar dateOfReturn) {
        booksOnHand.put(bookID, new GregorianCalendar[]{dateOfIssue, dateOfReturn});
    }

    /**
     * Удоляет из HashMap booksOnHand книги, возвращенные в библиотеку.
     */
    public void removeBooksOnHand(int bookID) {
        booksOnHand.remove(bookID);
    }

    /**
     * Возвращает номер читательского билета
     */
    public int getLibraryCardID() {
        return libraryCardID;
    }

    /**
     * Возвращает HashMap booksOnHand с книгами, взятыми в библиотеке.
     */
    public HashMap<Integer, GregorianCalendar[]> getBooksOnHand() {
        return booksOnHand;
    }

    @Override
    public String toString() {
        return String.format("Читательский билет №: %d\nФИО читателя: %s\nНомер группы: %d",
                libraryCardID, libraryCardHolderName, libraryCardHolderGroupID);
    }
}
