<template>
  <div>
    <van-sticky>
      <van-nav-bar title="产品信息"/>
      <van-tabs v-model="typeActive" color="#1989fa" @click="onTabChanged">
        <van-tab
          :title="'全部(' + (tabStatus === 0 && !searchLoading ? searchResultCount : count[0]).toString() +')' "></van-tab>
        <van-tab :title="'未开始(' + count[1]+')' "></van-tab>
        <van-tab :title="'验证中(' + count[2]+')' "></van-tab>
        <van-tab :title="'已完成(' + count[3]+')' "></van-tab>
      </van-tabs>
      <my-search-input placeholder="搜索产品关键字"
                       cache-key="CACHE_PRODUCT_SEARCH_HISTORY"
                       @onSearch="onSearch"/>
      <div style="background-color: #ffffff;">
        <span @click="() => {this.$router.back();}"
              style="color: #1989fa;display: flex;align-items: center;width: 80px">
          <van-icon name="arrow-left" size="25px"/>
          <span style="font-size: 16px;color: #1989fa">返回</span>
        </span>
        <van-divider style="margin: 0;">共 {{ searchResultCount }} 个结果</van-divider>
      </div>
    </van-sticky>
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
import MySearchInput from "./MySearchInput";

export default {
  name: "Product",
  components: {
    ProductCard, MySearchInput
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
    onSearch(searchKey) {
      this.searchKey = searchKey;
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
    console.log(this.$route.meta.keepAlive);
    this.projectId = this.$route.query.projectId;
    this.listCount();
  },
  beforeRouteLeave(to, from, next) {
    console.log(to);
    if (to.path === '/process') {
      to.meta.keepAlive = false;
    }
    next();
  }
}
</script>

<style scoped>

</style>
