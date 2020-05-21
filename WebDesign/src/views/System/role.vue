<template>
    <div>   
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            
            <el-row :gutter="16">
                <el-col :span="4">
                    <div class="label-wrap">
                        <div class="wrap-content">
                            <el-select v-model="data.selectValue" placeholder="请选择关键字" style="width: 100%">
                                    <el-option v-for="item in data.option" :key="item.value" :value="item.value" :label="item.label"></el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                   <el-input v-model="inputID" placeholder="请输入参数名称" style="width: 100%"></el-input>
                </el-col>

                <div class="dataButton">
                    <el-col :span="2">
                       <el-button type="primary">搜索</el-button>
                    </el-col>
                    
                    <el-col :span="6">
                        <el-button type="danger" @click="roleDia = true">新增</el-button>
                    </el-col>
                </div>
                 
            </el-row>
            <el-row>
                <el-col :span="24" class="dataInf">角色管理</el-col>
            </el-row>
        </el-form>

        <!-- 表格-->
        <el-table
          :data="tables"
          stripe
          border
          style="width: 100%"
          @selection-change="selsChange">
          <el-table-column
            type="selection"
            width="50">
          </el-table-column>
          <el-table-column
            prop="name"
            label="名称">
          </el-table-column>
          <el-table-column
            prop="rolePower"
            label="角色权限">
          </el-table-column>
          <el-table-column
            prop="powerLevel"
            label="权限等级">
          </el-table-column>
          <el-table-column
            prop="describe"
            label="描述">
          </el-table-column>
          <el-table-column
            prop="createDate"
            label="创建日期">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="roleDia = true">编辑</el-button>
              <el-button type="danger" size="mini" v-if="!scope.row.editing"  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="black-space-30"></div>
        <!-- 底部分页-->
        <el-row>
          <el-col :span="14">
            <el-button type="danger" size="medium" @click="deleteAll">批量删除</el-button>
          </el-col>
          <el-col :span="10" class="pull-l">
            <el-pagination
              background
              @current-change="handleCurrentChange"
              layout="total, prev, pager, next, jumper"
              :total="1000"><!--数据库统计总数返回这里的total-->
            </el-pagination>
          </el-col>
        </el-row>

        <!--弹窗：新增-->
        <RoleDialog :flag="roleDia" @close="close"/>

    </div>
</template>
<script>
import RoleDialog from './dialog/powerinfo.vue'
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'usersInfo',
    components: { RoleDialog },
    setup(props, { root } ) {
        /**
         * 数据
         */
        const roleDia = ref(false);
        // 学院选项

        const data = reactive({
            selectValue: "",
            option: [
                {value: "name", label: "名称"},
                {value: "describe", label: "描述"},
            ]
        });
       
        
        const inputID = ref('');
        const inputName = ref('');

        // 表格数据
        const tableData = reactive([{
          name: '超级管理员',
          rolePower: 'admin',
          powerLevel: '1',
          describe: '',
          createDate: '2020-04-17 16:20'
        }, 
        {
          name: 'test',
          rolePower: 'user',
          powerLevel: '3',
          describe: '普通用户',
          createDate: '2020-05-25 17:30'
        }, 
        ]);

        /**
         * 方法
         */
        //点击跳转页面
        const handleCurrentChange = (val) => {

        }

        const deleteInfo = () => {
          root.confirm({
            content: "将删除该信息, 是否继续?",
            tip: "警告",
            fn: confirmDelete,
          })
        }

        const deleteAll = () => {
          root.confirm({
            content: "将删除所选信息, 是否继续?",
            tip: "警告",
            fn: confirmDelete
          })
        }

        const confirmDelete=() => {
            handleDelete()
        }

        

        const close = () => {
          roleDia.value = false
        }

        return {
          // ref
      
          inputID,
          inputName,
          // reactive
          data,
          tableData,
          roleDia,
          // methods
          handleCurrentChange,
          close,
          deleteInfo,
          deleteAll
        }
    },

     computed: {
      // 模糊搜索
      tables () {
        const inputID = this.inputID
      
        if (inputID) {
          return this.tableData.filter(data => {
            return Object.keys(data).some(key => {
              return String(data[key]).toLowerCase().indexOf(inputID) > -1
            })
          })
        }
        return this.tableData
      },
      
    },

    methods: {
        handleDelete(index, row){ //删除行    
            this.tableData.splice(index, 1)    
        },
        
      
    }
    
}
</script>
<style lang="scss" scoped>
@import "../../styles/config.scss";
.label-wrap {
  @include labelDom(left, 60, 40);
}
.dataInf {
  padding-top: 30px;
  padding-bottom: 5px;
  padding-left: 13px;
  font-size: 20px;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-weight: bold;
}
.dataButton{
  padding-left: 500px;
}
.pull-l {
  padding-left: 1px;
}
</style>