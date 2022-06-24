package MAT.gominsageori.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @ManyToMany
    @JoinColumn( name = "favoriteRestaurant" , nullable = true)
    private List<Restaurant> favoriteRestaurant;

    public String getPid() {
        if(this.pid != null) {
            return pid;
        }
        else {
            throw new IllegalStateException("No pid data");
        }
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getId() {
        if(this.id != null) {
            return id;
        }
        else {
            throw new IllegalStateException("No id data");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        if(this.name != null) {
            return name;
        }
        else {
            throw new IllegalStateException("No name data");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        if(this.pwd != null) {
            return pwd;
        }
        else {
            throw new IllegalStateException("No pwd data");
        }
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Restaurant> getFavoriteRestaurant() {
        if(this.favoriteRestaurant != null && !this.favoriteRestaurant.isEmpty()) {
            return this.favoriteRestaurant;
        }
        else {
            throw new IllegalStateException("favoriteRestaurant is empty");
        }
    }

    public void SetFavorites(List<Restaurant> favorites){
        this.favoriteRestaurant = favorites;
    }
}
