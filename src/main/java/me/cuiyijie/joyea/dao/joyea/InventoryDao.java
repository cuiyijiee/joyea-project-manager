package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryDao {

    List<Inventory> findAll();

}

