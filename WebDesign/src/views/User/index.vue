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
                            <el-select v-model="colvalue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in coloptions"
                                    :key="item.colvalue"
                                    :label="item.label"
                                    :value="item.colvalue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap stuName">
                        <label for="">专业：</label>
                        <div class="wrap-content">
                            <el-select v-model="spevalue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in speoptions"
                                    :key="item.spevalue"
                                    :label="item.label"
                                    :value="item.spevalue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <el-col :span="3">
                    <div class="label-wrap stuName">
                        <label for="">年级：</label>
                        <div class="wrap-content">
                            <el-select v-model="yearvalue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in yearoptions"
                                    :key="item.yearvalue"
                                    :label="item.label"
                                    :value="item.yearvalue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>

                <div  class="stuButton">
                    <el-button type="primary">搜索</el-button>
                    <el-button type="primary">新增</el-button>
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
              <el-button type="primary" size="mini">编辑</el-button>
              <el-button type="danger" size="mini">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="black-space-30"></div>
        <!-- 底部分页-->
        <el-row>
          <el-col :span="14">
            <el-button type="danger" size="medium">批量删除</el-button>
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
    </div>
</template>
<script>
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'stuInfo',
    setup(props) {
        // 学院选项
        const coloptions = reactive([{
          colvalue: 1,
          label: '数学与计算机科学学院'
        }, {
          colvalue: 2,
          label: '电气工程与自动化学院'
        }, {
          colvalue: 3,
          label: '环境与资源学院'
        }]);
        // 专业选项
        const speoptions = reactive([{
          spevalue: 1,
          label: '计算机技术'
        }, {
          spevalue: 2,
          label: '软件工程'
        }, {
          spevalue: 3,
          label: '数学系'
        }]);
        // 学年选项
        const yearoptions = reactive([{
          yearvalue: 1,
          label: '大一'
        }, {
          yearvalue: 2,
          label: '大二'
        }, {
          yearvalue: 3,
          label: '大三'
        }, {
          yearvalue: 4,
          label: '大四'
        }]);
        const colvalue = ref('');
        const spevalue = ref('');
        const yearvalue = ref('');
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
          sSpe: '计算机技术',
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

        //点击跳转页面
        const handleCurrentChange = (val) => {

        }

        return {
            handleCurrentChange,
            yearoptions,
            speoptions,
            coloptions,
            colvalue,
            spevalue,
            yearvalue,
            inputID,
            inputName,
            tableData
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
  padding-left: 1404px;
}
.pull-l {
  padding-left: 52px;
}
</style>