package com.admin.admin.dao.master.dw_sing;

import com.admin.admin.entity.dw_sing.SinginInformation;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SinginDao {
    /*
    列表
     */
    List<SinginModel> ListSingin(@Param("searchModel") SearchModel searchModel,@Param("type") int type,@Param("limit") String limit);

    /*
    查看
     */
    SinginInformation getSingin(@Param("id") int Id);

    /*
    音视频管理
     */
    List<SinginModel> ListAudio(@Param("searchModel") SearchModel searchModel,@Param("type") int type,@Param("limit") String limit);







}
