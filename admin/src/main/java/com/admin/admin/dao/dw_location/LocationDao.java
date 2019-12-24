package com.admin.admin.dao.dw_location;

import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.model.Execl.ExeclModel;
import com.admin.model.location.LocationModel;
import com.admin.model.search.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationDao {
    //定位记录列表
    List<LocationModel> listLocationModel(@Param("condition") String condition );
    // 今日轨迹
    List<Locationmation> ListLocation(@Param("PersonId") String PersonId,@Param("date") String date);
    //历史轨迹
    List<Locationmation> HistoricalTrack(SearchModel searchModel);
    //查看定位信息
    Locationmation GetLocation(@Param("id") int id);


        /*
    查看当前位置
     */
    Locationmation GetLocationByPerson(@Param("PersonId") String PersonId);




}
