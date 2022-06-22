package MAT.gominsageori.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @ManyToMany
    @JoinColumn( name = "favoriteRestaurant" , nullable = true)
    private List<Restaurant> favoriteRestaurant;

    public String getUserId(){ return userId; }

    public void setUserId(String userId){ this.userId = userId; }

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

    public String getEmail() {return this.email;}

    public void setEmail(String email) {this.email = email;}

    public String getPwd() {
        return password;
    }

    public void setPwd(String pwd) {
        this.password = pwd;
    }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    public List<Restaurant> getFavoriteRestaurant(){
        return this.favoriteRestaurant;
    }

    public void SetFavorites(List<Restaurant> favorites){
        this.favoriteRestaurant = favorites;
    }
}
