module com.example.cp2406assignmentbeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
   requires org.apache.commons.csv;

    opens com.example.cp2406assignmentbeta to javafx.fxml;
    exports com.example.cp2406assignmentbeta;


}