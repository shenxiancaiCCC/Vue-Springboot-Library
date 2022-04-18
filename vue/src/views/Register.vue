<template>
<div  class="login-container"  >
    <el-form ref="form" :model="form"    :rules="rules" class="login-page">
      <h2 class="title" style="margin-bottom: 20px">用户注册</h2>
      <el-form-item prop="username" >
        <el-input v-model="form.username" placeholder="请输入用户名" clearable >
          <template #prefix>
            <el-icon class="el-input__icon"><User/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password"  placeholder="请输入密码" clearable show-password>
          <template #prefix>
            <el-icon class="el-input__icon"><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="confirm">
        <el-input v-model="form.confirm" placeholder="请再次确认密码" clearable show-password>
          <template #prefix>
            <el-icon class="el-input__icon"><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="role">
        <el-radio v-model="form.role" label="2">读者</el-radio>
        <el-radio v-model="form.role" label="1">管理员</el-radio>
      </el-form-item>
      <el-form-item prop="authorize" v-if="form.role==1">
        <el-input v-model="form.authorize" placeholder="请输入管理员注册码" clearable show-password>
          <template #prefix>
            <el-icon class="el-input__icon"><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <div style="display: flex">
          <el-input  v-model="form.validCode" style="width: 45%;" placeholder="请输入验证码"></el-input>
          <ValidCode @input="createValidCode" style="width: 50%"/>
        </div>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" style=" width: 100%" @click="register">注 册</el-button>
      </el-form-item>
      <el-form-item><el-button type="text" @click="$router.push('/login')">前往登录>> </el-button></el-form-item>
    </el-form>
</div>

</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";
import ValidCode from "../components/Validate";
export default {
  name: "Login",
  components:{
    ValidCode
  },
  data(){
    return{
      form:{},
      validCode: '',
      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
          {
            min: 2,
            max: 13,
            message: '长度要求为2到13位',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          }
        ],
      confirm:[
        {
          required:true,
          message:"请确认密码",
          trigger:"blur"
        }
      ],
        authorize:[
          {
            required:true,
            message:"请输入注册码",
            trigger:"blur"
          }
        ],
      }
    }
    },

  methods:{
    createValidCode(data){
      this.validCode =data
    },
    register(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.form.validCode) {
            ElMessage.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            ElMessage.error("验证码错误")
            return
          }
          if(this.form.password != this.form.confirm)
          {
            ElMessage.error("两次密码输入不一致")
            return
          }
          if(this.form.role == 1 && this.form.authorize != "1234")
          {
            ElMessage.error("请输入正确的注册码")
            return
          }
          request.post("user/register",this.form).then(res=>{
            if(res.code == 0)
            {
              ElMessage.success("注册成功")
              this.$router.push("/login")
            }
            else {ElMessage.error(res.msg)}
          })
        }
      })

    }
  }

  }

</script>

<style scoped>
.login-container {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url('../img/bg2.svg');
  background-size: contain;
}
.login-page {
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>