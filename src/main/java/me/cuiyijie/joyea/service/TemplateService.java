package me.cuiyijie.joyea.service;

import io.swagger.models.auth.In;
import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateDao templateDao;

    public List<Template> listAll(){
        return templateDao.listAll();
    }

    public Integer update(Template template){
        return templateDao.update(template);
    }

    public Integer insert(Template template){
        return templateDao.insert(template);
    }

    public Integer delete(Template template){
        return templateDao.delete(template);
    }
}
