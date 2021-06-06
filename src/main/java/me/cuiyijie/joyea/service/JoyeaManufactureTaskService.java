package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.JoyeaManufactureTask;

import java.util.List;

public interface JoyeaManufactureTaskService {

    List<JoyeaManufactureTask> findByManufacturerOrderId(String manufactureId);

}
