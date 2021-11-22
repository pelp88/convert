module ru.coderiders.teamtask {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.json;
    requires com.fasterxml.jackson.databind;


    exports ru.coderiders.teamtask.converter;
    opens ru.coderiders.teamtask.converter to javafx.fxml;
    exports ru.coderiders.teamtask.core;
    opens ru.coderiders.teamtask.core to javafx.fxml;
    exports ru.coderiders.teamtask.reader;
    opens ru.coderiders.teamtask.reader to javafx.fxml;
    exports ru.coderiders.teamtask.core.classes;
    opens ru.coderiders.teamtask.core.classes to javafx.fxml;
}