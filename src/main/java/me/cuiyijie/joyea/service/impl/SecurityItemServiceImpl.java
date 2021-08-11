package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.SecurityItemDao;
import me.cuiyijie.joyea.domain.SecurityItem;
import me.cuiyijie.joyea.service.ISecurityItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityItemServiceImpl implements ISecurityItemService {


    @Autowired
    private SecurityItemDao securityItemDao;

    @Override
    public SecurityItem find(String projectNumber) {
        SecurityItem securityItem = new SecurityItem();
        securityItem.setProjectNumber(projectNumber);
        return securityItemDao.get(securityItem);
    }

    @Override
    public Integer add(SecurityItem securityItem) {
        return securityItemDao.insert(securityItem);
    }

    @Override
    public Integer update(SecurityItem securityItem) {
        SecurityItem existed = securityItemDao.get(securityItem);
        if (existed == null) {
            return add(securityItem);
        } else {
            return securityItemDao.update(securityItem);
        }
    }

    @Override
    public Integer delete(SecurityItem securityItem) {
        return securityItemDao.delete(securityItem.getProjectNumber());
    }
}
