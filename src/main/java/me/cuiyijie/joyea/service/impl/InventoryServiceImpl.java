package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.InventoryDao;
import me.cuiyijie.joyea.domain.Inventory;
import me.cuiyijie.joyea.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryDao inventoryDao;


    @Override
    public List<Inventory> findAll() {
        return inventoryDao.findAll();
    }
}
