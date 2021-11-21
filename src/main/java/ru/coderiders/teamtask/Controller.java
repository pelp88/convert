package ru.coderiders.teamtask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private Map<String, Converter> converters = new HashMap<>();
    private Alert alert = new Alert(Alert.AlertType.ERROR);

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
        Reader webReader = new WebReader();

        try {
            webReader.read();
        } catch (IOException e) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Ошибка загрузки данных!");
            alert.setContentText("Проверьте интернет-соединение");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }

        Map<String, Reader.ParamsContainer> data = webReader.getData();
        for (var entry : data.entrySet()){
            Reader.ParamsContainer params = entry.getValue();
            Converter converter = new Converter();
            converter.setQuantity(params.quantity);
            converter.setRatio(params.ratio);
            this.converters.put(entry.getKey(), converter);

            this.originalLang.getItems().add(String.format("%s - %s", entry.getKey(), params.name));
            this.targetLang.getItems().add(String.format("%s - %s", entry.getKey(), params.name));
        }
    }

    @FXML
    protected void onClick() {
        String currency1 = this.originalLang.getValue().substring(0, 3);
        String currency2 = this.targetLang.getValue().substring(0, 3);

        if (!currency1.equals(currency2)) {
            Double value = Double.valueOf(this.enterField.getText());
            Double originalToRub = this.converters.get(currency1).reverseConvert(value);
            Double toTarget = this.converters.get(currency2).convert(originalToRub);
            this.resultField.setText(String.valueOf(toTarget));
        } else {
            this.resultField.setText(this.enterField.getText());
        }
    }
}