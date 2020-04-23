<template>
    <div>   
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-row :gutter="16">
                <el-col :span="3">
                    <div class="label-wrap stuID">
                        <div class="wrap-content">
                            <el-input v-model="inputID" placeholder="请输入参数名称" style="width: 300%"></el-input>
                        </div>
                    </div>
                </el-col>

                <div class="dataButton">
                    <el-button type="primary">搜索</el-button>
                    <el-button type="primary" @click="dataDia = true">新增</el-button>
                </div>
            </el-row>
            <el-row>
                <el-col :span="24" class="dataInf">数据字典</el-col>
            </el-row>
        </el-form>

        <!-- 表格-->
        <el-table
          :data="tables"
          stripe
          border
          style="width: 100%">
          <el-table-column
            type="selection"
            width="50">
          </el-table-column>
          <el-table-column
            prop="paramsId"
            label="参数名称">
          </el-table-column>
          <el-table-column
            prop="paramKey"
            label="key值">
          </el-table-column>
          <el-table-column
            prop="paramType"
            label="参数类型">
          </el-table-column>
          <el-table-column
            prop="remarks"
            label="备注">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="dataDia = true">编辑</el-button>
              <el-button type="danger" size="mini" @click="deleteInfo">删除</el-button>
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
        <DataDialog :flag="dataDia" @close="close"/>

    </div>
</template>
<script>
import DataDialog from "./dialog/stuinfo.vue";
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'stuInfo',
    components: { DataDialog },
    setup(props, { root } ) {
        /**
         * 数据
         */
        const dataDia = ref(false);
        // 学院选项
        const colOptions = reactive([{
          colValue: 1,
          label: '数学与计算机科学学院'
        }, {
          colValue: 2,
          label: '电气工程与自动化学院'
        }, {
          colValue: 3,
          label: '环境与资源学院'
        }]);
        // 专业选项
        const speOptions = reactive([{
          speValue: 1,
          label: '计算机技术'
        }, {
          speValue: 2,
          label: '软件工程'
        }, {
          speValue: 3,
          label: '数学系'
        }]);
        // 学年选项
        const yearOptions = reactive([{
          yearValue: 1,
          label: '大一'
        }, {
          yearValue: 2,
          label: '大二'
        }, {
          yearValue: 3,
          label: '大三'
        }, {
          yearValue: 4,
          label: '大四'
        }]);
        const colValue = ref('');
        const speValue = ref('');
        const yearValue = ref('');
        const inputID = ref('');
        const inputName = ref('');

        // 表格数据
        const tableData = reactive([{
          paramsId: '角色类型',
          paramKey: 'roleType',
          paramType: '标准参数',
          remarks: ' ',
        }, 
        {
          paramsId: '用户角色',
          paramKey: 'userRole',
          paramType: '系统参数',
          remarks: ' ',
        }, 
        {
          paramsId: '用户状态',
          paramKey: 'userState',
          paramType: '系统参数',
          remarks: ' ',
        }, 
        {
          paramsId: '数据类型',
          paramKey: 'dataType',
          paramType: '系统参数',
          remarks: ' ',
        }, 
        {
          paramsId: '性别',
          paramKey: 'gender',
          paramType: '标准参数',
          remarks: ' ',
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
            fn: confirmDelete
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

        }

        const close = () => {
          dataDia.value = false
        }

        return {
          // ref
          colValue,
          speValue,
          yearValue,
          inputID,
          inputName,
          // reactive
          yearOptions,
          speOptions,
          colOptions,
          tableData,
          dataDia,
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
      }
    },
}
</script>
<style lang="scss" scoped>
@import "../../styles/config.scss";
.label-wrap {
  &.stuID { @include labelDom(right, 60, 40); }
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