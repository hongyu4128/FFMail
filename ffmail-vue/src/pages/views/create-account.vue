<template>
  <div class="center-in-center">
    <el-form ref="form" :model="form" label-width="150px">
      <el-form-item label="收件服务器类型">
        <el-select v-model="form.region" placeholder="请选择收件服务器">
          <el-option label="POP3" value="POP3"></el-option>
          <el-option label="IMAP" value="IMAP"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱地址">
        <el-input v-model="form.address" placeholder="请输入邮箱地址" ></el-input>
      </el-form-item>
      <el-form-item label="邮箱密码">
        <el-input v-model="form.password" placeholder="请输入邮箱密码（客户端密码）"></el-input>
      </el-form-item>
      <el-form-item label="收件服务器">
        <el-input v-model="form.service" placeholder="请输入收件服务器"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.prevent="doClick">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>import {saveUserEmail} from '../../api/common/setting'

export default {
  name: 'create-account.vue',
  data () {
    return {
      form: {
        region: '',
        address: '',
        password: '',
        service: ''
      }
    }
  },
  methods: {
    doClick () {
      // eslint-disable-next-line no-undef
      saveUserEmail({userId: 1, recvServiceType: this.form.region, emailAddress: this.form.address, emailPwd: this.form.password, recvService: this.form.service}).then(res => {
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
  .center-in-center{
    position: absolute;
    top: 20%;
    left: 40%;
  }
</style>