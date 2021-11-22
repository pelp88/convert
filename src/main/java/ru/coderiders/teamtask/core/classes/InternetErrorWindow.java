package ru.coderiders.teamtask.core.classes;

public class InternetErrorWindow extends ErrorWindow{
    @Override
    public void setParams() {
        this.setHeaderText("Ошибка загрузки данных!");
        this.setContentText("Проверьте интернет-соединение");
    }
}
