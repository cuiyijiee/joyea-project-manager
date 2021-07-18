package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.pojo.NextPlusTenant;

import java.util.List;

public interface INextPlusService {

    String getAccessToken();

    NextPlusTenant getTenantInfo();

    void sendNotify(List<String> ids, String content);
}
