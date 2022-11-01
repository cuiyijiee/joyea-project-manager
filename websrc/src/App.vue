<template>
  <div id="app">
    <router-view/>
    <van-image-preview v-model="showImagePreview" :startPosition="imagePreviewStartIndex"
                       :images="imagePreviewImages.map(item => item.url)" @change="onChange">
      <template v-slot:cover>
        <div>
          <p>文件名:{{ fileName.length === 0 ? '暂未设置' : fileName }}</p>
          <!--          <van-button>显示原图</van-button>-->
        </div>
      </template>
    </van-image-preview>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import store from "../src/store";

export default {
  name: 'App',
  data(){
    return{
      fileName:""
    }
  },
  methods:{
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
}
</style>
