/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *

 */
public class Car implements Comparable<Car> {

    private String carID;//Unique identifier of Car
    private Brand brand;//Brand
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return "< " + carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID + " >";
    }

    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) {
            return d;
        }
        // they are in the same brand, comparing based on their ID
        return this.carID.compareTo(c.carID);
    }

    public String screenString() {
        return "< " + brand.getBrandName() + "\n " + carID + ", " + color + ", " + frameID + ", " + engineID + " >";
    }

}
