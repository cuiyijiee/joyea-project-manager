import Project from '../components/Project';
import ProjectCollect from '../components/ProjectCollect';
import Product from "../components/Product";
import Process from "../components/Process";
import CheckItem from "../components/CheckItem";
import CheckItemResult from "../components/CheckItemResult";

import Error from "../view/Error";
import Login from "../view/Login";
import ELogin from "../view/ELogin";

import VueRouter from 'vue-router';
import Vue from "vue";

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [{
    path: '/project', name: '项目信息', component: Project, meta: {
      keepAlive: true
    }
  }, {
    path: '/projectCollect', name: '我的收藏项目', component: ProjectCollect, meta: {
      keepAlive: true
    }
  }, {
    path: '/product', name: '产品信息', component: Product, meta: {
      keepAlive: true
    }
  }, {
    path: '/process', name: '工序信息', component: Process, meta: {
      keepAlive: true
    }
  }, {
    path: '/checkItem', name: '点检项信息', component: CheckItem, meta: {
      keepAlive: true
    }
  }, {
    path: '/checkItemResult', name: '点检项记录', component: CheckItemResult, meta: {
      keepAlive: true
    }
  }, {
    path: '/error', name: '错误页面', component: Error
  }, {
    path: '/login', name: '登录界面', component: Login
  }, {
    path: '/elogin', name: '范围登录界面', component: ELogin
  }]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('token');
    if (token === 'null' || token === '') {
      next('/login');
    } else {
      next();
    }
  }
})

export default router;
