/******************************************
 * Name: Nikhil Nambiar Chengat
 * ID: 217783606
 * Course: EECS 1021
 * Minor Project: Self Watering System
 ******************************************/

package sample; //package

//imports
import com.fazecast.jSerialComm.SerialPort;
import javafx.beans.binding.Bindings;
import javafx.geometry.*;
import sample.DataController;
import sample.SerialPortService;
import javafx.application.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.IOException;


public class Main extends Application {
    private final static int MAX_MOISTURE_SENSOR_VALUE = 1 << 10; //moisture sensor

    public static void main(String[] args) { // main method
        launch(args);



    }

    private Node makeButtonRow(SerialPort sp) {  //button for manually switching on the pump
        var outputStream = sp.getOutputStream();
        var hbox = new HBox();
        var button = new Button("Activate Pump!");

        hbox.setSpacing(10.0);

        button.setOnMousePressed(value -> {
            try {
                outputStream.write(255);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        button.setOnMouseReleased(value -> {
            try {
                outputStream.write(0);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        hbox.getChildren().addAll(button);

        return hbox;
    }

    private Node makeSliderRow(SerialPort sp) {  //slider to switch on the LED
        var outputStream = sp.getOutputStream();
        var hbox = new HBox();
        var slider = new Slider();
        var label = new Label();

        hbox.setSpacing(10.0);
        slider.setMin(0.0);
        slider.setMax(100.0);

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                outputStream.write(newValue.byteValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        label.textProperty().bind(Bindings.when(slider.valueProperty().isEqualTo(100)).then("LED is ON!").otherwise("LED is OFF!"));
        hbox.getChildren().addAll(slider, label);

        return hbox;

    }

    @Override
    public void start(Stage stage) { //stage and the live graph of moisture sensor
        var controller = new DataController();
        var serialPort = SerialPortService.getSerialPort("/dev/cu.usbserial-14110"); //port
        serialPort.addDataListener(controller);
        var rowA = makeButtonRow(serialPort);
        var rowB = makeSliderRow(serialPort);


        stage.setTitle("Control Menu");

        var now = System.currentTimeMillis();


        var xAxis = new NumberAxis("Time (ms)", now, now + 50000, 10000); // x-axis
        var yAxis = new NumberAxis("Voltage (V)", 0, MAX_MOISTURE_SENSOR_VALUE, 10); // y-axis

        var series = new XYChart.Series<>(controller.getDataPoints());
        var lineChart = new LineChart<>(xAxis, yAxis, FXCollections.singletonObservableList(series));
        lineChart.setTitle("Live Moisture Sensor Data");

        var vbox = new VBox();
        vbox.setSpacing(10.0);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(rowA, rowB, lineChart);

        Scene scene = new Scene(vbox,600,400); // JavaFX window
        stage.setScene(scene);
        stage.show();
    }
}
