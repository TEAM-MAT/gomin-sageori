package MAT.gominsageori.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId = "";

    @Column(nullable = false)
    private String name = "";

    @Column(nullable = false)
    private String email = "";

    @Column(nullable = false)
    private String password = "";

    @Column(nullable = false)
    private String salt = "";

    @ManyToMany
    @JoinColumn( name = "favoriteRestaurant" , nullable = true)
    private List<Restaurant> favoriteRestaurant;

    public String getUserId() {
        if(this.userId != null) {
            return userId;
        }
        else {
            throw new IllegalStateException("No user id data");
        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        if(this.password != null) {
            return password;
        }
        else {
            throw new IllegalStateException("No pwd data");
        }
    }

    public void setPwd(String pwd) {
        this.password = pwd;
    }

    public List<Restaurant> getFavoriteRestaurant() {
        if(this.favoriteRestaurant != null && !this.favoriteRestaurant.isEmpty()) {
            return this.favoriteRestaurant;
        }
        else {
            throw new IllegalStateException("favoriteRestaurant is empty");
        }
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setFavorites(List<Restaurant> favorites){
        this.favoriteRestaurant = favorites;
    }
}
