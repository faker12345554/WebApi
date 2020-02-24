package com.admin.admin.service.dw_group;

import com.admin.admin.dao.dw_userpermission.UserPermissionGroupDao;
import com.admin.admin.entity.dw_group.Condition;
import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;

import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.coordina.MenuModel;
import com.admin.model.menu.MenuData;
import com.admin.model.menu.ParentMenu;
import com.admin.model.menu.SonMenu;
import com.admin.page.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private UserPermissionGroupDao GroupDao;

    //新增
    public int saveGroup(UserPermissionGroup UserGroup) {
        UserGroup.setCreatetime(new Date());
        int userGroup=GroupDao.saveUserGroup(UserGroup);
        for (MenuModel item:UserGroup.getMenuList()){
            UserRole userRole=new UserRole();
            userRole.setPermissionid(UserGroup.getPermissionid());
            userRole.setCreatetime(new Date());
            userRole.setMenuid(item.getId());
            userRole.setRolename(item.getName());
            userRole.setStatus(true);
            GroupDao.saveUserRole(userRole);
        }

        return UserGroup.getPermissionid();
    }

    public int selectByName(String Name) {
        return GroupDao.selectByName(Name);
    }

    //修改
    public int updateGroup(UserPermissionGroup UserGroup) {
        UserGroup.setCreatetime(new Date());
        GroupDao.deleteUserRole(UserGroup.getPermissionid());
        for (MenuModel item:UserGroup.getMenuList()){
            UserRole userRole=new UserRole();
            userRole.setPermissionid(UserGroup.getPermissionid());
            userRole.setCreatetime(new Date());
            userRole.setMenuid(item.getId());
            userRole.setRolename(item.getName());
            userRole.setStatus(true);
            GroupDao.saveUserRole(userRole);
        }
        return GroupDao.updateGroup(UserGroup);
    }

    //删除
    public int deleteGroup(boolean flag, int GroupId) {

        GroupDao.DelStatus(flag,GroupId);
        return GroupDao.deleteGroup(flag, GroupId);
    }

    //获取组信息
    public UserPermissionGroup getGroup(int id) {
        UserPermissionGroup userPermissionGroup = GroupDao.getGroup(id);
       // GroupDao.getSomeoneMenuList(id);
        userPermissionGroup.setMenuList(GroupDao.getSomeoneMenuList(id));
        //return GroupDao.getGroup(id);
        return userPermissionGroup;
    }

    //组列表
    public PageBean listGroup(Condition condition) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(condition.getPageIndex(), condition.getPageSize());
        List<UserPermissionGroup> allItems = GroupDao.listGroup(condition);
        PageInfo<UserPermissionGroup> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<UserPermissionGroup> pageData = new PageBean<>(condition.getPageIndex(), condition.getPageSize(), countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        return pageData;
    }

    /**
     * 所以的菜单
     * @return
     */
    public List<MenuModel> GetMenuList(){
        List<MenuModel> Menu = new ArrayList<MenuModel>();
        //菜单
        List<MenuModel> mainList = new ArrayList<MenuModel>();

        List<MenuModel> menuList=GroupDao.GetMenuList();
        for (MenuModel item: menuList){

            if (item.getTopid() == 0) {
                mainList.add(item);
            }
        }
        for (MenuModel item:mainList){
            MenuModel menuModel=new MenuModel();
            menuModel.setId(item.getId());
            menuModel.setName(item.getName());
            List<MenuModel> SubList = new ArrayList<MenuModel>();
            for (MenuModel itam:menuList){
                if (itam.getTopid()==item.getId()){
                    MenuModel model=new MenuModel();
                    model.setId(itam.getId());
                    model.setName(itam.getName());
                    SubList.add(model);
                }
            }
            menuModel.setChildren(SubList);
            Menu.add(menuModel);
        }
        return Menu;
    }

}
