package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Restaurant;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoritesReturnParam {
    @ApiModelProperty(
            name = "FavoritesReturnParam"
    )

    @ApiParam(value = "사용자의 favorites 리스트")
    private List<Restaurant> userfavorites;
    @ApiParam(value = "사용자의 favorites의 수")
    private int count;

    public List<Restaurant> getUserfavorites() {
        return userfavorites;
    }

    public void setUserfavorites(List<Restaurant> userfavorites) {
        this.userfavorites = userfavorites;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
