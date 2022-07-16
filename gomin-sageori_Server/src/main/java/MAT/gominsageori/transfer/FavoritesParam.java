package MAT.gominsageori.transfer;

import MAT.gominsageori.domain.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoritesParam {
    private List<Restaurant> userfavorites;
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
