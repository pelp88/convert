package ru.coderiders.teamtask.core.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public abstract class ErrorWindow extends Alert{
    public ErrorWindow() {
        super(Alert.AlertType.ERROR);
        this.setTitle("Ошибка!");
        this.setParams();
    }

    public void exit(){
        Platform.exit();
        System.exit(0);
    }

    public abstract void setParams();
}
