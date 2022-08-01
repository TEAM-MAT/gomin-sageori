package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false , unique = false , length = 50)
    private String name;

    @Column(nullable = true)
    private Boolean franchise; // 프랜차이즈 여부

    @Column(nullable = true)
    private Float externalStar; //외부 평점

    @Column(nullable = true)
    private Float internalStar= 0.0F; //내부 평점

    @Column(nullable = true)
    private Time startTime; //영업시작시간

    @Column(nullable = true)
    private Time finTime; //영업 종료 시간

    @Column(nullable = true)
    private String businessDate;

    @ManyToOne
    @JoinColumn(name = "bestMenu" , nullable = true)
    private Menu bestMenu;

    @Column(nullable= true)
    private String restaurantType;

    @Column(nullable = true)
    private String callNumber;

    @ManyToMany
    @JoinTable(name = "restaurant_menu")
    private List<Menu> menus = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.REFRESH , CascadeType.REMOVE})
    @JoinColumn(name = "RestaurantAdd")
    private Address address;

    @Column(nullable = true)
    private String naverMapUrl = "";

    @Column(nullable = true)
    private int imageCount = 0;


    public String getRestaurantType() {
        return restaurantType;
    }

    public String getNaverMapUrl() {
        return naverMapUrl;
    }

    public int getImageCount() {
        return imageCount;
    }

    public Long getId(){
        if(this.id != null){
            return id;
        }
        else{
            throw new IllegalStateException("No id data");
        }
    }

    public String getName(){
        if(this.name != null){
            return name;
        }
        else{
            throw new IllegalStateException("No name data");
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFullAddress() {
        return this.address.getFullAddress();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Boolean getFranchise() {
        if(this.franchise != null) {
            return franchise;
        }
        else {
            return false;
        }
    }

    public Menu getBestMenu(){
        return bestMenu;
    }

    public Float getInternalStar() {
        if(this.internalStar != null) {
            return internalStar;
        }
        else {
            return 0F;
        }
    }

    public Float getExternalStar() {
        if(this.externalStar != null) {
            return externalStar;
        }
        else {
            return 0F;
        }
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public String getFinTime() {
        return finTime.toString();
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setFranchise(Boolean franchise) {
        this.franchise = franchise;
    }

    public void setBestMenu(Menu bestMenu) {
        this.bestMenu = bestMenu;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

}
