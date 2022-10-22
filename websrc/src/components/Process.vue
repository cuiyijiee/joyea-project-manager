<template>
  <div>
    <van-nav-bar left-arrow left-text="返回" title="工序信息" @click-left="() => {this.$router.push({
    path:'/product',query:{projectId:projectId}
    })}"/>
    <van-tabs v-model="typeActive" color="#1989fa" @click="onTabChanged">
      <van-tab :title="'全部(' + count[0]+')' "></van-tab>
      <van-tab :title="'未开始(' + count[1]+')' "></van-tab>
      <van-tab :title="'验证中(' + count[2]+')' "></van-tab>
      <van-tab :title="'已完成(' + count[3]+')' "></van-tab>
    </van-tabs>
    <van-search v-model="searchKey" placeholder="搜索工序关键字" @search="onSearch"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
    <van-list
      ref="processList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <ProcessCard v-for="item in processList" :key="item.taskId" :item="item"
                   @click.native="handleClickProcessItem(item.taskId)">
      </ProcessCard>
    </van-list>
  </div>
</template>

<script>

import {listProcess, listProcessCount} from "../api";
import ProcessCard from "./ProcessCard";

export default {
  name: "Process",
  components: {
    ProcessCard
  },
  data() {
    return {
      typeActive: "全部",
      searchKey: "",
      searchResultCount: 0,
      projectId: "",
      orderId: "",
      current: 0,
      pageSize: 5,
      status: 0,
      searchLoading: false,
      searchHasMore: true,
      processList: [],
      count: [0, 0, 0, 0]
    }
  },
  methods: {
    onTabChanged(name, title) {
      this.status = name;
      this.onSearch();
    },
    onSearch() {
      this.current = 0;
      this.searchHasMore = true;
      this.processList = [];
      this.$refs.processList.check();
    },
    onLoad() {
      this.listProcess();
    },
    listProcess() {
      listProcess(this.orderId, this.searchKey, this.status, this.current + 1, this.pageSize).then(data => {
        this.processList = this.processList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;
        this.current = data.pageNum;
      }).finally(() => {
        this.searchLoading = false;
      })
    },
    handleClickProcessItem(taskId) {
      this.$router.push({
        path: "/checkItem",
        query: {
          orderId: this.orderId,
          projectId: this.projectId,
          taskId: taskId
        }
      })
    },
    listCount() {
      listProcessCount(this.orderId).then(resp => {
        if (resp.code === '0') {
          this.count = resp.obj.split("_")
        }
      })
    }
  },
  mounted() {
    this.orderId = this.$route.query.orderId;
    this.projectId = this.$route.query.projectId;
    this.listCount();
  }
}
</script>

<style scoped>

</style>
