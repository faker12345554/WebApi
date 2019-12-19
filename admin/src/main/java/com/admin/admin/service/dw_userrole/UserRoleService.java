package com.admin.admin.service.dw_userrole;

import com.admin.admin.dao.dw_userrole.UserRoleDao;
import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.menu.MenuData;
import com.admin.model.menu.ParentMenu;
import com.admin.model.menu.SonMenu;
import com.admin.model.userrole.UserRoleModel;
import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    private ResponseResult result = new ResponseResult();

    //新增
    public ResponseResult saveUserRole(UserRole userRole) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleDao.saveUserRole(userRole));
    }

    //修改
    public ResponseResult updateUserRole(UserRole userRole) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleDao.updateUserRole(userRole));
    }

    //删除
    public ResponseResult deleteUserRole(boolean flag, int UserRoleId) {
        if (flag == true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(userRoleDao.deleteUserRole(flag, UserRoleId));
    }

    //获取所以菜单
    public ResponseResult<Menu> listMenu(int UserId) {

        List<ParentMenu> Menu = new ArrayList<ParentMenu>();
        //菜单
        List<Menu> MenuList = userRoleDao.listMenu(UserId);
        if (MenuList.isEmpty()) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("该用户没有任何权限,请联系管理员");
        }
        List<Menu> mainList = new ArrayList<Menu>();
        for (Menu item : MenuList) {
            if (item.getTopid() == 0) {
                mainList.add(item);
            }
        }

        for (Menu item : mainList) {
            ParentMenu model = new ParentMenu();
            MenuData Title = new MenuData(item.getIcon());
//            Title.setTitle(item.getMenuname());
           // Title.setIcon(item.getIcon());
            model.setPath(item.getPath());
            model.setComponent(item.getComponent());
            model.setRedirect(item.getRedirect());
            model.setName(item.getName());
            model.setMeta(Title);

            List<SonMenu> SubList = new ArrayList<SonMenu>();
            for (Menu Me : MenuList) {
                if (Me.getTopid() == item.getMenu_id()) {
                    MenuData data = new MenuData(Me.getName(),Me.getAffix());
                    SonMenu Sub = new SonMenu();
                    Sub.setPath(Me.getPath());
                    Sub.setComponent(Me.getComponent());
                    Sub.setName(Me.getName());
//                    data.setTitle(Me.getMenuname());
//                    data.setAffix(Me.getAffix());
//                    data.setIcon(Me.getIcon());
                    Sub.setMeta(data);
                    SubList.add(Sub);
                }

            }
            model.setChildren(SubList);

            Menu.add(model);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(Menu);
    }


    //获取用户拥有的权限
    public ResponseResult listUserRole(int id, int PageSize, int PageIndex) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<UserRoleModel> allItems = userRoleDao.listUserRole(id);
        PageInfo<UserRoleModel> info = new PageInfo<>(allItems);//全部记录
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<UserRoleModel> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageData);

    }

}
