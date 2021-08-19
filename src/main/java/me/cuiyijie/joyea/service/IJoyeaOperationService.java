package me.cuiyijie.joyea.service;

import jdk.nashorn.internal.scripts.JO;
import me.cuiyijie.joyea.domain.JoyeaOperation;

import java.util.List;

public interface IJoyeaOperationService {

    List<JoyeaOperation> findByManufacturerOrderId(String manufactureId);

    List<JoyeaOperation> findAll(JoyeaOperation joyeaOperation);

}
