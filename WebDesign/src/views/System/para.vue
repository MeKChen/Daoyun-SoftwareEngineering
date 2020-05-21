<template>
    <div>   
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            
            <el-row :gutter="16">
                <div class="dataButton">
                    
                    <el-col :span="1">
                        <el-button type="primary" @click="paraDia = true">新增</el-button>
                    </el-col>
                </div>               
            </el-row>
            <el-row>
                <el-col :span="24" class="dataInf">系统参数</el-col>
            </el-row>
        </el-form>
        
        <!-- 表格-->
        <el-table
          :data="tableData"
          stripe
          border
          style="width: 100%"
          @selection-change="selsChange">
          <el-table-column
            type="selection"
            width="50">
          </el-table-column>
          <el-table-column
            prop="kWord"
            label="关键字">
          </el-table-column>
          <el-table-column
            prop="kValue"
            label="值">
          </el-table-column>
          <el-table-column
            prop="kRemark"
            label="备注">
          </el-table-column>
          <el-table-column
            prop="createDate"
            label="创建日期">
          </el-table-column>
          <el-table-column
            prop="reviseDate"
            label="修改日期">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="paraDia = true">编辑</el-button>
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
        <ParaDialog :flag="paraDia" @close="close"/>

    </div>
</template>
<script>
import ParaDialog from './dialog/parainfo.vue'
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'parasInfo',
    components: { ParaDialog },
    setup(props, { root } ) {
        /**
         * 数据
         */
        const paraDia = ref(false);
        // 学院选项

        const data = reactive({
        });
       

        // 表格数据
        const tableData = reactive([{
          kWord: 'TimeLimit',
          kValue: '60',
          kRemark: '签到时间限制',
          createDate: '2020-3-26 20:00',
          reviseDate: '2020-4-26 20:00',
        }, 
        {
          kWord: 'Distance',
          kValue: '200',
          kRemark: '签到有效范围半径',
          createDate: '2020-3-26 21:00',
          reviseDate: '2020-4-26 20:05',
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
          paraDia.value = false
        }

        return {
          // ref
          // reactive
          data,
          tableData,
          paraDia,
          // methods
          handleCurrentChange,
          close,
          deleteInfo,
          deleteAll
        }
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
  padding-left: 10px;
}
.pull-l {
  padding-left: 1px;
}
</style>