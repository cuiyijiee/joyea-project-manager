<template>
  <div>
    泛微检查权限中。。。
    <h1 v-if="error">{{ errorMsg }}</h1>
  </div>
</template>

<script>

import {getEcologyAuthUrl, getEcologyProfile} from "../api";
import {getQueryParam} from "../utils/JoyeaUtil"
import {mapActions} from 'vuex';

export default {
  name: "ELogin.vue",
  data() {
    return {
      redirect: "",
      error: false,
      errorMsg: ""
    }
  },
  methods: {
    ...mapActions(['setToken']),
  },
  mounted() {
    let ticket = getQueryParam("ticket")
    if (ticket) {
      console.log("got ecology ticket: " + ticket);
      getEcologyProfile(ticket).then(data => {
        console.log("get user profile: " + JSON.stringify(data));
        if (data.code === "0") {
          this.$notify({type: 'success', message: '欢迎回来，' + data.obj.name + '！'});
          let token = data.obj.token;
          this.setToken(token);
          this.$router.replace("/project")
        } else {
          this.error = true;
          this.errorMsg = data.msg;
        }
      })
    } else {
      getEcologyAuthUrl().then(resp => {
        let authUrl = resp.obj;
        window.location.replace(authUrl.replace("#", "%23"));
      })
    }
  }
}
</script>

<style scoped>

</style>
