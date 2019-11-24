<template>
  <div>
    <el-button type="primary" plain @click="onSend">发送</el-button>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="收件人">
      <el-input v-model="form.recvname"></el-input>
      </el-form-item>
      <el-form-item label="邮件主题">
        <el-input v-model="form.subject"></el-input>
      </el-form-item>
      <el-form-item label="邮件正文">
        <quill-editor ref="text" v-model="form.content" class="myQuillEditor" :options="editorOption" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { quillEditor } from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'
  import {sendEmail} from '../../api/common/send'
  export default {
    name: 'send-box',
    components: {
      quillEditor
    },
    data () {
      return {
        form: {
          recvname: '',
          subject: '',
          content: ''
        },
        editorOption: {}
      }
    },
    methods: {
      onSend () {
        sendEmail({emailFrom: '792472345@qq.com', emailPwd: 'mvrsvutncspcbdgd', emailRecipient: this.form.recvname, emailSubject: this.form.subject, sendDate: new Date(), emailContent: this.form.content}).then(res => {
          console.debug(res)
          // eslint-disable-next-line handle-callback-err
        }).catch(err => {
          this.$message.warning(err.message)
        })
      }
    }
  }

</script>

<style scoped>
  .inline {
    display: inline-block;
  }
</style>