package MAT.gominsageori.transfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFavoritesParam {
    private Long memberId;
    private String newFavorites;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNewFavorites() {
        return newFavorites;
    }

    public void setNewFavorites(String newFavorites) {
        this.newFavorites = newFavorites;
    }
}
