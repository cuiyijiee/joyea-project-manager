import Project from '@/components/Project';
import VueRouter from 'vue-router';
import Vue from "vue";

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/',
      name: '项目信息',
      component: Project
    }
  ]
})
