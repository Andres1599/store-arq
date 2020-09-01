package unis.stores.entities;

import java.util.List;

public class ProductFabric {

    private int id;

    private String name;

    private String description;

    private String partNo;

    private double price;

    private int stock;

    private List<FabricVehicle> vehicles;

    public ProductFabric() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<FabricVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<FabricVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public double getSalePrice() {
        return round(this.price * 1.90, 2);
    }

    public double getValueWithoutIVA() {
        return round(getSalePrice() / 1.12, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
