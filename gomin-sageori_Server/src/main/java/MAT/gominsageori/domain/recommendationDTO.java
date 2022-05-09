package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class recommendationDTO {
    private int size;

    private ArrayList<String> name = new ArrayList<>();

    private ArrayList<String> address = new ArrayList<>();
}
