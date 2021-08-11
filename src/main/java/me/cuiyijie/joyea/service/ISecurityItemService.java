package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.SecurityItem;

public interface ISecurityItemService {

    SecurityItem find(String projectNumber);

    Integer add(SecurityItem joyeaCheckItem);

    Integer update(SecurityItem joyeaCheckItem);

    Integer delete(SecurityItem joyeaCheckItem);

}
