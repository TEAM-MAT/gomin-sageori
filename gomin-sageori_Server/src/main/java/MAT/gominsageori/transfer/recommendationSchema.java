package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Schema(description = "레스토랑 추천 응답DTO.")
@Getter
@Setter
public class recommendationSchema {
    @Schema(description = "전체 추천목록 사이즈")
    private int size;

    @Schema(description = "추천 레스토랑 상호명, 주소,id 리스트 각 정보는 배열[0].address와 같이 접근.")
    private ArrayList<HashMap<String, Object>> restaurants = new ArrayList<>();

    public void setSize(int size) {
        this.size = size;
    }

    public void addRestaurant(HashMap restaurant) {
        restaurants.add(restaurant);
    }
}
