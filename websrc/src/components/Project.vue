<template>
  <div class="hello">
    <van-nav-bar title="项目信息"/>
    <van-search v-model="searchKey" placeholder="搜索项目关键字" @search="onSearch"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
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

import {listProject} from "../api";

export default {
  name: 'Project',
  components: {
    ProjectCard
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
    onSearch() {
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
    }
  },
  mounted() {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
</style>
