package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
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
    private Float internalStar; //내부 평점

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
        if(this.address != null){
            return address;
        }
        else{
            throw new IllegalStateException("No Address data");
        }
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFullAddress() {
        if(this.address != null) {
            return this.address.getFullAddress();
        }
        else {
            return "주소 정보 없음";
        }
    }

    public Boolean getFranchise() {
        if(this.franchise != null) {
            return franchise;
        }
        else {
            return false;
        }
    }

    public Menu getBestMenu() {
        if(this.bestMenu != null) {
            return bestMenu;
        }
        else {
            throw new IllegalStateException("No menu data");
        }
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
        if(this.businessDate != null) {
            return businessDate;
        }
        else {
            return "영업일 정보 없음";
        }
    }

    public String getStartTime() {
        if(this.startTime != null) {
            return startTime.toString();
        }
        else {
            return "영업 시작 시간 없음";
        }
    }

    public String getFinTime() {
        if(this.finTime != null) {
            return finTime.toString();
        }
        else {
            return "영업 종료 시간 없음";
        }
    }

    public String getCallNumber() {
        if(this.callNumber != null) {
            return callNumber;
        }
        else {
            return "전화번호 없음";
        }
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
