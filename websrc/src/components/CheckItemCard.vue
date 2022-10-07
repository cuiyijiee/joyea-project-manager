<template>
  <div class="check-item-card">
    <div class="container">
      <van-row>
        <van-col span="24"><span class="desc">检验类型：</span>{{ item.typeName || '' }}</van-col>
        <van-col span="24"><span class="desc">
          <van-icon v-if="item.keyItem === 1" name="star"/>
<!--          <van-icon v-else name="star-o"/>-->
          检验标准：</span>{{ item.checkStandard || '' }}
        </van-col>
      </van-row>
      <van-row>
        <van-col span="24"><span class="desc">检验方法：</span>{{ item.typeName1 || 'XXX' }}</van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "CheckItemCard",
  props: {
    item: {
      type: Object,
    }
  },
  data() {
    return {}
  },
  methods: {},
  mounted() {
  }
}
</script>

<style scoped>
div.check-item-card {
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
