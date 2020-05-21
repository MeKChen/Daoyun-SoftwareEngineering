import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

import Layout from '@/views/Layout/index.vue'

export default new Router({
  routes: [
    {
      path: "/",
      //redirect: "login",
      redirect: "console",
      hidden: true,
      meta: {
        name: "主页"
      }
    },
    {
      path: "/login",
      name: "Login",
      hidden: true,
      meta: {
        name: "登录"
      },
      component: () => import("../views/Login/index.vue")
    },
    /**
     * 系统控制
     */
    {
      path: "/console",
      name: "Console",
      redirect: "userManage",
      meta: {
        name: "系统管理",
        icon: "el-icon-setting"
      },
      component: Layout,
      children: [
        {
          path: "/userManage",
          name: "UserManage",
          meta: {
            name: "用户管理"
          },
          component: () => import("../views/System/index.vue")
        },
        {
          path: "/menuManage",
          name: "MenuManage",
          meta: {
            name: "菜单管理"
          },
          component: () => import("../views/System/menu.vue")
        },
        {
          path: "/roleManage",
          name: "RoleManage",
          meta: {
            name: "角色管理"
          },
          component: () => import("../views/System/role.vue")
        },
        {
          path: "/systemPara",
          name: "SystemPara",
          meta: {
            name: "系统参数"
          },
          component: () => import("../views/System/para.vue")
        },
        {
          path: "/fabric",
          name: "Fabric",
          meta: {
            name: "学校组织结构"
          },
          component: () => import("../views/System/fabric.vue")
        }
      ]
    },
    /**
     * 用户信息
     */
    {
      path: "/userInfo",
      name: "UserInfo",
      redirect: "stuIndex",
      meta: {
        name: "用户信息",
        icon: "el-icon-user"
      },
      component: Layout,
      children: [
        {
          path: "/stuIndex",
          name: "StuIndex",
          meta: {
            name: "学生信息"
          },
          component: () => import("../views/User/index.vue")
        },
        {
          path: "/teaIndex",
          name: "TeaIndex",
          meta: {
            name: "教师信息"
          },
          component: () => import("../views/User/teacher.vue")
        }
      ]
    },
    /**
     * 签到管理
     */
    {
      path: "/attendanceManage",
      name: "AttendanceManage",
      //redirect: "attendanceInfo",
      meta: {
        name: "签到管理",
        icon: "el-icon-edit"
      },
      component: Layout,
      children: [
        {
          path: "/attendanceInfo",
          name: "AttendanceInfo",
          meta: {
            name: "签到信息"
          },
          component: () => import("../views/Attendance/index.vue")
        },
        {
          path: "/attendance",
          name: "Attendance",
          meta: {
            name: "课程签到"
          },
          component: () => import("../views/Attendance/attend.vue")
        }
      ]
    },
    /**
     * 角色分配
     */
    {
      path: "/role",
      name: "Role",
      meta: {
        name: "角色分配",
        icon: "el-icon-s-custom"
      },
      component: Layout,
      children: [
        {
          path: "/power",
          name: "Power",
          meta: {
            name: "权限分配"
          },
          component: () => import("../views/Power/index.vue")
        }
      ]
    },
 
    /**
     * 异常页面
     */
    {
      path: "/abnormal",
      name: "Abnormal",
      redirect: "abnoraml_404",
      meta: {
        name: "异常页面",
        icon: "el-icon-magic-stick"
      },
      component: Layout,
      children: [
        {
          path: "/abnormal_404",
          name: "Abnormal_404",
          meta: {
            name: "404"
          },
          component: () => import("../views/Abnormal/404.vue")
        },
        {
          path: "/abnormal_403",
          name: "Abnormal_403",
          meta: {
            name: "403"
          },
          component: () => import("../views/Abnormal/403.vue")
        },
        {
          path: "/abnormal_500",
          name: "Abnormal_500",
          meta: {
            name: "500"
          },
          component: () => import("../views/Abnormal/500.vue")
        },
      ]
    },
    /**
     * 数据字典
     */
    {
      path: "/data_dictionary",
      name: "Data_dictionary",
      redirect: "data_dict",
      meta: {
        name: "数据字典",
        icon: "el-icon-user"
      },
      component: Layout,
      children: [
        {
          path: "/data_dict",
          name: "Data_dict",
          meta: {
            name: "数据字典"
          },
          component: () => import("../views/Data_dict/index.vue")
        },
      ]
    },
  ]
});
