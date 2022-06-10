package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Schema(description = "레스토랑 추천 응답DTO. 상호명과 주소는 서로 매칭됨.")
@Getter
@Setter
public class recommendationSchema {
    @Schema(description = "전체 추천목록 사이즈")
    private int size;

    @Schema(description = "추천 레스토랑 상호명 리스트")
    private ArrayList<String> name = new ArrayList<>();

    @Schema(description = "추천 레스토랑 주소 리스트.")
    private ArrayList<Address> address = new ArrayList<>();

    public void setSize(int size) {
        this.size = size;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }
}
