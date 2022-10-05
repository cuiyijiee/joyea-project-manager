<template>
  <div class="project-card">
    <div class="container">
      <van-row>
        <van-col span="12">{{ item.projectName }}</van-col>
        <van-col span="12">{{ item.fNumber }}</van-col>
      </van-row>
      <van-row>
        <van-col span="12">装调负责人：{{ item.projectFzr || '' }}</van-col>
        <van-col span="12">项目经理：{{ '' }}</van-col>
      </van-row>
      <van-row>
        <van-col span="12">所属部门：{{ item.projectDepart || '' }}</van-col>
        <van-col span="12">装调项目部：{{ '' }}</van-col>
      </van-row>
      <van-divider></van-divider>
      <van-row>
        <van-col span="4"></van-col>
        <van-col span="10">完成率</van-col>
        <van-col span="10">合格率</van-col>
      </van-row>
      <van-row>
        <van-col span="4">自检</van-col>
        <van-col span="10">
          <van-progress :percentage="100"/>
        </van-col>
        <van-col span="10">
          <van-progress :percentage="80"/>
        </van-col>
      </van-row>
      <van-row>
        <van-col span="4">互检</van-col>
        <van-col span="10">
          <van-progress :percentage="50"/>
        </van-col>
        <van-col span="10">
          <van-progress :percentage="20"/>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProjectCard",
  data() {
    return {
      item: {
        fid: "123456",
        fNumber: "J2012BJTRT01S",
        projectName: "北京同仁堂",
        projectFzr: "袁红星",
        projectDepart: "李金国"
      }
    }
  }
}
</script>

<style scoped>
div.project-card {
  margin: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
}

div.container {
  padding: 10px;
}

.van-progress{
  margin: 10px 0;
}
</style>
