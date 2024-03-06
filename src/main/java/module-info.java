module ad.portafolioblackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens ad.portafolioblackjack to javafx.fxml;
    exports ad.portafolioblackjack;
}