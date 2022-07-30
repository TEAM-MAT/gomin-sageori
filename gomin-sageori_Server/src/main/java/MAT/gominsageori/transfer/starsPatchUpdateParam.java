package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class starsPatchUpdateParam {
    @ApiModelProperty(
            name = "별점 업데이트 body",
            example = ""
    )

    @ApiParam( name = "userId")
    private String userId;

    @ApiParam( name = "stars" )
    private Float stars;
}
