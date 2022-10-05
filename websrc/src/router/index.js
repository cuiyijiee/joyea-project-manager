import Vue from 'vue'
import Router from 'vue-router'
import Project from '@/components/Project'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '项目信息',
      component: Project
    }
  ]
})
