<template>
  <div>
    <el-card style="width: 40%; margin-left: 120px; margin-top: 40px" >
        <h2 style="padding: 30px">个人信息</h2>
      <el-form :model="form" ref="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input style="width: 80%" v-model="form.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input style="width: 80%" v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="权限">
            <span v-if="form.role==1" style="margin:5px">管理员</span>
            <span v-if="form.role==2" style="margin:5px">读者</span>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input style="width: 80%" v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <div>
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </div>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" style="width: 80%" v-model="form.address"></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "Person",
  data() {
    return {
      form: {}
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.form = JSON.parse(str)
  },
  methods: {
    update() {
      request.put("/user", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          ElMessage.success("更新成功")
          sessionStorage.setItem("user", JSON.stringify(this.form))
          // 触发Layout更新用户信息
          this.$emit("userInfo")
        } else {
          ElMessage.error(res.msg)
        }
      })

    }
  }
}
</script>

<style>
.avatar-uploader  {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409EFF;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.box-card {
  width: 60%;
  margin: auto;
  padding: 20px;
}
</style>
