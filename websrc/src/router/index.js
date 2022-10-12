import Project from '../components/Project';
import Product from "../components/Product";
import Process from "../components/Process";
import CheckItem from "../components/CheckItem";
import CheckItemResult from "../components/CheckItemResult";

import Error from "../view/Error";
import Login from "../view/Login";

import VueRouter from 'vue-router';
import Vue from "vue";

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [{
    path: '/project', name: '项目信息', component: Project
  }, {
    path: '/product', name: '产品信息', component: Product
  }, {
    path: '/process', name: '工序信息', component: Process
  }, {
    path: '/checkItem', name: '点检项信息', component: CheckItem
  }, {
    path: '/checkItemResult', name: '点检项记录', component: CheckItemResult
  }, {
    path: '/error', name: '错误页面', component: Error
  }, {
    path: '/login', name: '登录界面', component: Login
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
