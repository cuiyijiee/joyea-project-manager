<template>
  <div class="project-card">
    <div class="container">
      <van-row>
        <van-col span="24">
          <van-icon v-if="item.collect" name="like" style="padding: 0 5px;" color="#ee0a24"
                    @click.stop="handleCollectProject"/>
          <van-icon v-else name="like-o" style="padding: 0 5px;" color="#ee0a24" @click.stop="handleCollectProject"/>
          <span class="desc">项目名称：</span>{{ item.projectName }}
        </van-col>
        <van-col span="24"><span class="desc">项目编码：</span>{{ item.fnumber }}</van-col>
        <van-col span="24"><span class="desc">项目经理：</span>{{ item.xmFzr || '' }}</van-col>
        <van-col span="24"><span class="desc">所属部门：</span>{{ item.depart || '' }}</van-col>
        <van-col span="24"><span class="desc">装调部门：</span>{{ item.zdDept || '' }}</van-col>
        <van-col span="24"><span class="desc">装调负责人：</span>{{ item.zdDeptMans || '' }}</van-col>
      </van-row>
      <van-divider></van-divider>
      <van-row>
        <van-col span="4"></van-col>
        <van-col span="10"><span class="desc">完成率</span></van-col>
        <van-col span="10"><span class="desc">合格率</span></van-col>
      </van-row>
      <van-row>
        <van-col span="4"><span class="desc">自检</span></van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" type="spinner" size="20"/>
          <van-progress v-else :percentage="((projectSchedule.selfFinish / projectSchedule.self) * 100).toFixed(2)"/>
        </van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" type="spinner" size="20"/>
          <van-progress v-else :percentage="((projectSchedule.selfGood / projectSchedule.self) * 100).toFixed(2)"/>
        </van-col>
      </van-row>
      <van-row>
        <van-col span="4"><span class="desc">互检</span></van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" type="spinner" size="20"/>
          <van-progress v-else :percentage="((projectSchedule.eachFinish / projectSchedule.each) * 100).toFixed(2)"/>
        </van-col>
        <van-col span="10">
          <van-loading v-if="scheduleLoading" type="spinner" size="20"/>
          <van-progress v-else :percentage="((projectSchedule.eachGood / projectSchedule.each) * 100).toFixed(2)"/>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>

import {findProjectSchedule, addProjectCollect} from "../api";

export default {
  name: "ProjectCard",
  props: {
    item: {
      type: Object,
    }
  },
  data() {
    return {
      scheduleLoading: true,
      projectSchedule: Object
    }
  },
  methods: {
    handleCollectProject() {
      addProjectCollect(this.item.fid).then(resp => {
        if (resp.code === '0') {
          if(this.item.collect){
            this.$notify({type: "success", message: "取消收藏成功！"});
          }else{
            this.$notify({type: "success", message: "收藏成功！"});
          }
          this.item.collect = !this.item.collect;
        } else {
          this.$notify({type: "warning", message: resp.msg})
        }
      })
    }
  },
  mounted() {
    findProjectSchedule(this.item.fid).then(data => {
      this.projectSchedule = data.obj;
    }).finally(() => {
      this.scheduleLoading = false;
    })
  }
}
</script>

<style scoped>
div.project-card {
  margin: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

div.container {
  padding: 10px;
}

.van-progress {
  margin: 10px 0;
}

span.desc {
  color: dimgray;
}

.van-col {
  margin: 5px 0;
}

</style>
