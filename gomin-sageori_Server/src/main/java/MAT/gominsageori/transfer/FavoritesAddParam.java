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
    private Long memberId;
    @ApiParam(value = "추가할 favorites 리스트")
    private List<Long> favorites;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<Long> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Long> favorites) {
        this.favorites = favorites;
    }
}
