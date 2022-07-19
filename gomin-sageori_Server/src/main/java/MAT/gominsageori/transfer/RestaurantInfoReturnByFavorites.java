package MAT.gominsageori.transfer;

public class RestaurantInfoReturnByFavorites {
    private Long id;
    private String name;
    private String naverMapUrl = "";

    public RestaurantInfoReturnByFavorites(Long id, String name, String naverMapUrl) {
        this.id = id;
        this.name = name;
        this.naverMapUrl = naverMapUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNaverMapUrl() {
        return naverMapUrl;
    }

    public void setNaverMapUrl(String naverMapUrl) {
        this.naverMapUrl = naverMapUrl;
    }
}
