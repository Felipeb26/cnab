module com.batsworks.cnab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires org.slf4j;

    opens com.batsworks.cnab to javafx.fxml;
    exports com.batsworks.cnab;
}