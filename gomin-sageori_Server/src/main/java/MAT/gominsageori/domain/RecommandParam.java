package MAT.gominsageori.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecommandParam {
    @ApiModelProperty(
            name = "recommandParam",
            example = "?characteristic=spicy,soup&location=숭실대&franchise=true&allergic=메밀,땅콩"
    )
    @ApiParam(value = "ex)?characteristic=soup,spicy / 가능 값 : [soup,spicy,sweet,hot,meat,noodle,rice,bread]")
    private List<String> characteristic;

    @ApiParam(value = "신촌/서울대입구/숭실대")
    private String location;

    @ApiParam(value = "true/false")
    private Boolean franchise = false;

    //private String atmosphere; //분위기

    //@ApiParam(value = "메밀/밀/대두/땅콩/호두/잣/아황산류/복숭아/토마토/난류/우유/새우/고등어/오징어/게/조개류/돼지고기/소고기/닭고기")
    //private List<String> allergic;

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

    /*public List<String> getAllergic() {
        return allergic;
    }

    public void setAllergic(List<String> allergic) {
        this.allergic = allergic;
    }
     */
}