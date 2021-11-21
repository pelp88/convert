module ru.coderiders.teamtask {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.json;
    requires com.fasterxml.jackson.databind;


    opens ru.coderiders.teamtask to javafx.fxml;
    exports ru.coderiders.teamtask;
}