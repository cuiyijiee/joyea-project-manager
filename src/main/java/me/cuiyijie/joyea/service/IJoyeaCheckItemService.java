package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaCheckItem;

import java.util.List;

public interface IJoyeaCheckItemService {

    List<JoyeaCheckItem> findByOperationIdAndNo(String operationId, String operationNo);

    Integer add(JoyeaCheckItem joyeaCheckItem);

    Integer update(JoyeaCheckItem joyeaCheckItem);

    Integer delete(JoyeaCheckItem joyeaCheckItem);
}
