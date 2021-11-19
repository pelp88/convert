module ru.coderiders.teamtask {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens ru.coderiders.teamtask to javafx.fxml;
    exports ru.coderiders.teamtask;
}