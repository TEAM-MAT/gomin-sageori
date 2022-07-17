package MAT.gominsageori.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModifyFavoritesParam {
    private Long memberId;
    private List<Long> addFavorites;
    private List<Long> deleteFavorites;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
