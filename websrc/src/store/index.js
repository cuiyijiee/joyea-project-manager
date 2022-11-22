import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
    imagePreviewShow: false,
    imagePreviewImages: [],
    imagePreviewStartIndex: 0
  }, getters: {
    getToken(state) {
      return state.token;
    }, imagePreviewShow: function (state) {
      return state.imagePreviewShow;
    }, imagePreviewImages: function (state) {
      return state.imagePreviewImages;
    }, imagePreviewStartIndex: function (state) {
      return state.imagePreviewStartIndex;
    },
  }, mutations: {
    setToken(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
    }, delToken(state) {
      state.token = '';
      localStorage.removeItem("token");
    }
  }, actions: {
    setToken(state, token) {
      state.commit("setToken", token)
    }
  }
})

export default store;
