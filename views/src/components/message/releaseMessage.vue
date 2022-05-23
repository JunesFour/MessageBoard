<template>
    <div class="ms" style="margin-top:-20px;">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size:20px;">发布留言</span>
      </div>
      <div class="text item" style="margin-top:0px;">
        <span style="font-weight: bold;">主题：</span>
        <el-input v-model="title" placeholder="请输入标题" style="margin-top:20px;width:200px;"></el-input>
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">内容：</span>
        <div class="textarea" style="width:300px; margin-left:50px;margin-top:-17px;">
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入内容"
            v-model="content">
          </el-input>
        </div>
      </div>
      <div class="text item" style="margin-top:30px;margin-left:50px;">
        <el-button type="danger" @click="submit()">发布</el-button>
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
      title: "",
      content: "",
    };
  },
  methods: {
    submit() {
      let forms = new FormData();
      forms.append("token", JSON.parse(window.localStorage.getItem("token")));
      forms.append("title", this.title);
      forms.append("content", this.content);
      this.$axios.post("/release", forms).then((res) => {
        if (res.data.message === "success") {
          this.$message({
            message: "发布成功",
            type: "success",
          });
          this.$router.go(0);
        } else {
          this.$message({
            message: "发布失败",
            type: "error",
          });
        }
      });
    },
  },
};
</script>

<style scoped>
@import url("../../assets/css/show.css");
</style>