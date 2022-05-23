<template>
    <div class="show">
        <page-header></page-header>
        <div class="section">
            <message-list :key="timer2"></message-list>
            <div class="message-container">
            <div class="border">
                <show-message :key="timer" v-if="this.$store.state.tab.type == 0"></show-message>
                <release-message v-if="this.$store.state.tab.type == 1"></release-message>
                <update-message :key="timer" v-if="this.$store.state.tab.type == 2"></update-message>
                <reply-message :key="timer" v-if="this.$store.state.tab.type == 3"></reply-message>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import ReleaseMessage from '../message/releaseMessage.vue'
import ReplyMessage from '../message/replyMessage.vue'
import ShowMessage from '../message/showMessage.vue'
import UpdateMessage from '../message/updateMessage.vue'
import MessageList from './messageList.vue'
import pageHeader from './pageHeader.vue'
export default {
  components: { pageHeader, MessageList, UpdateMessage, ShowMessage, ReleaseMessage, ReplyMessage},
  data() {
      return {
          timer: '',
          timer2: '',
      }
  },
  mounted() {
  },
  methods: {
      // 刷新子组件
      handler() {
        this.timer = new Date().getTime();
      },
      handler2() {
          this.timer2 = new Date().getTime();
      }
  }
}
</script>

<style scoped>
@import url("../../assets/css/show.css");
</style>