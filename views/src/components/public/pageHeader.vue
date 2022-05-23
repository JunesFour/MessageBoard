<template>
  <div class="header">
    <form class="loginForm" v-if="isLogin == 0" style="margin-top:10px;margin-right:10px;">
      <label for="username">用户名：</label>
      <input type="text" v-model="username" />
      <label for="password">密码:</label>
      <input type="password" v-model="password" />
      <input type="button" value="登录" class="login-button" @click="login()" />
      <input
        type="button"
        value="注册"
        class="login-button"
        @click="register()"
      />
    </form>
    <form class="loginForm" v-else style="margin-top:10px;margin-right:10px;">
      <span class="greet">欢迎进入留言板 &nbsp;</span>
      <span class="identity" style="color: red">{{ username }}</span>

      <span>
        <a @click="logout()" style="margin-left: 30px; margin-right: 10px"
          >退出登录</a
        ></span
      >
    </form>
    <div class="button">
      <el-button @click="release()" style="width:110px;">发布留言</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      isLogin: 0,
    };
  },
  mounted() {
    let forms = new FormData();
    forms.append("token", JSON.parse(window.localStorage.getItem("token")));
    this.$axios.post("/checkToken", forms).then((res) => {
      if (res.data.message === "fail") {
        this.isLogin = 0;
      } else {
        this.username = res.data.username;
        this.isLogin = 1;
      }
    });
  },
  methods: {
    login() {
      let forms = new FormData();
      forms.append("username", this.username);
      forms.append("password", this.password);
      this.$axios.post("/login", forms).then((res) => {
        if (res.data.message === "success") {
          this.$message({
            message: "登录成功",
            type: "success",
          });
          window.localStorage.setItem("token", JSON.stringify(res.data.token));
          window.localStorage.setItem("username", this.username);
          this.$router.go(0);
        } else {
          this.$message({
            message: "用户名或密码错误",
            type: "error",
          });
        }
      });
    },
    register() {
      let forms = new FormData();
      forms.append("username", this.username);
      forms.append("password", this.password);
      this.$axios.post("/register", forms).then((res) => {
        if (res.data.message === "success") {
          this.$message({
            message: "注册成功",
            type: "success",
          });
        } else if (res.data.message === "exist") {
          this.$message({
            message: "已存在该用户，请直接登录",
            type: "error",
          });
        } else {
          this.$message({
            message: "注册失败",
            type: "error",
          });
        }
      });
    },
    logout() {
      this.$confirm("退出登录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "登出成功!",
          });
          window.localStorage.removeItem("token");
          window.localStorage.removeItem("username");
          window.localStorage.setItem("messageId", 0);
          this.$router.go(0);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消登出",
          });
        });
    },
    release() {
        if (this.isLogin == 0) {
            this.$message({
                message: '请先登录再发表留言',
                type: 'error'
            });
        } else {
            this.$store.state.tab.type = 1;
            window.localStorage.setItem("messageId", id);
            this.$parent.handler();
        }
    }
  },
};
</script>

<style scoped>
</style>