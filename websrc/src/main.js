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

Vue.config.productionTip = false;

import VConsole from 'vconsole'

if (process.env.NODE_ENV !== 'production') {
  new VConsole()
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: {App},
  template: '<App/>'
})
