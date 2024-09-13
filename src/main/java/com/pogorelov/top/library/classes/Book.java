package com.pogorelov.top.library.classes;

/**
 * Класс книги.
 */
public class Book {
    private static int generalBookID;
    private final int bookID;
    private final String bookName;
    private final String author;
    private final String publishing;
    private final int yearOfPublication;
    private boolean inStock;

    /**
     * Конструктор класса.
     */
    public Book(String bookName, String author, String publishing, int year) {
        generalBookID++;
        this.bookID = generalBookID;
        this.bookName = bookName;
        this.author = author;
        this.publishing = publishing;
        this.yearOfPublication = year;
        this.inStock = false;
    }

    /**
     * Возвращает значение boolean inStock (показывает есть ли в данный момент книга в библиотеке)
     */
    public boolean isInStock() {
        return inStock;
    }

    /**
     * Устанавливает значение для boolean inStock.
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    /**
     * Возвращает инвентарный номер книги
     */
    public int getBookID() {
        return bookID;
    }

    @Override
    public String toString() {
        return String.format("Инвентарный номер: %d\nНазвание: %s\nАвтор: %s\nИздательство: %s\nГод издания: %d",
                bookID, bookName, author, publishing, yearOfPublication);
    }
}
