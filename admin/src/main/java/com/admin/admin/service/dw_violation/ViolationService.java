package com.admin.admin.service.dw_violation;

import com.admin.admin.dao.dw_violation.ViolationDao;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.page.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationService {

    @Autowired
    private ViolationDao violationDao;

    /*
    新增或者修改
     */
    public int SaveOrUpdateViolation(Violationfens violationfens){
        if (violationfens.getId()!=0){
            return violationDao.UpdateViolation(violationfens);
        }
        return  violationDao.SaveViolation(violationfens);
    }

    /*
    删除
     */
    public int deleteViolation(int id){
        return violationDao.deleteViolation(id);
    }

    /*
    查看
     */
    public Violationfens getViolation(int id){
        return violationDao.selectViolation(id);
    }
    /*
    列表
     */

    public PageBean<Violationfens> ListViolation(int PageSize,int PageIndex){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<Violationfens> allItems = violationDao.ListViolation();
        PageInfo<Violationfens> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<Violationfens> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        //我系统封装了一个 直接丢给你试试

        return pageData;
    }

}
