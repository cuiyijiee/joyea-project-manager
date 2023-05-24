// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import Vant from 'vant';
import 'vant/lib/index.css';
import "./style/basic.css"

Vue.use(Vant);

import App from './App'
import router from './router'
import store from "./store";

import Video from 'video.js'
import video_zhCN from 'video.js/dist/lang/zh-CN.json'
import video_en from 'video.js/dist/lang/en.json'
Video.addLanguage('zh-CN', video_zhCN)
Video.addLanguage('en', video_en)

import 'video.js/dist/video-js.css'

Vue.config.productionTip = false;

import eruda from 'eruda'

// // if (process.env.NODE_ENV !== 'production') {
//   eruda.init();
// // }

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: {App},
  template: '<App/>'
})
