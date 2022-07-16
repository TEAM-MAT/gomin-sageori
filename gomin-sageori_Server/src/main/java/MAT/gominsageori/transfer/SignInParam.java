package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInParam {
    @ApiModelProperty(
            name = "signInParameter"
    )
    @ApiParam(value = "유저id")
    private String userId;

    @ApiParam(value = "유저 password")
    private String password;
}
