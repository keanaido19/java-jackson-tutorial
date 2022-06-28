package de.wethinkco.jackson;

public class Dealership {
    private int price;
    private Car car;

    public Dealership() {
    }

    public Dealership(int price, Car car) {
        this.price = price;
        this.car = car;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
