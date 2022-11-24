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
                    :title="item" :key="index"/>
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>
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
      if (this.searchKey) {
        let cachedSearchHistoryStr = localStorage.getItem(this.cacheKey)
        let cachedSearchHistory = []
        if (cachedSearchHistoryStr) {
          cachedSearchHistory = JSON.parse(cachedSearchHistoryStr)
        }
        let index = cachedSearchHistory.indexOf(this.searchKey)
        if (index !== -1) {
          cachedSearchHistory.splice(index, 1)
        }
        cachedSearchHistory.unshift(this.searchKey)
        localStorage.setItem(this.cacheKey, JSON.stringify(cachedSearchHistory))
      }
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
      this.searchKey = historyKey;
      this.onSearch();
    }
  },
  mounted() {
    this.searchKey = "";
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
