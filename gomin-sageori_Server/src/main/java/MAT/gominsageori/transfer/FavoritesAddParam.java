package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoritesAddParam {
    @ApiModelProperty(
            name = "FavoritesAddParam"
    )

    @ApiParam(value = "유저id")
    private String userId;
    @ApiParam(value = "추가할 favorites 리스트")
    private List<Long> favorites;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Long> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Long> favorites) {
        this.favorites = favorites;
    }
}
