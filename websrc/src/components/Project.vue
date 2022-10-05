<template>
  <div class="hello">
    <van-nav-bar title="项目信息"/>
    <van-search v-model="searchKey" placeholder="搜索项目关键字"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
    <van-list
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <ProjectCard v-for="item in projectList" :key="item.fid"/>
    </van-list>
  </div>
</template>


<script>
import ProjectCard from "./ProjectCard";

export default {
  name: 'HelloWorld',
  components: {
    ProjectCard
  },
  data() {
    return {
      searchKey: "",
      searchResultCount: 1875,
      searchLoading: false,
      searchHasMore: true,
      projectList: []
    }
  },
  methods: {
    onLoad() {
// 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      setTimeout(() => {
        for (let i = 0; i < 10; i++) {
          this.projectList.push(this.projectList.length + 1);
        }
        // 加载状态结束
        this.searchLoading = false;
        // 数据全部加载完成
        if (this.projectList.length >= 40) {
          this.searchHasMore = false;
        }
      }, 1000);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
</style>
