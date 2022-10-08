import Project from '../components/Project';
import Product from "../components/Product";
import Process from "../components/Process";
import CheckItem from "../components/CheckItem";
import CheckItemResult from "../components/CheckItemResult";

import VueRouter from 'vue-router';
import Vue from "vue";

Vue.use(VueRouter);

export default new VueRouter({
  routes: [{
    path: '/', name: '项目信息', component: Project
  }, {
    path: '/product', name: '产品信息', component: Product
  }, {
    path: '/process', name: '工序信息', component: Process
  }, {
    path: '/checkItem', name: '点检项信息', component: CheckItem
  }, {
    path: '/checkItemResult', name: '点检项记录', component: CheckItemResult
  }]
})
