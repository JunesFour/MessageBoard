<template>
  <div class="content">
    <div class="title">
      <!-- <img src="../../assets/images/logo.png" class="logo" /> -->
      <span style="font-weight: bold;font-size:20px;">留言内容</span>
    </div>
    <div class="info">
      <div class="table" style="width: 800px">
        <el-table
          :data="messageList"
          stripe
          style="width: 100%"
          :show-header="false"
        >
          <el-table-column prop="title" width="560"> </el-table-column>
          <el-table-column prop="addTime" width="100"> </el-table-column>
          <el-table-column>
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="show(scope.row.id)"
                style="padding: 5px; margin: 0px"
                >查看</el-button
              >
              <el-button
                size="mini"
                @click="edit(scope.row.id, scope.row.username)"
                style="padding: 5px; margin: 2px"
                >修改</el-button
              >
              <el-button
                size="mini"
                @click="reply(scope.row.id)"
                type="danger"
                style="padding: 5px; margin: 2px"
                v-if="username == 'admin'"
                >回复</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="s" style="float: left; margin-left: 30%; margin-top: 20px">
      <el-pagination
        background
        layout="prev, pager, next"
        :page-count="totalPages"
        @current-change="currentChange"
        style="text-align: center"
        >{{}}
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  // 注入reload依赖
  inject: ["reload"],
  data() {
    return {
      messageList: [],
      totalPages: 0,
      currentPage: 1,
      username:"",
    };
  },
  mounted() {
    this.username = window.localStorage.getItem("username");
    let forms = new FormData();
    forms.append("page", this.currentPage);
    this.$axios.post("/getMessages", forms).then((res) => {
      this.totalPages = parseInt(res.data.totalPages);
      this.messageList = res.data.messageList;
      for (let i = 0; i < this.messageList.length; i++) {
        this.messageList[i].addTime = new Date(
          this.messageList[i].addTime * 1000
        ).toLocaleDateString();
      }
    });
  },
  methods: {
    currentChange(currentPage) {
      let forms = new FormData();
      forms.append("page", currentPage);
      this.$axios.post("/getMessages", forms).then((res) => {
        this.totalPages = parseInt(res.data.totalPages);
        this.messageList = res.data.messageList;
        for (let i = 0; i < this.messageList.length; i++) {
          this.messageList[i].addTime = new Date(
            this.messageList[i].addTime * 1000
          ).toLocaleDateString();
        }
      });
    },
    show(id) {
      this.$store.state.tab.type = 0;
      window.localStorage.setItem("messageId", id);
      // 调用父组件的方法
      this.$parent.handler();
    },
    edit(id, messageAuthor) {
      if (window.localStorage.getItem("token") === null) {
        this.$message({
            message: "请先登录再修改留言",
            type: "error",
          });
      } else if (messageAuthor === this.username || this.username === "admin") {
          this.$store.state.tab.type = 2;
          window.localStorage.setItem("messageId", id);
          this.$parent.handler();
      } else {
        this.$message({
            message: "这不是您的留言，您无法修改",
            type: "error",
          });
      }
    },
    reply(id) {
      this.$store.state.tab.type = 3;
      window.localStorage.setItem("messageId", id);
      // 调用父组件的方法
      this.$parent.handler();
    }
  },
};
</script>

<style scoped>
</style>