<template>
<div class="fullscreen">
  <div class="register-box">
    <div style="text-align: center">
      <img src="../assets/logo.png" alt="" class="logo">
    </div>
    <p class="text-tips">你好，欢迎注册</p>
    <form action="" class="login-form">
      <div class="m-list-group">
        <div class="m-list-group-item">
          <input type="text" placeholder="Username" class="m-input" v-model="userName">
        </div>
        <div class="m-list-group-item">
          <input type="email" placeholder="Telephone" class="m-input" v-model="telephone">
        </div>
        <div class="m-list-group-item">
          <input type="password" placeholder="Password" class="m-input" v-model="password">
        </div>
      </div>
      <el-button class="m-btn sub select-none" @click.prevent="handleRegister" :loading="registerLoading">注册</el-button>
      <p class="text-tips">已经有账号？</p>
      <a href="/login" class="m-btn m-btn-text" >登陆</a>
    </form>
  </div>
</div>
</template>
<script>
import {mapActions} from 'vuex'
export default {
  name: 'login',
  data () {
    return {
      registerLoading: false,
      userName: '',
      telephone: '',
      password: ''
    }
  },
  methods: {
    ...mapActions(['register']),
    handleRegister () {
      console.debug(this.userName + ',' + this.telephone + ',' + this.password)
      if (!this.userName || !this.telephone || !this.password) {
        return this.$message.warning('用户名,手机号和密码必填')
      }
      if ((this.userName.length > 10) || (this.userName.length < 6)) {
        return this.$message.warning('用户名需要为6-10位')
      }
      if ((this.telephone.length !== 11)) {
        return this.$message.warning('手机号需要为11位数字')
      }
      if ((this.password.length > 18) || (this.password.length < 8)) {
        return this.$message.warning('密码需要为8-18位字符')
      }
      this.registerLoading = false
      this.register({
        userName: this.userName.trim(),
        telephone: this.telephone.trim(),
        password: this.password
      }).then(res => {
        this.$message.success('恭喜您注册成功,即将跳转到登录界面')
        this.loading = true
        setTimeout(() => {
          this.$router.push('/login')
        }, 1500)
      }).catch(err => {
        this.$message.error(err.message)
      })
    }
  }
}
</script>
<style type="text/css">
  .m-list-group{
    border-radius: 3px;
    padding: 0;
    margin: 0 0 20px;
  }
  .m-list-group .m-list-group-item{
    position: relative;
    display: block;
    padding: 6px 10px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #e7ecee;
  }
  .m-list-group .m-list-group-item:first-child{
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
  }
  .fullscreen{
    position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .register-box{
    position: relative;
    width: 330px;
    margin: 0 auto;
    padding: 0px 15px;
  }
  .register-box .logo{
    max-width: 40%;
    margin-bottom: 30px;
  }
  .register-box .text-tips{
    text-align: center;
    color: #909DB7;
  }
  .register-box .m-input{
    width: 100%;
    padding: 10px;
    border: none;
    outline: none;
    box-sizing: border-box;
  }
  .register-box .m-btn{
    font-size: 18px;
    width: 100%;
    color: #fff;
    background-color: #1bbd70;
    display: inline-block;
    padding: 10px 12px;
    margin-bottom: 5px;
    line-height: 1.42857143;
    text-align: center;
    cursor: pointer;
    outline: none;
    border-radius: 2px;
    border: 1px solid #1bbd70;
    box-sizing: border-box;
    text-decoration: none;
  }
  .register-box .m-btn.m-btn-text{
    background: #fff;
    color: #1bbd70;
  }
  .register-box .m-btn:hover{
    background-color: #15A25F;
  }
  .register-box .m-btn.m-btn-text:hover{
    background-color: #F4F5F5;
  }
  .register-box .m-btn:active{
    opacity: 0.8;
  }
  @media (max-width: 768px) {
    .register-box{
      width: auto;
    }
  }
</style>
