package com.admin.admin.service.dw_userrole;

import com.admin.admin.dao.dw_userrole.UserRoleDao;
import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.menu.MenuData;
import com.admin.model.menu.ParentMenu;
import com.admin.model.menu.SonMenu;
import com.admin.model.userrole.UserRoleModel;
import com.admin.page.PageBean;
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


    //新增
    public int saveUserRole(UserRole userRole) {

        return userRoleDao.saveUserRole(userRole);
    }

    //修改
    public int updateUserRole(UserRole userRole) {

        return userRoleDao.updateUserRole(userRole);
    }

    //删除
    public int deleteUserRole(boolean flag, int UserRoleId) {
        return userRoleDao.deleteUserRole(flag, UserRoleId);
    }

    //获取所以菜单
    public List<ParentMenu> listMenu(int UserId) {

        List<ParentMenu> Menu = new ArrayList<ParentMenu>();
        //菜单
        List<Menu> MenuList = userRoleDao.listMenu(UserId);

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
                    MenuData data = new MenuData(Me.getMenuname(), Me.getAffix(), Me.isHidden());
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

        return Menu;
    }


    //获取用户拥有的权限
    public PageBean listUserRole(int id, int PageSize, int PageIndex) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<UserRoleModel> allItems = userRoleDao.listUserRole(id);
        PageInfo<UserRoleModel> info = new PageInfo<>(allItems);//全部记录
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<UserRoleModel> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);


        return pageData;

    }

}
