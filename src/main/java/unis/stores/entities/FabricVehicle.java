package unis.stores.entities;

public class FabricVehicle {

    private String universalCode;

    private String brand;

    private String line;

    private String year;

    public FabricVehicle() {
    }

    public String getUniversalCode() {
        return universalCode;
    }

    public void setUniversalCode(String universalCode) {
        this.universalCode = universalCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vehicle guest = (Vehicle) obj;
        return this.universalCode.equals(guest.getUniversalCode());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((universalCode == null) ? 0 : universalCode.hashCode());
        result = prime * result
                + ((getBrand() == null) ? 0 : getBrand().hashCode());
        result = prime * result
                + ((getLine() == null) ? 0 : getLine().hashCode());
        result = prime * result
                + ((getYear() == null) ? 0 : getYear().hashCode());
        return result;
    }
}
