package ru.coderiders.teamtask.core.classes;

public class FileAccessErrorWindow extends ErrorWindow{
    @Override
    public void setParams() {
        this.setHeaderText("Ошибка чтения файла кэша!");
        this.setContentText("Файл кэша не существует либо поврежден");
    }
}
