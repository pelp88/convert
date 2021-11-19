package ru.coderiders.teamtask;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {
    private final Loader loader = new Loader();

    @FXML
    private Button button;

    @FXML
    private ChoiceBox<String> originalLang;

    @FXML
    private ChoiceBox<String> targetLang;

    @FXML
    private TextField enterField;

    @FXML
    private TextField resultField;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Set<String> currencies = this.loader.getCurrencies();

        for (String currency : currencies){
            this.originalLang.getItems().add(String.format("%s - %s", currency,
                    this.loader.getCurrencyName(currency)));
            this.targetLang.getItems().add(String.format("%s - %s", currency,
                    this.loader.getCurrencyName(currency)));
        }
    }

    @FXML
    protected void onClick() {
        Double value = Double.valueOf(this.enterField.getText());
        this.resultField.setText(String.valueOf(this.loader.convert(
                this.originalLang.getValue().substring(0, 3),
                this.targetLang.getValue().substring(0, 3),
                value)));
    }
}