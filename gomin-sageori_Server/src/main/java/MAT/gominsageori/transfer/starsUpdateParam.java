package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class starsUpdateParam {
    @ApiModelProperty(
            name = "별점 업데이트 body",
            example = ""
    )

    @ApiParam( name = "식당Id")
    private Long id;

    @ApiParam( name = "별점" )
    private Float stars;
}
