<template>
  <div class="hello">
    <van-sticky>
      <van-nav-bar title="项目信息" right-text="我的收藏" @click-right="handleClickMyFav"/>
      <my-search-input placeholder="搜索项目关键字"
                       cache-key="CACHE_PROJECT_SEARCH_HISTORY"
                       @onSearch="onSearch"/>
      <van-divider style="background-color: #ffffff;margin: 0">共 {{ searchResultCount }} 个结果</van-divider>
    </van-sticky>
    <van-list
      ref="projectList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <ProjectCard v-for="item in projectList" :key="item.fid" :item="item"
                   @click.native="handleClickProjectItem(item.fid)"/>
    </van-list>
  </div>
</template>


<script>
import ProjectCard from "./ProjectCard";
import MySearchInput from "./MySearchInput";

import {listProject} from "../api";

export default {
  name: 'Project',
  components: {
    ProjectCard, MySearchInput
  },
  data() {
    return {
      searchKey: "",
      searchResultCount: 0,
      searchLoading: false,
      searchHasMore: true,
      projectList: [],
      current: 0,
      pageSize: 5
    }
  },
  methods: {
    onSearch(searchKey) {
      this.searchKey = searchKey;
      this.current = 0;
      this.searchHasMore = true;
      this.projectList = [];
      this.$refs.projectList.check();
    },
    onLoad() {
      this.listProject();
    },
    listProject() {
      listProject(this.searchKey, this.current + 1, this.pageSize).then(data => {
        this.projectList = this.projectList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;

        this.current = data.pageNum;
      }).finally(() => {
        // 加载状态结束
        this.searchLoading = false;
      })
    },
    handleClickProjectItem(fid) {
      this.$router.push({
        path: "/product",
        query: {projectId: fid}
      })
    },
    handleClickMyFav() {
      this.$router.push("/projectCollect")
    }
  },
  mounted() {
  },
  beforeRouteLeave(to, from, next) {
    console.log(to);
    if (to.path === '/product' || to.path === '/projectCollect') {
      to.meta.keepAlive = false;
    }
    next();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
</style>
