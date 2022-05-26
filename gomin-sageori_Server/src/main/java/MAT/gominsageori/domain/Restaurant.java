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

    @Column( nullable= false , unique = false , length = 50)
    private String name;

    @Column (nullable = true)
    private Boolean Franchise; // 프랜차이즈 여부

    @Column (nullable = true)
    private Float external_star; //외부 평점

    @Column (nullable = true)
    private Float internal_star; //내부 평점

    @Column (nullable = true)
    private Time startTime; //영업시작시간

    @Column (nullable = true)
    private Time finTime; //영업 종료 시간

    @ManyToOne
    @JoinColumn(name = "bestMenu" , nullable = true)
    private Menu bestMenu;

    @Column (nullable= true)
    private String restaurant_type;

    @Column (nullable = true)
    private String call_number;


    @ManyToMany
    @JoinTable(name = "restaurant_menu")
    private List<Menu> menus = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.REMOVE})
    @JoinColumn(name = "RestaurantAdd")
    private Address address;

    @Column (nullable = true)
    private String business_date;

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
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

    public String getFullAddress(){
        return this.address.getfulladdress();
    }

    public Boolean getFranchise() {
        return Franchise;
    }

    public Menu getBestMenu() {
        return bestMenu;
    }

    public Float getInternal_star(){return internal_star;}

    public Float getExternal_star(){return external_star;}

    public String getBusiness_date(){return business_date;}

    public String getStartTime(){
        if(this.startTime != null){
            return startTime.toString();
        }
        else{
            return "영업 " +
                    "시작 시간 없음";
        }
    }

    public String getFinTime(){
        if(finTime != null){
            return finTime.toString();
        }
        else{
            return "영업 종료 시간 없음";
        }

    }

    public String getCall_number() {
        return call_number;
    }

    public void setFranchise(Boolean franchise) {
        Franchise = franchise;
    }

    public void setBestMenu(Menu bestMenu) {
        this.bestMenu = bestMenu;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
