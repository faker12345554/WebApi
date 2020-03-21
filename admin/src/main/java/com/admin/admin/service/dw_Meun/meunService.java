package com.admin.admin.service.dw_Meun;

import com.admin.admin.dao.master.dw_meun.MeunDao;
import com.admin.admin.entity.dw_menu.Menu;
import com.admin.page.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class meunService {

    @Autowired
    private MeunDao meunDao;

    public PageBean GetMenuList(int PageSize, int PageIndex){//PageBean

        PageHelper.startPage(PageIndex, PageSize);
        List<Menu> allItems = meunDao.GetMenuList();
        PageInfo<Menu> info = new PageInfo<>(allItems);
        int countNums = (int) info.getTotal();
        PageBean<Menu> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());
        pageData.setItems(allItems);
        return pageData;

      /*  PageHelper.startPage(PageIndex, PageSize);
        List<User> allItems = userDao.listUser(userName,phone,status);
        PageInfo<User> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<User> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        return pageData;*/

       // return meunDao.GetMenuList( PageSize, PageIndex);

    }

    public int delMeun(boolean flag,int id){
        return meunDao.delMeun(flag,id);
    }

    public Menu GetMeunbyid(int id){
        return meunDao.GetMeunbyid(id);
    }
}
