<template>
  <div id="app">
    <router-view/>
    <van-image-preview v-model="showImagePreview" :startPosition="imagePreviewStartIndex"
                       :images="imagePreviewImages.map(item => item.previewUrl)" @change="onChange"/>
    <video-dialog ref="videoDialog"/>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

import VideoDialog from "./components/VideoDialog.vue";

export default {
  name: 'App',
  components:{
    VideoDialog
  },
  data() {
    return {
      title: ""
    }
  },
  methods: {
    onChange(index) {
      this.index = index;
      this.title = this.imagePreviewImages[parseInt(index)].title;
    },
  },
  created() {
    this.$store.state.imagePreviewShow = false;
  },
  computed: {
    ...mapGetters([
      'imagePreviewImages', 'imagePreviewStartIndex'
    ]),
    showImagePreview: {
      get: function () {
        return this.$store.state.imagePreviewShow;
      },
      set: function (newValue) {
        this.$store.state.imagePreviewShow = newValue;
      }
    }
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100vh;
}
</style>
