<template>
    <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-row :gutter="16">
                <el-col :span="3">
                    <div class="label-wrap teaID">
                        <label for="">工号：</label>
                        <div class="wrap-content">
                            <el-input v-model="inputID" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="3">
                    <div class="label-wrap teaName">
                        <label for="">姓名：&nbsp;&nbsp;</label>
                        <div class="wrap-content">
                            <el-input v-model="inputName" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap teaCol">
                        <label for="">学院：</label>
                        <div class="wrap-content">
                            <el-select v-model="colValue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in colOptions"
                                    :key="item.colValue"
                                    :label="item.label"
                                    :value="item.colValue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <div  class="stuButton">
                    <el-button type="primary">搜索</el-button>
                    <el-button type="primary" @click="teaDia = true">新增</el-button>
                </div>
            </el-row>
            <el-row>
                <el-col :span="24" class="teaInf">教师信息</el-col>
            </el-row>
        </el-form>

        <!-- 表格-->
        <el-table
            :data="tableData"
            stripe
            border
            style="width: 100%">
            <el-table-column
                type="selection"
                width="50">
            </el-table-column>
            <el-table-column
                prop="tID"
                label="工号">
            </el-table-column>
            <el-table-column
                prop="tName"
                label="姓名">
            </el-table-column>
            <el-table-column
                prop="tCol"
                label="学院">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="teaDia = true">编辑</el-button>
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
        <TeaDialog :flag="teaDia" @close="close"/>

    </div>
</template>
<script>
import TeaDialog from "./dialog/teainfo.vue";
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'teaInfo',
    components: { TeaDialog },
    setup(props, { root } ) {
        /**
         * 数据
         */
        const teaDia = ref(false);
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
        const colValue = ref('');
        const inputID = ref('');
        const inputName = ref('');

        // 表格数据
        const tableData = reactive([{
          tID: '190356236',
          tName: '李田中',
          tCol: '数学与计算机科学学院',
        }, 
        {
          tID: '190352346',
          tName: '吴所长',
          tCol: '数学与计算机科学学院',
        }, 
        {
          tID: '490352236',
          tName: '陈浩南',
          tCol: '环境与资源学院',
        }, 
        {
          tID: '523356236',
          tName: '王二哈',
          tCol: '电气工程与自动化学院',
        }, 
        {
          tID: '190356236',
          tName: '陈先盛',
          tCol: '数学与计算机科学学院',
        }, 
        {
          tID: '490567236',
          tName: '林辈先',
          tCol: '环境与资源学院',
        }]);

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
          teaDia.value = false
        }

        return {
          // ref
          colValue,
          inputID,
          inputName,
          // reactive
          colOptions,
          tableData,
          teaDia,
          // methods
          handleCurrentChange,
          close,
          deleteInfo,
          deleteAll
        }
    },
}
</script>
<style lang="scss" scoped>
@import "../../styles/config.scss";
.label-wrap {
  &.teaID { @include labelDom(right, 60, 40); }
  &.teaName { @include labelDom(right, 60, 40); }
  &.teaCol { @include labelDom(right, 60, 40); }
}
.teaInf {
  padding-top: 30px;
  padding-bottom: 5px;
  padding-left: 13px;
  font-size: 20px;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-weight: bold;
}
.stuButton{
  padding-left: 1384px;
}
.pull-l {
  padding-left: 1px;
}
</style>