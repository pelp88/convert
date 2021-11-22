package ru.coderiders.teamtask.core.classes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.coderiders.teamtask.converter.Converter;
import ru.coderiders.teamtask.reader.CacheReader;
import ru.coderiders.teamtask.reader.CacheWriter;
import ru.coderiders.teamtask.reader.Reader;
import ru.coderiders.teamtask.reader.WebReader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CurrencyConverterController implements Initializable {
    private final Map<String, Converter> converters = new HashMap<>();

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
        Reader reader = new WebReader();

        try {
            reader.read();
            CacheWriter.write(reader.getData());
        } catch (IOException e1) {
            this.showErrorWindow(new InternetErrorWindow(), false);
            try {
                reader = new CacheReader();
                reader.read();
            } catch (IOException e2) {
                this.showErrorWindow(new FileAccessErrorWindow(), true);
            }
        }

        Map<String, Reader.CurrencyData> data = reader.getData();
        for (var entry : data.entrySet()){
            Reader.CurrencyData params = entry.getValue();
            String name = String.format("%s - %s", entry.getKey(), params.name);

            Converter converter = new Converter();
            converter.setQuantity(params.quantity);
            converter.setRatio(params.ratio);
            this.converters.put(name, converter);

            this.originalLang.getItems().add(name);
            this.targetLang.getItems().add(name);
        }
    }

    @FXML
    protected void onClick() {
        String currency1 = this.originalLang.getValue();
        String currency2 = this.targetLang.getValue();
        String input = this.enterField.getText();

        if (currency1 == null | currency2 == null | input.equals("")) {
            this.showErrorWindow(new InputErrorWindow(), false);
        } else if (!currency1.equals(currency2)) {
            Double value = Double.valueOf(input);
            Double originalToRub = this.converters.get(currency1).reverseConvert(value);
            Double toTarget = this.converters.get(currency2).convert(originalToRub);
            this.resultField.setText(String.valueOf(toTarget));
        } else if (currency1.equals(currency2)) {
            this.resultField.setText(input);
        }
    }

    private void showErrorWindow(ErrorWindow error, boolean exitFlag){
        error.showAndWait();
        if (exitFlag) {
            error.exit();
        }
    }
}