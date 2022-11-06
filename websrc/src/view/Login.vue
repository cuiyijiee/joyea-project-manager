<template>
  <div>
    检查权限中。。。
    <h1 v-if="error">{{ errorMsg }}</h1>
  </div>
</template>

<script>
import {getTicket, getProfile} from "../api";
import {mapActions} from 'vuex';

export default {
  name: "Login",
  data() {
    return {
      redirect: "",
      error: false,
      errorMsg: ""
    }
  },
  methods: {
    ...mapActions(['setToken']),
    login() {
      let _this = this;
      if (process.env.NODE_ENV !== 'production') {
        getProfile("test").then(data => {
          if (data.code === "0") {
            this.$notify({type: 'success', message: '欢迎回来，' + data.obj.name + '！'});
            let token = data.obj.token;
            _this.setToken(token);
            console.log("login success")
            this.$router.replace("/project")
          } else {
            this.error = true;
            this.errorMsg = data.msg;
          }
        })
      } else {
        getTicket().then(data => {
          let ticket = data.obj.ticket;
          callNextPlusLogin(ticket, (success, authCode) => {
            if (success) {
              getProfile(authCode).then(data => {
                if (data.code === "0") {
                  this.$notify({type: 'success', message: '欢迎回来，' + data.obj.name + '！'});
                  let token = data.obj.token;
                  _this.setToken(token);
                  console.log("login success")
                  this.$router.replace("/project")
                } else {
                  this.error = true;
                  this.errorMsg = data.msg;
                }
              })
            } else {
              this.error = true;
              this.errorMsg = "当前非next+环境！";
            }
          })
        })
      }
    }
  },
  mounted() {
    this.error = false;
    this.errorMsg = "";
    this.redirect = this.$route.query.redirect;
    this.login();
  }
}
</script>

<style scoped>

</style>
