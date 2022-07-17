package MAT.gominsageori.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddDeleteFavoritesParam {
    private Long memberId;
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
