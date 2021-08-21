package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.SecurityItem;

import java.util.List;

public interface ISecurityItemService {

    List<SecurityItem> find(String projectNumber);

    Integer add(SecurityItem joyeaCheckItem);

    Integer update(SecurityItem joyeaCheckItem);

    Integer delete(SecurityItem joyeaCheckItem);

}
