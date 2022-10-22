<template>
  <div>
    <van-nav-bar title="产品信息"
                 left-arrow left-text="返回" @click-left="() => {this.$router.push('/project')}"/>
    <van-tabs v-model="typeActive" color="#1989fa" @click="onTabChanged">
      <van-tab :title="'全部(' + count[0]+')' "></van-tab>
      <van-tab :title="'未开始(' + count[1]+')' "></van-tab>
      <van-tab :title="'验证中(' + count[2]+')' "></van-tab>
      <van-tab :title="'已完成(' + count[3]+')' "></van-tab>
    </van-tabs>
    <van-search v-model="searchKey" placeholder="搜索产品关键字" @search="onSearch"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
    <van-list
      ref="productList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <ProductCard v-for="item in productList" :key="item.orderId + '_' + item.productNumber" :item="item"
                   @click.native="handleClickProductItem(item.orderId)"/>
    </van-list>
  </div>
</template>

<script>

import {listProduct, listProductCount} from "../api";
import ProductCard from "./ProductCard";

export default {
  name: "Product",
  components: {
    ProductCard
  },
  data() {
    return {
      typeActive: "全部",
      searchKey: "",
      tabStatus: 0,
      searchResultCount: 0,
      projectId: "",
      current: 0,
      pageSize: 5,
      searchLoading: false,
      searchHasMore: true,
      productList: [],
      count: [0, 0, 0, 0]
    }
  },
  methods: {
    onTabChanged(name, title) {
      this.tabStatus = name;
      this.onSearch();
    },
    onSearch() {
      this.current = 0;
      this.searchHasMore = true;
      this.productList = [];
      this.$refs.productList.check();
    },
    onLoad() {
      this.listProduct();
    },
    listProduct() {
      this.searchLoading = true;
      listProduct(this.projectId, this.searchKey, this.tabStatus, this.current + 1, this.pageSize).then(data => {
        this.productList = this.productList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;
        this.current = data.pageNum;
      }).finally(() => {
        this.searchLoading = false;
      })
    },
    handleClickProductItem(orderId) {
      this.$router.push({
        path: "/process",
        query: {
          orderId: orderId,
          projectId: this.projectId
        }
      })
    },
    listCount() {
      listProductCount(this.projectId).then(resp => {
        if (resp.code === '0') {
          this.count = resp.obj.split("_")
        }
      })
    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.listCount();
  }
}
</script>

<style scoped>

</style>
