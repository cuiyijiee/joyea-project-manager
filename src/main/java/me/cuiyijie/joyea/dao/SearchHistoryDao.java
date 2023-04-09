package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.cuiyijie.joyea.model.SearchHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SearchHistoryDao extends BaseMapper<SearchHistory> {


    SearchHistory selectHistory(@Param("easUserId") String easUserId,
                                @Param("searchType") String searchType,
                                @Param("searchKey") String searchKey);

}
