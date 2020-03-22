<template>
    <div>   
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-row :gutter="16">
                <el-col :span="3">
                    <div class="label-wrap stuID">
                        <label for="">学号：</label>
                        <div class="wrap-content">
                            <el-input v-model="inputID" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="3">
                    <div class="label-wrap stuName">
                        <label for="">姓名：&nbsp;&nbsp;</label>
                        <div class="wrap-content">
                            <el-input v-model="inputName" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap stuName">
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

                <el-col :span="5">
                    <div class="label-wrap stuName">
                        <label for="">专业：</label>
                        <div class="wrap-content">
                            <el-select v-model="speValue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in speOptions"
                                    :key="item.speValue"
                                    :label="item.label"
                                    :value="item.speValue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <el-col :span="3">
                    <div class="label-wrap stuName">
                        <label for="">年级：</label>
                        <div class="wrap-content">
                            <el-select v-model="yearValue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in yearOptions"
                                    :key="item.yearValue"
                                    :label="item.label"
                                    :value="item.yearValue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <div  class="stuButton">
                    <el-button type="primary">搜索</el-button>
                    <el-button type="primary" @click="stuDia = true">新增</el-button>
                </div>
            </el-row>
            <el-row>
                <el-col :span="24" class="stuInf">学生信息</el-col>
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
            prop="sID"
            label="学号">
          </el-table-column>
          <el-table-column
            prop="sName"
            label="姓名">
          </el-table-column>
          <el-table-column
            prop="sCol"
            label="学院">
          </el-table-column>
          <el-table-column
            prop="sSpe"
            label="专业">
          </el-table-column>
          <el-table-column
            prop="sYear"
            label="年级">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="stuDia = true">编辑</el-button>
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
        <StuDialog :flag="stuDia" @close="close"/>

    </div>
</template>
<script>
import StuDialog from "./dialog/stuinfo.vue";

import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'stuInfo',
    components: { StuDialog },
    setup(props, { root } ) {
        /**
         * 数据
         */
        const stuDia = ref(false);
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
          sID: '190356236',
          sName: '李田中',
          sCol: '数学与计算机科学学院',
          sSpe: '计算机技术',
          sYear: '大一'
        }, 
        {
          sID: '190352346',
          sName: '吴所长',
          sCol: '数学与计算机科学学院',
          sSpe: '计算机技术',
          sYear: '大一'
        }, 
        {
          sID: '490352236',
          sName: '陈浩南',
          sCol: '环境与资源学院',
          sSpe: '资源环境科学系',
          sYear: '大二'
        }, 
        {
          sID: '523356236',
          sName: '王二哈',
          sCol: '电气工程与自动化学院',
          sSpe: '电气工程专业',
          sYear: '大二'
        }, 
        {
          sID: '190356236',
          sName: '陈先盛',
          sCol: '数学与计算机科学学院',
          sSpe: '计算机技术',
          sYear: '大一'
        }, 
        {
          sID: '490567236',
          sName: '林辈先',
          sCol: '环境与资源学院',
          sSpe: '资源环境科学系',
          sYear: '大一'
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
          stuDia.value = false
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
          stuDia,
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
  &.stuID { @include labelDom(right, 60, 40); }
  &.stuName { @include labelDom(right, 60, 40); }
  &.stuCol { @include labelDom(right, 60, 40); }
  &.stuSpe { @include labelDom(right, 60, 40); }
  &.stuYear { @include labelDom(right, 60, 40); }
}
.stuInf {
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