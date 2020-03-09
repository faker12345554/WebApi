package com.admin.admin.service.dw_location;

import com.admin.admin.dao.dw_location.LocationDao;
import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.model.Execl.ExeclModel;
import com.admin.model.location.LocationModel;
import com.admin.model.search.SearchModel;
import com.admin.page.PageBean;
import com.admin.tool.CacheUtils;
import com.admin.tool.JudgementRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;


    /*
    查询定位信息
     */
    public PageBean listLocationModel(String Condition ,int PageSize,int PageIndex){
        String limit="";
        int type= JudgementRole.Distinguishroles();
        if (type==1){
            limit= CacheUtils.get("accountname").toString();
        }else{
            limit=CacheUtils.get("PoliceCode").toString();
        }
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);
        List<LocationModel> allItems = locationDao.listLocationModel(Condition,type,limit);
        PageInfo<LocationModel> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<LocationModel> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        return pageData;
    }

    /*
    查看今日轨迹 去调用一下ListLocation这个方法
     */
    public List<Locationmation> ListLocation(String PersonId,String date){
        return locationDao.ListLocation(PersonId,date);
        }

    /*
    导出 历史轨迹信息
     */
    public List<Locationmation> HistoricalTrack( SearchModel searchModel){

        return locationDao.HistoricalTrack(searchModel);
    }

    /*
    查看定位信息
     */
    public Locationmation GetLocation(int id){

        return locationDao.GetLocation(id);
    }


    /*
    查看当前定位信息
     */
    public Locationmation GetLocationByPerson(String PersonId){

        return locationDao.GetLocationByPerson(PersonId);
    }
}
