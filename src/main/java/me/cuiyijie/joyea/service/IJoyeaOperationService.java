package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaOperation;

import java.util.List;

public interface IJoyeaOperationService {

    List<JoyeaOperation> findByManufacturerOrderId(String manufactureId);

}
