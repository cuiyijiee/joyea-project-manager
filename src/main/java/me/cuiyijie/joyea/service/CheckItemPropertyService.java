package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.CheckItemPropertyDao;
import me.cuiyijie.joyea.enums.CheckCategoryType;
import me.cuiyijie.joyea.enums.CheckItemPropertyType;
import me.cuiyijie.joyea.enums.CheckModuleType;
import me.cuiyijie.joyea.enums.CheckStageType;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/26 14:25
 */
@Service
public class CheckItemPropertyService {

    private final Logger logger = LoggerFactory.getLogger(CheckItemPropertyService.class);


    @Autowired
    private CheckItemPropertyDao checkItemPropertyDao;

    public Map<CheckItemPropertyType, List<Enum<?>>> getCheckItemProperty(Integer checkItemId, CheckItemPropertyType propertyType) {
        Map<CheckItemPropertyType, List<Enum<?>>> resultMap = new HashMap<>();
        List<Enum<?>> categoryTypes = new ArrayList<>();
        List<Enum<?>> stageTypes = new ArrayList<>();
        List<Enum<?>> moduleTypes = new ArrayList<>();
        try {
            CheckItemProperty checkItemProperty = new CheckItemProperty();
            checkItemProperty.setCheckItemId(checkItemId);
            if (propertyType != null) {
                checkItemProperty.setPropertyType(propertyType);
            }
            List<CheckItemProperty> checkItemProperties = checkItemPropertyDao.list(checkItemProperty);
            for (int index = 0; index < checkItemProperties.size(); index++) {
                CheckItemProperty temp = checkItemProperties.get(index);
                switch (temp.getPropertyType()) {
                    case CATEGORY:
                        categoryTypes.add(CheckCategoryType.valueOf(temp.getPropertyValue()));
                        break;
                    case STAGE:
                        stageTypes.add(CheckStageType.valueOf(temp.getPropertyValue()));
                        break;
                    case MODULE:
                        moduleTypes.add(CheckModuleType.valueOf(temp.getPropertyValue()));
                        break;
                }
            }
        } catch (Exception exception) {
            logger.error("获取点检项属性失败：", exception);
        }
        resultMap.put(CheckItemPropertyType.CATEGORY, categoryTypes);
        resultMap.put(CheckItemPropertyType.STAGE, stageTypes);
        resultMap.put(CheckItemPropertyType.MODULE, moduleTypes);
        return resultMap;
    }

    public void updateCheckItemProperty(CheckItem checkItem) {
        List<CheckItemProperty> properties = new ArrayList<>();

    }
}
