package com.admin.page;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class PageUtil {

    /*
    分页
     */
    public static PageBean PageList(List<T> DataList , int PageIndex, int PageSize){
        PageHelper.startPage(PageIndex, PageSize);
        PageInfo<T> info = new PageInfo<>(DataList);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<T> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(DataList);
        return pageData;
    }
}
