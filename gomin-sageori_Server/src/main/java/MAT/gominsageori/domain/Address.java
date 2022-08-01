package MAT.gominsageori.domain;

import javax.persistence.*;

@Entity
@Table(name = "R_Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressNum;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String road;

    @Column
    private String buildingNumber;

    @Column
    private String floor;
    
    @Column
    private String location; //설입,숭입,신촌 단위 구분

    public Long getId() {
        if(this.addressNum != null) {
            return addressNum;
        }
        else {
            throw new IllegalStateException("No id data");
        }
    }

    public String getFullAddress() {
        if(this.city == null && this.district == null && this.road == null && this.buildingNumber == null && this.floor == null) {
            return null;
        }
        else {
            if (this.city != null) {
                city = "";
            }
            if (this.district != null) {
                district = "";
            }
            if (this.road != null) {
                road = "";
            }
            if (this.buildingNumber != null) {
                buildingNumber = "";
            }
            if (this.floor != null) {
                floor = "";
            }
            return this.city + this.district + this.road + this.buildingNumber + this.floor;
        }
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }
    public String getLocation() {
        return location;
    }

    public String getRoad() {
        return road;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getFloor() {
        return floor;
    }

    public String setCity(String city) {
        this.city = city;
        return city;
    }

    public String setDistrict(String district) {
        this.district = district;
        return district;
    }

    public String setLocation(String location) {
        this.location = location;
        return location;
    }

    public String setRoad (String road) {
        this.road = road;
        return road;
    }

    public String setBuildingNum(String num) {
        this.buildingNumber = num;
        return num;
    }

    public String setFloor(String floor) {
        this.floor = floor;
        return floor;
    }
}
