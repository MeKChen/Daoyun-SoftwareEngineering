import Vue from 'vue'
import Router from 'vue-router'
import Container from './views/Container.vue'
import menuAPI from './api/manage/menuMainAPI'

Vue.use(Router);

const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [
    {
      path: "/",
      //redirect: "login",
      redirect: "login", 
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
      component: () => import('./views/login/Login.vue'),
    },
    /**
     * 用户信息
     */
    {
      path: "/home",
      name: "Home",
      redirect: "stuIndex",
      meta: {
        name: "用户信息",
        icon: "el-icon-user"
      },
      component: Container,
      children: [
        {
          path: "/stuIndex",
          name: "StuIndex",
          meta: {
            name: "学生信息"
          },
          component: () => import('./views/menuManage/MenuIndex.vue')
        },
        {
          path: "/teaIndex",
          name: "TeaIndex",
          meta: {
            name: "教师信息"
          },
          component: () => import('./views/dataDictionary/DictionaryContainer.vue')
        }
      ]
    },

  ]
});
