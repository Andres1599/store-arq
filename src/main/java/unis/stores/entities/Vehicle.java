package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "vehicle")
public class Vehicle {

    /**
     * The id of the vehicle
     */
    @Id
    @Column(name = "universal_code", nullable = false)
    @JsonProperty("universalCode")
    private String universalCode;

    /**
     * The brand of the vehicle
     */
    @OneToOne
    private Brand brand;

    /**
     * The vehicle of the vehicle
     */
    @OneToOne
    private Line line;

    /**
     * The year of the vehicle
     */
    @Column(name = "year")
    private String year;

    public Vehicle() {
    }

    /**
     * Returns the universalCode of the vehicle
     *
     * @return    the vehicle universalCode.
     */
    public String getUniversalCode() {
        return universalCode;
    }

    /**
     * Sets the universalCode to the vehicle
     *
     * @param     universalCode the value we want be the universalCode of the vehicle.
     */
    public void setUniversalCode(String universalCode) {
        this.universalCode = universalCode;
    }

    /**
     * Returns the year of the vehicle
     *
     * @return    the vehicle year.
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year to the vehicle
     *
     * @param     year the value we want be the year of the vehicle.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Returns the brand of the vehicle
     *
     * @return    the vehicle brand.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Sets the brand to the vehicle
     *
     * @param     brand the value we want be the brand of the vehicle.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Returns the line of the vehicle
     *
     * @return    the vehicle line.
     */
    public Line getLine() {
        return line;
    }

    /**
     * Sets the line to the vehicle
     *
     * @param     line the value we want be the line of the vehicle.
     */
    public void setLine(Line line) {
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
