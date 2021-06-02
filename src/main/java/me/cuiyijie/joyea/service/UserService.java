package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;


public interface UserService {


    JoyeaAccessToken getAccessTokenByTicket(String ticket);

    JoyeaUserProfile getUserInfoByToken(String token);

}
