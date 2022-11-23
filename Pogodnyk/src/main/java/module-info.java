module PetP {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens PetP to javafx.fxml;
    exports PetP;
}
