package MAT.gominsageori.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @ManyToMany
    @JoinColumn( name = "favoriteRestaurant" , nullable = true)
    private List<Restaurant> favoriteRestaurant;

    public String getPid(){ return pid; }

    public void setPid(String pid){ this.pid = pid; }

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Restaurant> getFavoriteRestaurant(){
        return this.favoriteRestaurant;
    }

    public void SetFavorites(List<Restaurant> favorites){
        this.favoriteRestaurant = favorites;
    }
}
