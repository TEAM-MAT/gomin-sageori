package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecommandParam {
    private List<String> characteristic;
    private String location;
    private Boolean franchise;
    //private String atmosphere; //분위기
    private List<String> allergic;

    public List<String> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(List<String> characteristic) {
        this.characteristic = characteristic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getFranchise() {
        return franchise;
    }

    public void setFranchise(Boolean franchise) {
        this.franchise = franchise;
    }

    /*public String getAtmosphere() {
        return atmosphere;
    }*/

   /* public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }*/

    public List<String> getAllergic() {
        return allergic;
    }

    public void setAllergic(List<String> allergic) {
        this.allergic = allergic;
    }
}
