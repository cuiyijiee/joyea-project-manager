<template>
  <van-dialog v-model="videoDialogVisible" :title="toPlayVideo.title"
              @opened="handleOpenDialog" @close="handleCloseVideo">
    <video ref="previewVideo" class="video-js vjs-fluid vjs-16-9 video-box"
           oncontextmenu="return false">
      <p class="vjs-no-js">请升级你的浏览器观看视频</p>
    </video>
  </van-dialog>
</template>

<script>

import videojs from 'video.js'

let videoLoading = require("../assets/video-loading.jpg")
export default {
  name: "VideoPlayer",
  data() {
    return {
      initStatus: 0, //0-未初始化，1-初始化中，2-初始化完成
      checkInitRobot: null,
      player: null,
      toPlayVideo: {
        title: "测试视频",
        url: "https://s3.meituan.net/v1/mss_80d691367d3045158769d28878d5cfd6/merchant-video/7d01905a16f4a6e0db4b4ac2894b743a.mp4"
      },
    }
  },
  methods: {
    init() {
      this.initStatus = 1;
      const context = this;
      if (this.player == null) {
        videojs(this.$refs.previewVideo, {
          controls: true, // 是否显示控制条
          preload: 'auto',
          autoplay: false,
          fluid: true, // 自适应宽高
          language: 'zh-CN', // 设置语言
          muted: false, // 是否静音
          inactivityTimeout: false,
          poster: videoLoading,
          controlBar: { // 设置控制条组件
            children: [
              {name: 'playToggle'}, // 播放按钮
              {name: 'currentTimeDisplay'}, // 当前已播放时间
              {name: 'progressControl'}, // 播放进度条
              {name: 'durationDisplay'}, // 总时间
              {
                name: 'volumePanel', // 音量控制
                inline: false, // 不使用水平方式
              },
              {name: 'FullscreenToggle'} // 全屏
            ]
          },
          sources: []
        }, function () {
          context.initStatus = 2;
          context.player = this;
        });
      }
    },
    handleOpenDialog() {
      this.toPlayVideo.title = this.$store.state.videoDialogTitle;
      this.toPlayVideo.url = this.$store.state.videoDialogUrl;

      this.checkInitRobot = setInterval(() => {
        console.log("start check init!")
        if (this.initStatus === 0) {
          console.log("start init!")
          this.init();
        } else if (this.initStatus === 2) {
          console.log("inited")
          if (this.checkInitRobot) {
            clearInterval(this.checkInitRobot);
          }
          console.log("play")
          this.startPlayVideo(this.$store.state.videoDialogUrl);
        }
      }, 1000)
    },
    handleCloseVideo() {
      if (this.player != null) {
        this.player.reset();
        this.player.pause();
      }
    },
    startPlayVideo(videoUrl) {
      const data = {
        src: videoUrl,
        type: 'video/mp4'
      };
      this.player.pause();
      this.player.src(data);
      this.player.load(data);
      this.player.play();
    }
  },
  computed: {
    videoDialogVisible: {
      get: function () {
        return this.$store.state.videoDialogShow;
      },
      set: function (newValue) {
        this.$store.state.videoDialogShow = newValue;
      }
    }
  }
}
</script>

<style scoped>

</style>
