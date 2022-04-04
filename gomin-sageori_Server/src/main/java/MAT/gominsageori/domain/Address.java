package MAT.gominsageori.domain;

import javax.persistence.*;

@Entity
@Table(name = "R_Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressnum;

    @OneToOne
    @JoinColumn( name = "restaurant" , nullable = false)
    private Restaurant restaurant;

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

    public Long getId(){
        return this.addressnum;
    }

    public Restaurant getRestaurant(){
        return this.restaurant;
    }

    public String getfulladdress(){
        return this.City + this.district + this.road + this.addressnum + this.floor;
    }

    public String getCity(){
        return this.City;
    }

    public String getDistrict(){
        return this.district;
    }
}
