<template>
  <div>
    <van-search v-model="searchKey"
                :placeholder="placeholder"
                @focus="onShowHistory"
                @search="onSearch"/>
    <van-overlay :show="searchHistoryVisible" @click="searchHistoryVisible = false">
      <div class="wrapper" @click.stop>
        <van-search v-model="searchKey"
                    ref="realSearch"
                    :placeholder="placeholder"
                    @focus="onShowHistory"
                    @search="onSearch"/>
        <div class="search-history" v-if="searchHistoryVisible">
          <van-cell v-for="(item,index) in cachedSearchHistory"
                    @click="onClickHistory(item)"
                    :title="item.searchKey" :key="index"/>
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>

import {getLatestSearchHistory} from "../api";

export default {
  name: "MySearchInput",
  props: {
    placeholder: {
      type: String,
      default: "请输入内容"
    },
    cacheKey: {
      type: String,
      default: "searchKey"
    }
  },
  data() {
    return {
      searchKey: "",
      searchHistoryVisible: false,
      cachedSearchHistory: []
    }
  },
  methods: {
    onSearch() {
      this.$emit("onSearch", this.searchKey);
      this.searchHistoryVisible = false;
    },
    onShowHistory() {
      let cachedSearchHistoryStr = localStorage.getItem(this.cacheKey)
      if (cachedSearchHistoryStr) {
        this.cachedSearchHistory = JSON.parse(cachedSearchHistoryStr)
      }
      this.searchHistoryVisible = true;
      this.$nextTick(() => {
        const input = document.getElementsByClassName("van-field__control")[1];
        input.focus();
      })
    },
    onClickHistory(historyKey) {
      this.searchKey = historyKey.searchKey;
      this.onSearch();
    }
  },
  mounted() {
    this.searchKey = "";
    getLatestSearchHistory(this.cacheKey).then(resp => {
      this.cachedSearchHistory = resp.list;
    })
  }
}
</script>

<style scoped>
.search-history {
  position: absolute;
  width: 100%;
  z-index: 4;
}
</style>
