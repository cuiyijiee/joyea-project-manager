<template>
  <div class="process-card">
    <div class="container">
      <van-row>
        <van-col span="24"><span class="desc">工序编号：</span>{{ item.processNumber || '' }}</van-col>
        <van-col span="24"><span class="desc">工序名称：</span>{{ item.processName || '' }}</van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProcessCard",
  props: {
    item: {
      type: Object,
    }
  },
  data() {
    return {
    }
  },
  methods: {},
  mounted() {
  }
}
</script>

<style scoped>
div.process-card {
  margin: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

div.container {
  padding: 10px;
}

.van-progress {
  margin: 10px 0;
}

span.desc {
  color: dimgray;
}
</style>
