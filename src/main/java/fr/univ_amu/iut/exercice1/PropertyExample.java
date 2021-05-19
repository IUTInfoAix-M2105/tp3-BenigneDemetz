package fr.univ_amu.iut.exercice1;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class PropertyExample {
    private ChangeListener changeListener;
    private InvalidationListener invalidationListener;

    private IntegerProperty anIntProperty;

    public static void main(String[] args) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createProperty();
        propertyExample.addAndRemoveInvalidationListener();
        propertyExample.addAndRemoveChangeListener();
    }

    void createProperty() {
        setAnInt(1024);
        System.out.println();
        System.out.println("anIntProperty = " + anIntProperty());
        System.out.println("anIntProperty.get() = " + getAnInt());
        System.out.println("anIntProperty.getValue() = " + getAnInt());

    }

    void addAndRemoveInvalidationListener() {
        System.out.println();
        System.out.println("Add invalidation listener.");
        invalidationListener = observable -> System.out.println("The observable has been invalidated.");
        anIntProperty.addListener(invalidationListener);
        System.out.println("setValue() with 1024.");
        anIntProperty.setValue(1024);
        System.out.println("set() with 2105.");
        anIntProperty.set(2105);
        System.out.println("setValue() with 5012.");
        anIntProperty.setValue(5012);
        System.out.println("Remove invalidation listener.");
        invalidationListener = null;
        System.out.println("set() with 1024.");
        anIntProperty.set(1024);

    }

    void addAndRemoveChangeListener() {
        throw new RuntimeException("Not yet implemented !");
    }


    public int getAnInt() {
        return anIntProperty.get();
    }

    public void setAnInt(int anInt) {
        if (anIntProperty == null)
            anIntProperty = new SimpleIntegerProperty();
        this.anIntProperty.set(anInt);
    }

    public IntegerProperty anIntProperty() {
        return anIntProperty;
    }

}