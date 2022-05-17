package MAT.gominsageori.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isSoup = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isSpicy = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isSweet = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isHot = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isMeat = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isNoodle = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isRice = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBread = false;

    /*
    @Column(columnDefinition = "boolean default false")
    private Boolean hasBuckwheat = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasWheat = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasSoybean = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasPeanut = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasWalnut = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasPineNut = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasSulFurousAcid = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasPeach = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasTomato = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasEgg = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasMilk = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasSquid = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasMackerel = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasShrimp = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasCrab = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasClam = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasPork = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasBeef = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasChicken = false;

     */
    @ManyToMany(mappedBy = "menus")
    private List<Restaurant> restaurants = new ArrayList<>();

    public Long getId() {
        return id;
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
/*
    public boolean isHasBuckwheat() {
        return hasBuckwheat;
    }

    public void setHasBuckwheat(boolean hasBuckwheat) {
        this.hasBuckwheat = hasBuckwheat;
    }

    public boolean isHasWheat() {
        return hasWheat;
    }

    public void setHasWheat(boolean hasWheat) {
        this.hasWheat = hasWheat;
    }

    public boolean isHasSoybean() {
        return hasSoybean;
    }

    public void setHasSoybean(boolean hasSoybean) {
        this.hasSoybean = hasSoybean;
    }

    public boolean isHasPeanut() {
        return hasPeanut;
    }

    public void setHasPeanut(boolean hasPeanut) {
        this.hasPeanut = hasPeanut;
    }

    public boolean isHasWalnut() {
        return hasWalnut;
    }

    public void setHasWalnut(boolean hasWalnut) {
        this.hasWalnut = hasWalnut;
    }

    public boolean isHasPineNut() {
        return hasPineNut;
    }

    public void setHasPineNut(boolean hasPineNut) {
        this.hasPineNut = hasPineNut;
    }

    public boolean isHasSulFurousAcid() {
        return hasSulFurousAcid;
    }

    public void setHasSulFurousAcid(boolean hasSulFurousAcid) {
        this.hasSulFurousAcid = hasSulFurousAcid;
    }

    public boolean isHasPeach() {
        return hasPeach;
    }

    public void setHasPeach(boolean hasPeach) {
        this.hasPeach = hasPeach;
    }

    public boolean isHasTomato() {
        return hasTomato;
    }

    public void setHasTomato(boolean hasTomato) {
        this.hasTomato = hasTomato;
    }

    public boolean isHasEgg() {
        return hasEgg;
    }

    public void setHasEgg(boolean hasEgg) {
        this.hasEgg = hasEgg;
    }

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public boolean isHasSquid() {
        return hasSquid;
    }

    public void setHasSquid(boolean hasSquid) {
        this.hasSquid = hasSquid;
    }

    public boolean isHasMackerel() {
        return hasMackerel;
    }

    public void setHasMackerel(boolean hasMackerel) {
        this.hasMackerel = hasMackerel;
    }

    public boolean isHasShrimp() {
        return hasShrimp;
    }

    public void setHasShrimp(boolean hasShrimp) {
        this.hasShrimp = hasShrimp;
    }

    public boolean isHasCrab() {
        return hasCrab;
    }

    public void setHasCrab(boolean hasCrab) {
        this.hasCrab = hasCrab;
    }

    public boolean isHasClam() {
        return hasClam;
    }

    public void setHasClam(boolean hasClam) {
        this.hasClam = hasClam;
    }

    public boolean isHasPork() {
        return hasPork;
    }

    public void setHasPork(boolean hasPork) {
        this.hasPork = hasPork;
    }

    public boolean isHasBeef() {
        return hasBeef;
    }

    public void setHasBeef(boolean hasBeef) {
        this.hasBeef = hasBeef;
    }

    public boolean isHasChicken() {
        return hasChicken;
    }

    public void setHasChicken(boolean hasChicken) {
        this.hasChicken = hasChicken;
    }

 */
}
