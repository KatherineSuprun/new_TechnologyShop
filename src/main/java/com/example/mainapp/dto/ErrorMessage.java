package com.example.mainapp.dto;

public class ErrorMessage {
    private String message;
    private int errorCode;

    // Конструктор класса
    public ErrorMessage(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    // Метод для получения сообщения об ошибке
    public String getMessage() {
        return message;
    }

    // Метод для получения кода ошибки
    public int getErrorCode() {
        return errorCode;
    }

    // Метод для установки сообщения об ошибке
    public void setMessage(String message) {
        this.message = message;
    }

    // Метод для установки кода ошибки
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }

    public static void main(String[] args) {
        // Пример использования класса ErrorMessage
        ErrorMessage error = new ErrorMessage("Ошибка ввода данных", 1001);
        System.out.println("Сообщение об ошибке: " + error.getMessage());
        System.out.println("Код ошибки: " + error.getErrorCode());

        // Изменение сообщения и кода ошибки
        error.setMessage("Новая ошибка");
        error.setErrorCode(2002);

        System.out.println("Обновленное сообщение об ошибке: " + error.getMessage());
        System.out.println("Обновленный код ошибки: " + error.getErrorCode());

        // Вывод всего объекта ErrorMessage
        System.out.println(error);
    }
}