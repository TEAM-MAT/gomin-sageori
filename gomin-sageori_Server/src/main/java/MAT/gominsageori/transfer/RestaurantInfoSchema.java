package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Address;
import MAT.gominsageori.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "식당 정보 리턴 DTO")
@Setter
@Getter
public class RestaurantInfoSchema {
    @Schema(description = "식당 이름")
    private String name;

    @Schema(description = "식당 외부평점")
    private Float externalStar;

    @Schema(description = "식당 내부평점")
    private Float internalStar;

    @Schema(description = "영업 시작시간")
    private String startTime;

    @Schema(description = "영업 종료시간")
    private String finTime;

    @Schema(description = "영업 요일")
    private String businessDay;

    @Schema(description = "식당 전화번호")
    private String callNumber;

    @Schema(description = "대표 메뉴")
    private String bestMenu;

    @Schema(description = "주소")
    private Address address;

    @Schema(description = "이미지 개수, 식당id_이미지번호.jpg로 접근하면됨.")
    private int imageCount;

    public void setRiSchemaFromRestaurant(Restaurant restaurant){
        try{
            this.name = restaurant.getName();
            this.externalStar = restaurant.getExternalStar();
            this.internalStar = restaurant.getInternalStar();
            this.businessDay = restaurant.getBusinessDate();
            this.startTime = restaurant.getStartTime();
            this.finTime = restaurant.getFinTime();
            this.callNumber = restaurant.getCallNumber();
            this.bestMenu = restaurant.getBestMenu().getName();
            this.address = restaurant.getAddress();
            this.imageCount = restaurant.getImageCount();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
