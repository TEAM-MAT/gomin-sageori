package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoritesModifyParam {
    @ApiModelProperty(
            name = "FavoritesModifyParam"
    )

    @ApiParam(value = "유저id")
    private String userId;
    @ApiParam(value = "추가할 favorites 리스트")
    private List<Long> addFavorites;
    @ApiParam(value = "제거할 favorites 리스트")
    private List<Long> deleteFavorites;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Long> getAddFavorites() {
        return addFavorites;
    }

    public void setAddFavorites(List<Long> addFavorites) {
        this.addFavorites = addFavorites;
    }

    public List<Long> getDeleteFavorites() {
        return deleteFavorites;
    }

    public void setDeleteFavorites(List<Long> deleteFavorites) {
        this.deleteFavorites = deleteFavorites;
    }
}
