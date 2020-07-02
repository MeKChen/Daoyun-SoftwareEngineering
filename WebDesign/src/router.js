import Vue from 'vue'
import Router from 'vue-router'
import Container from './views/HeadContainer.vue'
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
      path: '/login', 
      name: 'login',
      component: () => import('./views/login/Login.vue'),
      hidden: true,
      meta: {title: '到云', authFilter: false}
    },
    {
      path: '/',
      component: Container,
      redirect: 'home',
      hidden: false,
      children: [{
          path: '/home',
          component: () => import('./views/home/Home.vue'),
          name: 'home',
          hidden: false,
          meta: {title: '首页', icon: 'all', noCache: true, authFilter: false}
      }]
    },
    {
      path: '/basic',
      component: Container,
      hidden: false,
      children: [{
          path: '/userManage/index',
          component: () => import('./views/baseInfo/userManage/UserIndex.vue'),
          name: 'userManage',
          hidden: false,
          meta: {title: '用户管理', noCache: true, icon: 'account'}
      }]
    }, {
      path: '/systemManage',
      component: Container,
      hidden: false,
      meta: {title: '系统管理', icon: 'viewlist', noCache: true},
      name: 'systemManage',
      children: [{
          path: '/systemManage/menu',
          name: 'menuManage',
          hidden: false,
          meta: {title: '菜单管理', noCache: true},
          component: () => import('./views/menuManage/MenuIndex.vue')
      }, {
          path: '/dataDictionary/index',
          name: 'dataDictionary',
          hidden: false,
          meta: {title: '数据字典', noCache: true, icon: 'similar-product'},
          component: () => import('./views/dataDictionary/DictionaryContainer.vue')
      }, {
          path: '/roleManage/index',
          name: 'roleManage',
          hidden: false,
          meta: {title: '角色管理', noCache: true, icon: 'edit'},
          component: () => import('./views/powerMain/roleMain/RoleMain.vue')
      }]
    },
    {
      path: '/errPage',
      component: Container,
      hidden: false,
      meta: {title: '异常页', icon: 'rfq', noCache: true},
      children: [{
          path: '/errPage/404',
          component: () => import('./views/errPage/404.vue'),
          name: 'errPage404',
          hidden: false,
          meta: {title: '找不到页面', noCache: true}
      }, {
          path: '/errPage/403',
          name: 'errPage403',
          hidden: false,
          meta: {title: '无权访问', noCache: true},
          component: () => import('./views/errPage/403.vue')
      }, {
          path: '/errPage/500',
          name: 'errPage500',
          hidden: false,
          meta: {title: '服务器出错', noCache: true},
          component: () => import('./views/errPage/500.vue')
      }]
    }, {
      path: '*',
      name: 'Page404',
      hidden: true,
      meta: {title: '找不到页面', authFilter: false},
      component: () => import('./views/errPage/404')
    }
  ]
});
