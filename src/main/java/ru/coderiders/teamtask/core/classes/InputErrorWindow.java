package ru.coderiders.teamtask.core.classes;

public class InputErrorWindow extends ErrorWindow{
    @Override
    public void setParams() {
        this.setHeaderText("Ошибка ввода!");
        this.setContentText("Проверьте заполнены ли поля валют и кол-ва УЕ");
    }
}
