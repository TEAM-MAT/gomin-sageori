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
    private String atmosphere; //분위기
    private List<String> allergic;
}
