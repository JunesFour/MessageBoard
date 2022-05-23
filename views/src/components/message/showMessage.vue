<template>
  <div class="ms" style="margin-top:-20px;">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-weight: bold;font-size:20px;">查看留言</span>
      </div>
      <div class="text item" style="margin-top:10px;">
        <span style="font-weight: bold;">主题：</span>{{ title }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">日期：</span>{{ addTime }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">作者：</span>{{ username }}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">内容：</span>{{ content}}
      </div>
      <div class="text item" style="margin-top:30px;">
        <span style="font-weight: bold;">回复：</span>{{ reply}}
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
      reply: "",
      addTime: "",
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
      this.reply = res.data.messageItem.reply;
      if (this.reply === "") {
        this.reply = "暂无回复";
      }
      this.addTime = new Date(
        res.data.messageItem.addTime * 1000
      ).toLocaleDateString();
    });
  },
  methods: {},
};
</script>

<style scoped>
@import url("../../assets/css/show.css");
</style>