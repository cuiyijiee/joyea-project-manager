package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.pojo.request.TransCheckItemRequest;

import java.util.List;

public interface IJoyeaCheckItemService {

    PageInfo<JoyeaCheckItem> findByOperationIdAndNo(TransCheckItemRequest transCheckItemRequest);

    Integer add(JoyeaCheckItem joyeaCheckItem);

    Integer update(JoyeaCheckItem joyeaCheckItem);

    Integer delete(JoyeaCheckItem joyeaCheckItem);
}
