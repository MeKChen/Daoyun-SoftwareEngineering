<template>
    <div id="login">
        <div class="login-wrap">
            <ul class="menu-tab">
                <li v-for="item in menuTab" :key="item.id" :class="{'current': item.current}" @click="toggleMneu(item)">{{ item.txt }}</li>
             </ul>
             <!-- 表单 start-->
             <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="login-form" size="medium">  
               
                <el-form-item prop="username" class="item-form">
                    <label>邮箱</label>
                    <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item prop="password" class="item-form">
                    <label>密码</label>
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off" minlength="6" maxlength="20"></el-input>
                </el-form-item>

                <el-form-item prop="passwords" class="item-form" v-show="model === 'register'">
                    <label>重复密码</label>
                    <el-input type="password" v-model="ruleForm.passwords" autocomplete="off" minlength="6" maxlength="20"></el-input>
                </el-form-item>

                <el-form-item prop="code" class="item-form">
                <label>验证码</label>
                <el-row :gutter="10">
                <el-col :span="15">
                    <el-input v-model.number="ruleForm.code" minlength="6" maxlength="6"></el-input>
                </el-col>
                <el-col :span="9">
                    <el-button type="success" class="block">获取验证码</el-button>
                </el-col>
                </el-row>
                </el-form-item>

                <el-form-item>
                    <el-button type="danger" @click="submitForm('ruleForm')" class="login-btn block">提交</el-button>
                </el-form-item>
                </el-form>
        </div>
    </div>
</template>
<script>
import { reactive, ref, ifRef, onMounted } from '@vue/composition-api'
import { stripscript, validateEmail, validatePass, validateVCode } from '@/utils/validate'//字符过滤
export default {
    name: 'login',
    //setup(props, context){
    setup(props, { refs }){
      //验证用户名是否为邮箱
      let validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入用户名'));
        } else if(validateEmail(value)){
          callback(new Error('用户名格式错误'));
        }else {
          callback();
        }
      };
      //验证密码
      let validatePassword = (rule, value, callback) => {
        ruleForm.password = stripscript(value) //过滤后的数据
        value = ruleForm.password
        if (value === '') {
          callback(new Error('请输入密码'));
        } else if (validatePass(value)){
          callback(new Error('密码为6至20位的数字和字母组成'));
        } else {
          callback();
        }
      };
      //验证重复密码
      let validatePasswords = (rule, value, callback) => {
        if(model.value === 'login'){ callback(); }
        ruleForm.passwords = stripscript(value) //过滤后的数据
        value = ruleForm.passwords
        if (value === '') {
          callback(new Error('请输入密码'));
        } else if (value != this.ruleForm.password){
          callback(new Error('输入的密码要一致'));
        } else {
          callback();
        }
      };
      //验证验证码
      let validateCode = (rule, value, callback) => {
        ruleForm.code = stripscript(value) //过滤后的数据
        value = ruleForm.code
        if (value === '') {
          return callback(new Error('请输入验证码'));
        }else if(validateVCode(value)){
          return callback(new Error('验证码格式有误'));
        }else{
          callback();
        }
      };
      //存放data数据、自定义函数、生命周期
      const menuTab = reactive([
        { txt: '登录', current: true, type: 'login' },
        { txt: '注册', current: false, type: 'register' }
      ]);
      // 模块值 
      const model = ref('login');
      // 表单绑定数据
      const ruleForm = reactive({
        username: '',
        password: '',
        passwords: '',
        code: ''
      });
      // 表单的验证
      const rules = reactive ({
          username: [
            { validator: validateUsername, trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ],
          passwords: [
            { validator: validatePasswords, trigger: 'blur' }
          ],
          code: [
            { validator: validateCode, trigger: 'blur' }
          ]
        });
      /**
       * 声明函数
       */
      const toggleMneu = (data => {
        menuTab.forEach(elem =>{
                elem.current =false
            });
            data.current = true
            //修改模块指
            model.value = data.type
      })
      const submitForm = (formName => {
        refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      }) 
      /**
       * 生命周期
       */
      // 挂载完成后
      onMounted(() => {
        
      })
      
      return{
        menuTab,
        model,
        ruleForm,
        rules,
        toggleMneu,
        submitForm
      }
    },
    data(){
      
    },
}
</script>
<style lang="scss" scoped>
#login {
    height: 100vh;
    background-color: #344a5f;
}
.login-wrap {
    width: 330px;
    margin: auto;
}
.menu-tab {
    text-align: center;
    li{
        display: inline-block;
        width: 88px;
        line-height: 36px;
        font-size: 14px;
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
    .current {
        background-color: rgba(0, 0, 0, .1);
    }
}
.login-form {
    margin-top: 29px;
    label {
        display: block;
        margin-bottom: 3px;
        font-size: 14px;
        color:#fff;
    }
    .item-form {
        margin-bottom: 13px;
    }
    .block {
        display: block;
        width: 100%;
    }
    .login-btn { margin-top: 19px;}
}
</style>
