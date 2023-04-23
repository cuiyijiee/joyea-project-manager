<template>
  <div id="app">
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"/>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"/>
    <van-image-preview v-model="showImagePreview" :startPosition="imagePreviewStartIndex"
                       :images="imagePreviewImages.map(item => item.url)" @change="onChange"/>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

export default {
  name: 'App',
  data() {
    return {
      fileName: ""
    }
  },
  methods: {
    onChange(index) {
      this.index = index;
      this.fileName = this.imagePreviewImages[parseInt(index)].fileName;
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
