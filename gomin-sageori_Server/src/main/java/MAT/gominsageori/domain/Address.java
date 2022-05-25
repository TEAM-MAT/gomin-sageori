package MAT.gominsageori.domain;

import javax.persistence.*;

@Entity
@Table(name = "R_Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressnum;
    /*
    @OneToOne
    @JoinColumn( name = "address_restaurant" , nullable = false)
    private Restaurant restaurant;

     */

    @Column
    private String City;

    @Column
    private String district;

    @Column
    private String road;

    @Column
    private String building_number;

    @Column
    private String floor;
    
    @Column
    private String location; //설입,숭입,신촌 단위 구분

    public Long getId(){
        return this.addressnum;
    }
    /*
    public Restaurant getRestaurant(){
        return this.restaurant;
    }

     */

    public String getfulladdress(){
        return this.City + this.district + this.road + this.addressnum + this.floor;
    }

    public String getCity(){
        return this.City;
    }

    public String getDistrict(){
        return this.district;
    }
    public String getLocation(){ return this.location; }

    public String getRoad(){return this.road;}

    public String getBuilding_number(){return this.building_number;}

    public String getFloor(){return this.floor;}

    /*
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

     */

    public String setCity(String city){
        this.City = city;
        return city;
    }

    public String setDistrict(String district){
        this.district = district;
        return district;
    }

    public String setLocation(String location){
        this.location = location;
        return location;
    }

    public String setRoad (String road){
        this.road = road;
        return road;
    }

    public String setbuildingnum(String num){
        this.building_number = num;
        return num;
    }

    public String setFloor(String floor){
        this.floor = floor;
        return floor;
    }

}
