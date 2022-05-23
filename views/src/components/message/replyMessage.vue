<template>
    <div class="ms" style="margin-top:-20px;">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size:20px;">回复留言</span>
      </div>
      <div class="text item" style="margin-top:10px;">
        <span style="font-weight: bold;">主题：</span>{{ title }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">作者：</span>{{ username }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">日期：</span>{{ addTime }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">内容：</span>{{ content }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">回复：</span>
        <div class="textarea" style="width:300px; margin-left:50px;margin-top:-17px;">
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入内容"
            v-model="reply">
          </el-input>
        </div>
      </div>
      <div class="text item" style="margin-top:30px;margin-left:50px;">
        <el-button type="danger" @click="submit()">回复</el-button>
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;"></span>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      title: "",
      content: "",
      addTime: "",
      reply: "",
    };
  },
  mounted() {
    let forms = new FormData();
    let id = parseInt(window.localStorage.getItem("messageId"));
    forms.append("id", id);
    this.$axios.post("/show", forms).then((res) => {
      this.username = res.data.messageItem.username;
      this.title = res.data.messageItem.title;
      this.content = res.data.messageItem.content;
      this.addTime = new Date(
        res.data.messageItem.addTime * 1000
      ).toLocaleDateString();
    });
  },
  methods: {
    submit() {
      if (this.reply === "") {
        this.$message({
          message: "回复内容为空",
          type: "error",
        });
      } else {
        let forms = new FormData();
        let id = parseInt(window.localStorage.getItem("messageId"));
        forms.append("id", id);
        forms.append("reply", this.reply);
        this.$axios.post("/reply", forms).then((res) => {
          if (res.data.message === "success") {
            this.$message({
              message: "回复成功",
              type: "success",
            });
            this.$store.state.tab.type = 0;
            this.$parent.handler2();
          } else {
            this.$message({
              message: "回复失败",
              type: "error",
            });
          }
        });
      }
    },
  },
};
</script>

<style scoped>
@import url("../../assets/css/show.css");
</style>