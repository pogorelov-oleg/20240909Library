package com.pogorelov.top.library.classes;

import java.util.GregorianCalendar;

/**
 * Класс лога.
 */
public class Log {
    private static int generalLogID;
    private int logID;
    private GregorianCalendar date;
    private String logMessage;

    /**
     * Конструктор класса.
     */
    public Log() {
        generalLogID++;
        this.logID = generalLogID;
        date = new GregorianCalendar();
        logMessage = "Запись №" + logID + " от " + date.getTime() + "\n";
    }

    /**
     * Возвращает номер записи.
     */
    public int getLogID() {
        return logID;
    }

    /**
     * Добавляет сообщение в лог.
     */
    public void setLogMessage(String logMessage) {
        this.logMessage += logMessage + "\n";
    }

    @Override
    public String toString() {
        return logMessage;
    }
}
