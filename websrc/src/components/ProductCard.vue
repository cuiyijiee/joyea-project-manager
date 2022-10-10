<template>
  <div class="product-card">
    <div class="container">
      <van-row>
        <van-col span="24"><span class="desc">产品编号：</span>{{ item.productNumber || '' }}</van-col>
        <van-col span="24"><span class="desc">生产单号：</span>{{ item.orderNumber || '' }}</van-col>
      </van-row>
      <van-divider></van-divider>
      <van-row>
        <van-col span="4"></van-col>
        <van-col span="10"><span class="desc">完成率</span></van-col>
        <van-col span="10"><span class="desc">合格率</span></van-col>
      </van-row>

      <van-row>
        <van-col span="4"><span class="desc">自检</span></van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" size="20" type="spinner"/>
          <van-progress v-else :percentage="((productSchedule.selfFinish / productSchedule.self) * 100).toFixed(2)"/>
        </van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" size="20" type="spinner"/>
          <van-progress v-else :percentage="((productSchedule.selfGood / productSchedule.self) * 100).toFixed(2)"/>
        </van-col>
      </van-row>
      <van-row>
        <van-col span="4"><span class="desc">互检</span></van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" size="20" type="spinner"/>
          <van-progress v-else :percentage="((productSchedule.eachFinish / productSchedule.each) * 100).toFixed(2)"/>
        </van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" size="20" type="spinner"/>
          <van-progress v-else :percentage="((productSchedule.eachGood / productSchedule.each) * 100).toFixed(2)"/>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
import {findProductSchedule} from "../api";

export default {
  name: "ProductCard",
  props: {
    item: {
      type: Object,
    }
  },
  data() {
    return {
      scheduleLoading: true,
      productSchedule: Object
    }
  },
  methods: {},
  mounted() {
    findProductSchedule(this.item.orderId).then(data => {
      this.productSchedule = data.obj;
    }).finally(() => {
      this.scheduleLoading = false;
    })
  }
}
</script>

<style scoped>
div.product-card {
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
.van-col {
  margin: 5px 0;
}
</style>
