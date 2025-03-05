package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.EasUserDao;
import me.cuiyijie.joyea.model.EasUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EasUserService extends ServiceImpl<EasUserDao, EasUser> {


    public EasUser findByPersonId(String personId) {
        return baseMapper.selectOne(new QueryWrapper<EasUser>().eq("FPERSONID", personId));
    }

    public EasUser findById(String fid) {
        return baseMapper.selectById(fid);
    }

    public EasUser findByNumber(String fNumber) {
        return baseMapper.selectOne(new QueryWrapper<EasUser>().eq("FNUMBER",fNumber));
    }

}
