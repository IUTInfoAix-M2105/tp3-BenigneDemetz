package fr.univ_amu.iut.exercice3_1;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TriangleArea {

    private IntegerProperty x1 = new SimpleIntegerProperty(0);
    private IntegerProperty y1 = new SimpleIntegerProperty(0);

    private IntegerProperty x2 = new SimpleIntegerProperty(0);
    private IntegerProperty y2 = new SimpleIntegerProperty(0);

    private IntegerProperty x3 = new SimpleIntegerProperty(0);
    private IntegerProperty y3 = new SimpleIntegerProperty(0);

    private NumberBinding area;

    private StringExpression output;

    public TriangleArea() {
        createBinding();
    }

    public static void main(String[] args) {
        TriangleArea triangleArea = new TriangleArea();

        triangleArea.printResult();

        triangleArea.setP1(0, 1);
        triangleArea.setP2(5, 0);
        triangleArea.setP3(4, 3);

        triangleArea.printResult();

        triangleArea.setP1(1, 0);
        triangleArea.setP2(2, 2);
        triangleArea.setP3(0, 1);

        triangleArea.printResult();
    }

    public void setP1(int x1, int y1) {
        this.x1.set(x1);
        this.y1.set(y1);
    }

    public void setP2(int x2, int y2) {
        this.x2.set(x2);
        this.y2.set(y2);
    }

    public void setP3(int x3, int y3) {
        this.x3.set(x3);
        this.y3.set(y3);
    }

    public double getArea() {
        return area.getValue().doubleValue();
    }

    void printResult() {
        output = Bindings.format("For P1(%d,%d), P2(%d,%d), P3(%d,%d), the area of triangle ABC is %.1f",
                x1.getValue(), y1.getValue(), x2.getValue(), y2.getValue(), x3.getValue(), y3.getValue(), getArea());
        System.out.println(output.getValue());
    }

    private void createBinding() {
        NumberBinding x1y2 = Bindings.multiply(x1, y2);
        NumberBinding x1y3 = Bindings.multiply(x1,y3);
        NumberBinding x2y1 = Bindings.multiply(x2,y1);
        NumberBinding x2y3 = Bindings.multiply(x2,y3);
        NumberBinding x3y1 = Bindings.multiply(x3,y1);
        NumberBinding x3y2 = Bindings.multiply(x3,y2);
        NumberBinding x1y2x1y3 = Bindings.subtract(x1y2, x1y3);
        NumberBinding x1y2x1y3x2y3 = Bindings.add(x1y2x1y3, x2y3);
        NumberBinding x1y2x1y3x2y3x2y1 = Bindings.subtract(x1y2x1y3x2y3, x2y1);
        NumberBinding x1y2x1y3x2y3x2y1x3y1 = Bindings.add(x1y2x1y3x2y3x2y1, x3y1);
        NumberBinding result = Bindings.subtract(x1y2x1y3x2y3x2y1x3y1, x3y2);
        NumberBinding nB = Bindings.when(Bindings.lessThan(result, 0)).then(Bindings.negate(result)).otherwise(result);
        area = Bindings.divide(nB,2.0);
    }
}
