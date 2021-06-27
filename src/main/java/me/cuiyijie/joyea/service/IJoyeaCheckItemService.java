package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaCheckItem;

import java.util.List;

public interface IJoyeaCheckItemService {

    List<JoyeaCheckItem> findByOperationIdAndNo(String operationId, String operationNo);
}
