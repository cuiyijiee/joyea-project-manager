<template>
  <div>
    <van-nav-bar left-arrow left-text="返回" title="产品信息" @click-left="() => {this.$router.push('/')}"/>
    <van-tabs v-model="typeActive">
      <van-tab title="全部"></van-tab>
      <van-tab title="未开始"></van-tab>
      <van-tab title="验证中"></van-tab>
      <van-tab title="已完成"></van-tab>
    </van-tabs>
    <van-search v-model="searchKey" placeholder="搜索产品关键字" @search="onSearch"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
    <van-list
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

import {listProduct} from "../api";
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
      searchResultCount: 0,
      projectId: "",
      current: 0,
      pageSize: 5,
      searchLoading: false,
      searchHasMore: true,
      productList: []
    }
  },
  methods: {
    onSearch() {

    },
    onLoad() {
      this.listProduct();
    },
    listProduct() {
      this.searchLoading = true;
      listProduct(this.projectId, this.searchKey, this.current + 1, this.pageSize).then(data => {
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
    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
  }
}
</script>

<style scoped>

</style>
