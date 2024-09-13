package com.pogorelov.top.library;

import com.pogorelov.top.library.classes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task03-Library");
        //Создаем экземпляры студентов
        List<Student> students = new ArrayList<>();
        students.add(new Student("Олег Викторович Погорелов", 38, 3021));
        students.add(new Student("Ольга Ивановна Пырх", 21, 3023));
        students.add(new Student("Артем Николаевич Попов", 28, 213));


        //Создаем экземпляр библиотеки library
        Library library = new Library("Библиотека им. А. П. Чехова");

        //Создаем экземпляры книг и добавляем их в базу данных библиотеки
        library.addBook(new Book("Преступление и наказание", "Ф. М. Достоевский",
                "Эксмо", 2023));
        library.addBook(new Book("Старик и море", "Эрнест Хэмингуэй",
                "Мордовское книжное издательство", 1982));
        library.addBook(new Book("1984", "Джорж Оруэлл", "АСТ", 2021));
        library.addBook(new Book("Журнал \"Мурзилка\"№9", "-",
                "Молодая гвардия", 1961));

        //Выдаем студентам читательские билеты и добавляем их в базу данных библиотеки
        library.addLibraryCard(students.get(0));
        library.addLibraryCard(students.get(1));


        //Выдаем кники студентам
        library.lendBook(students.get(0), 3, 2); //Студент ,берет книгу ID-3 на 2 дня
        library.lendBook(students.get(1), 3, 1); //Ошибка! Книги нет в наличии
        library.lendBook(students.get(1), 1, 1); //Студент ,берет книгу ID-1 на 1 день
        library.returnBook(students.get(1), 1); //Студент ,возвращает книгу ID-1
        library.lendBook(students.get(2), 2, 3); //Ошибка! Отсутствует читательский билет

        //Выводим лог всех операций в библиотеке
        for (Map.Entry<Integer, Log> entry : library.getLogs().entrySet()) {
            Log value = entry.getValue();
            System.out.println(value);
        }
        //Проверяем, есть ли должники, и выводим результат в консоль
        library.searchForDebtors();
    }
}
