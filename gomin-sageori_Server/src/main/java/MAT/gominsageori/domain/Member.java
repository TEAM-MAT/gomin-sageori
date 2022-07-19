package MAT.gominsageori.domain;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "member")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String userId = "";

    @Column(nullable = false)
    private String name = "";

    @Column(nullable = false,unique = true)
    private String email = "";

    @Column(nullable = false)
    private String password = "";

    @Column(nullable = false)
    private String salt = "";

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ManyToMany
    @JoinColumn( name = "favoriteRestaurant" , nullable = true)
    private Set<Restaurant> favoriteRestaurant;

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

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPwd(String pwd) {
        this.password = pwd;
    }

    public Set<Restaurant> getFavoriteRestaurant() {
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

    public void setFavorites(Set<Restaurant> favorites){
        this.favoriteRestaurant = favorites;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getRoles() {
        return this.roles;
    }
}
