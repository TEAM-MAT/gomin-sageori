package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.Date.*;

@Getter
@Setter
public class RestaurantInfoSchema {
    @Schema(description = "식당 이름")
    private String name;

    @Schema(description = "식당 외부평점")
    private Float ex_star;

    @Schema(description = "식당 내부평점")
    private Float in_star;

    @Schema(description = "영업 시작시간")
    private String startTime;

    @Schema(description = "영업 종료시간")
    private String finTime;

    @Schema(description = "영업 요일")
    private String business_day;

    @Schema(description = "식당 전화번호")
    private String callNumber;

    @Schema(description = "대표 메뉴")
    private String bestMenu;

    @Schema(description = "주소")
    private String address;


    public void setRIschemaFromRestaurant(Restaurant restaurant){
        try{
            this.name = restaurant.getName();
            this.ex_star = restaurant.getExternal_star();
            this.in_star = restaurant.getInternal_star();
            this.business_day = restaurant.getBusiness_date();
            this.startTime = restaurant.getStartTime().toString();
            this.finTime = restaurant.getFinTime().toString();
            this.callNumber = restaurant.getCall_number();
            this.bestMenu = restaurant.getBestMenu().getName();
            this.address = restaurant.getFullAddress();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
