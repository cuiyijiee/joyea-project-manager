import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
    imagePreviewShow: false,
    imagePreviewImages: [],
    imagePreviewStartIndex: 0,
    videoDialogShow: false,
    videoDialogTitle: "",
    videoDialogUrl: ""
  }, getters: {
    getToken(state) {
      return state.token;
    }, imagePreviewShow: function (state) {
      return state.imagePreviewShow;
    }, imagePreviewImages: function (state) {
      return state.imagePreviewImages;
    }, imagePreviewStartIndex: function (state) {
      return state.imagePreviewStartIndex;
    }, videoDialogShow: function (state) {
      return state.videoDialogShow;
    }, videoDialogTitle: function (state) {
      return state.videoDialogTitle;
    }, videoDialogUrl: function (state) {
      return state.videoDialogUrl;
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
