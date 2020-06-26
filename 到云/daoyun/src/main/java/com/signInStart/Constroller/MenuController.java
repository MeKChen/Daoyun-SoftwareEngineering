package com.signInStart.Constroller;

import com.signInStart.Entity.BaseClass.*;
import com.signInStart.Entity.Menu;
import com.signInStart.Entity.RoleMenu;
import com.signInStart.Service.LoginInfoService;
import com.signInStart.Service.MenuService;
import com.signInStart.Service.RoleMenuService;
import com.signInStart.Utils.ResultUtils;
import com.sun.org.apache.regexp.internal.RE;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    LoginInfoService loginInfoService;
    /**
     * @Author ypp
     * @Description //TODO 获取所有菜单
     * @Param []
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public DataResult findAll() throws FriendlyException {
        List<Menu> all = menuService.findAll();
        return ResultUtils.success(all, all.size());
    }

    /**
     * 添加菜单，设置菜单权限
     *
     * @param menu
     * @param menu
     * @return
     */
    @RequestMapping(value = "/roleMenu", method = RequestMethod.POST)
    @Auth(BaseSetting.NOUSER)
    public DataResult add(Menu menu) throws FriendlyException  {
        menuService.addMenu(menu);
        return ResultUtils.success();
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return
     */
    @RequestMapping(value = "/roleMenu", method = RequestMethod.DELETE)
    public DataResult remove(@RequestParam("id") Long id) throws FriendlyException  {
        if (loginInfoService.checkUser()) {
            return ResultUtils.error(2, "没有权限");
        }
        Integer res = menuService.remove(id);
        if (res == 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error(1, "删除对象不存在");
    }

    /**
     * @Author ypp
     * @Description //TODO  删除某个角色的菜单权限 ，根据具体的角色来删除
     * @Param [menuId, userType]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public DataResult delRoleMenu(@RequestParam("menuId") Long menuId, @RequestParam("roleId") Long roleId)throws FriendlyException  {
        if (loginInfoService.checkUser()) {
            return ResultUtils.error(2, "没有权限");
        }
        roleMenuService.deleteByMenuIdRoleId(menuId, roleId);
        return ResultUtils.success();
    }

    /**
     * @Author ypp
     * @Description //TODO  删除某个角色的菜单权限，根据角色类型来删除
     * @Param [menuId, userType]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/userType", method = RequestMethod.DELETE)
    public DataResult delRoleMenu(@RequestParam("menuId") Long menuId, @RequestParam("userType") String userType)throws FriendlyException  {
        if (loginInfoService.checkUser()) {
            return ResultUtils.error(2, "没有权限");
        }
        roleMenuService.deleteByMenuIdUserType(menuId, userType);
        return ResultUtils.success();
    }

    /**
     * 修改菜单（废弃）
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/roleMenu", method = RequestMethod.PUT)
    public DataResult modify(Menu menu, @RequestParam("userTypes") String[] userTypes)throws FriendlyException  {
        if (loginInfoService.checkUser()) {
            return ResultUtils.error(2, "没有权限");
        }
        Integer res = menuService.modify(menu);
        if (res == 0) {
            roleMenuService.modify(menu, userTypes);
            return ResultUtils.success();
        }
        String[] msg = {"对象不能为空", "该URL已经被占用"};
        return ResultUtils.error(res, msg[res - 1]);
    }
    /**
     * @Author ypp
     * @Description //TODO  根据传进来的菜单id，获取该菜单所分配的角色
     * @Param [id]
     * @return com.signInStart.Entity.BaseClass.DataResult  角色名和userType
     **/
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public DataResult getMenuRole(@RequestParam("id") Long id)throws FriendlyException  {
        Menu byId = menuService.findById(id);
        return ResultUtils.success(byId.getRoles(), byId.getRoles()==null?0:byId.getRoles().size() );
    }

    /**
     * 获取所有根菜单
     *
     * @return
     */
    @RequestMapping(value = "/allRootMenu", method = RequestMethod.GET)
    public DataResult findAllRootMenu()throws FriendlyException  {
        List<Map<String, String>> allRootMenu = menuService.findAllRootMenu();
        return ResultUtils.success(allRootMenu, allRootMenu.size());
    }

    /**
     * 获取侧边栏
     * 二级侧边栏
     * @return
     */
//    @RequestMapping(value = "/sidebar", method = RequestMethod.GET)
//    public DataResult getSideBar() throws FriendlyException {
//        List<Menu> sidebar = menuService.getSidebar();
//        return ResultUtils.success(sidebar);
//    }
    /**
     * @Author ypp
     * @Description //TODO  菜单树
     * @Param []
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    public DataResult getMenuTree() throws FriendlyException {
        return ResultUtils.success(menuService.getMenuTree());
    }
    /**
     * @Author ypp
     * @Description //TODO  根据角色类型（userType）返回拥有的菜单
     * @Param [userType]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/menuList", method = RequestMethod.GET)
    public DataResult getMenuByUerType(@RequestParam("id") String userType) throws FriendlyException {
        return ResultUtils.success(menuService.getMenuByUserType(userType));
    }
    /**
     * @Author ypp
     * @Description //TODO  根据角色id获取菜单列表
     * @Params [userType]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    public DataResult getMenuByRoleID(@RequestParam("id") Long userType) throws FriendlyException {
        return ResultUtils.success(menuService.getMenuByRoleID(userType));
    }


    /**
     * @Author ypp
     * @Description //TODO  根据菜单代码获取用户列表
     * [menuValue]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/userTypeList", method = RequestMethod.GET)
    public DataResult getUserTypeList(@RequestParam("id")String menuValue) throws FriendlyException{
        return ResultUtils.success(menuService.getUserTypeByMenuValue(menuValue));
    }

    /**
     * @Author ypp
     * @Description //TODO  根据菜单代码获取菜单信息
     * @Param [userType]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/MenuInfo",method = RequestMethod.GET)
    public DataResult getMenuInfoByMenuValue(@RequestParam("menuValue") String menuValue) throws FriendlyException{
        return ResultUtils.success(menuService.getMenuInfoByMenuValue(menuValue));
    }
    /**
     * @Author ypp
     * @Description //TODO  添加菜单角色
     * @Params [menuValue, roleID]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/MenuRole", method = RequestMethod.POST)
    public DataResult addMenuRole(@RequestParam("menuValue")String[] menuValue, @RequestParam("roleID")Long roleID) throws FriendlyException{
        menuService.addMenuRole(menuValue, roleID);
        return ResultUtils.success();
    }
    /**
     * @Author ypp
     * @Description //TODO  获取所有菜单
     * @Params []
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/MenuList/All")
    public DataResult findAllMenu(){
        List<Map<String, String>> allMenuList = menuService.findAllMenuList();
        return ResultUtils.success(allMenuList,allMenuList.size());
    }
    /**
     * @Author ypp
     * @Description //TODO  修改菜单
     * @Params [menu]
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
    @RequestMapping(value = "/menuInfo", method = RequestMethod.PUT)
    @Auth(BaseSetting.NOUSER)
    public DataResult modifyMenu(Menu menu) throws FriendlyException {
        menuService.modify(menu);
        return ResultUtils.success("修改菜单成功");
    }
    /**
     * @Author ypp
     * @Description //TODO  根据登陆用户获取左侧栏(废弃)
     * @Params []
     * @return com.signInStart.Entity.BaseClass.DataResult
     **/
//    @RequestMapping("/sider")
//    public DataResult getSider() throws FriendlyException {
//        List<Menu> sidebar = menuService.getSidebar();
//        return ResultUtils.success(sidebar,sidebar.size());
//    }


}