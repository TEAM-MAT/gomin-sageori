package MAT.gominsageori.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @Column(name = "menu_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private boolean isSoup;

    @Column (nullable = true)
    private boolean isSpicy;

    @Column (nullable = true)
    private boolean isSweet;

    @Column (nullable = true)
    private boolean isHot;

    @Column (nullable = true)
    private boolean isMeat;

    @Column (nullable = true)
    private boolean isNoodle;

    @Column (nullable = true)
    private boolean isRice;

    @Column (nullable = true)
    private boolean isBread;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSoup() {
        return isSoup;
    }

    public void setSoup(boolean soup) {
        isSoup = soup;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public boolean isSweet() {
        return isSweet;
    }

    public void setSweet(boolean sweet) {
        isSweet = sweet;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isMeat() {
        return isMeat;
    }

    public void setMeat(boolean meat) {
        isMeat = meat;
    }

    public boolean isNoodle() {
        return isNoodle;
    }

    public void setNoodle(boolean noodle) {
        isNoodle = noodle;
    }

    public boolean isRice() {
        return isRice;
    }

    public void setRice(boolean rice) {
        isRice = rice;
    }

    public boolean isBread() {
        return isBread;
    }

    public void setBread(boolean bread) {
        isBread = bread;
    }
}
