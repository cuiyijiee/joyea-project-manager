import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
  },
  getters: {
    getToken(state) {
      return state.token;
    }
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
    },
    delToken(state) {
      state.token = '';
      localStorage.removeItem("token");
    }
  },
  actions: {
    setToken(state, token) {
      state.commit("setToken", token)
    }
  }
})

export default store;
