package me.cuiyijie.joyea.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2021/7/11 20:35
 */
@Data
public class NextPlusAccessTokenResp {

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("token_type")
    private String tokenType;

    @JsonAlias("expires_in")
    private String expiresIn;

    private String scope;
}
