package MAT.gominsageori.transfer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpParam {
    @ApiModelProperty(
            name = "signUpParameter"
    )
    @ApiParam(value = "name")
    private String name;

    @ApiParam(value = "password")
    private String password;

    @ApiParam(value = "email")
    private String email;
}
