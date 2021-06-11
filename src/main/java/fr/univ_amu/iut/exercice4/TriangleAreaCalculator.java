package fr.univ_amu.iut.exercice4;

import fr.univ_amu.iut.exercice3.TriangleArea;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TriangleAreaCalculator extends Application {
    private TriangleArea triangleArea = new TriangleArea();

    private Slider x1Slider = new Slider(0, 10, 0);
    private Slider x2Slider = new Slider(0, 10, 0);
    private Slider x3Slider = new Slider(0, 10, 0);

    private Slider y1Slider = new Slider();
    private Slider y2Slider = new Slider();
    private Slider y3Slider = new Slider();

    private Label p1Label = new Label("P1");
    private Label p2Label = new Label("P2");
    private Label p3Label = new Label("P3");

    private Label x1Label = new Label("X1 :");
    private Label x2Label = new Label("X2 :");
    private Label x3Label = new Label("X3 :");

    private Label y1Label = new Label("Y1 :");
    private Label y2Label = new Label("Y2 :");
    private Label y3Label = new Label("Y3 :");

    private static Label areaLabel = new Label("Area :");
    private static TextField areaTextField = new TextField();

    private static GridPane root = new GridPane();

    private DoubleBinding area;


    private static void configSlider(Slider slider) {
        slider.setMax(10);
        slider.setMin(0);
        slider.setValue(0);
        slider.setBlockIncrement(1);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(4);
        slider.setPrefSize(root.getMinWidth()-50,50);

    }

    @Override
    public void start(Stage stage) throws Exception {
        root.setMinHeight(500);
        root.setMinWidth(500);
        configGridPane();
        configSliders();

        addSliders();
        addArea();

        addPointLabels();
        createBinding();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Triangle Area Calculator");
        stage.show();
    }

    private void configSliders() {
        configSlider(x1Slider);
        configSlider(y1Slider);
        configSlider(x2Slider);
        configSlider(y2Slider);
        configSlider(x3Slider);
        configSlider(y3Slider);
    }

    private void configGridPane() {
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);
        root.add(x1Label,0,1);
        root.add(y1Label,0,2);
        root.add(x2Label,0,4);
        root.add(y2Label,0,5);
        root.add(x3Label,0,7);
        root.add(y3Label,0,8);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(50);
        column1.setMinWidth(50);

        root.getColumnConstraints().add(column1);

    }

    private void addArea() {
        areaTextField.setText("0");
        root.add(areaLabel,0,9);
        root.add(areaTextField,1,9);
    }

    private void addSliders() {
        ColumnConstraints column = new ColumnConstraints();
        column.fillWidthProperty();

        column.setHalignment(HPos.CENTER);
        root.getColumnConstraints().add(column);
        Node node =x1Slider;
        root.add(node, 1,1);
        node =y1Slider;
        root.add(node, 1,2);
         node =x2Slider;
        root.add(node, 1,4);
        node =y2Slider;
        root.add(node, 1,5);
        node =x3Slider;
        root.add(node, 1,7);
        node =y3Slider;
        root.add(node, 1,8);
    }

    private void addPointLabels() {
        Node node = p1Label;
        root.add(node, 1,0);
        node = p2Label;
        root.add(node, 1,3);
        node = p3Label;
        root.add(node, 1,6);
    }

    private void createBinding() {
        DoubleBinding complexBinding = new DoubleBinding() {
            {super.bind(x1Slider.valueProperty(),y1Slider.valueProperty(),x2Slider.valueProperty(),
                    y2Slider.valueProperty(),x3Slider.valueProperty(),y3Slider.valueProperty());}
            @Override
            protected double computeValue() {
                double result = ((x1Slider.valueProperty().get()*y2Slider.valueProperty().get())-(x1Slider.valueProperty().get()*y3Slider.valueProperty().get()));
                result = result + (x2Slider.valueProperty().get()*y3Slider.valueProperty().get());
                result = result - (x2Slider.valueProperty().get()*y1Slider.valueProperty().get());
                result = result + (x3Slider.valueProperty().get()*y1Slider.valueProperty().get());
                result = result - (x3Slider.valueProperty().get()*y2Slider.valueProperty().get());
                result = Math.abs(result);
                result = result/2;

                return result;
            }
        };
        area = complexBinding;

        area.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                areaTextField.setText(String.valueOf(newValue.intValue()));
            }
        });
    }
}
