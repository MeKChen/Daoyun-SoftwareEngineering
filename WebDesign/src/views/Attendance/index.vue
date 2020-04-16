<template>
    <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-row :gutter="16">
                <el-col :span="5">
                    <div class="label-wrap stuID">
                        <label for="">学号：</label>
                        <div class="wrap-content">
                            <el-input v-model="inputID" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap stuName">
                        <label for="">姓名：&nbsp;&nbsp;</label>
                        <div class="wrap-content">
                            <el-input v-model="inputName" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap stuCourse">
                        <label for="">课程名：</label>
                        <div class="wrap-content">
                            <el-input v-model="inputName" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>

                <el-col :span="5">
                    <div class="label-wrap teaName">
                        <label for="">授课教师：</label>
                        <div class="wrap-content">
                            <el-input v-model="inputName" placeholder="请输入" style="width: 100%"></el-input>
                        </div>
                    </div>
                </el-col>
            </el-row>
            <div class="black-space-30"></div>
            <el-row>
                <el-col :span="5">
                    <div class="label-wrap colName">
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
                    <div class="label-wrap speName">
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

                <el-col :span="5">
                    <div class="label-wrap attendStatus">
                        <label for="">签到状态：</label>
                        <div class="wrap-content">
                            <el-select v-model="attValue" placeholder="请选择" style="width: 100%">
                                <el-option
                                    v-for="item in attOptions"
                                    :key="item.attValue"
                                    :label="item.label"
                                    :value="item.attValue">
                                </el-option>
                            </el-select>
                        </div>
                    </div>
                </el-col>
            </el-row>
            <div class="black-space-30"></div>
            <el-row>
                <div  class="opeButton">
                    <el-button type="primary">搜索</el-button>
                </div>
            </el-row>

            <el-row>
                <el-col :span="24" class="attendInf">签到信息</el-col>
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
                label="学号"
                width="100">
            </el-table-column>
            <el-table-column
                prop="sName"
                label="姓名"
                width="100">
            </el-table-column>
            <el-table-column
                prop="sCourse"
                label="课程名"
                width="200">
            </el-table-column>
            <el-table-column
                prop="tName"
                label="授课教师"
                width="100">
            </el-table-column>
            <el-table-column
                prop="sCol"
                label="学院"
                width="200">
            </el-table-column>
            <el-table-column
                prop="sSpe"
                label="专业"
                width="200">
            </el-table-column>
            <el-table-column
                prop="attendTime"
                label="签到时间">
            </el-table-column>
            <el-table-column
                prop="attendPlace"
                label="签到地点"
                width="100">
            </el-table-column>
            <el-table-column
                prop="attendStatus"
                label="签到状态"
                width="100">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="attend">补签</el-button>
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
    </div>
</template>
<script>
import { reactive, ref } from '@vue/composition-api';
export default {
    name: 'attendanceInfo',
    setup(props, { root } ) {
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
        // 签到状态选项
        const attOptions = reactive([{
          attValue: 1,
          label: '已签到'
        }, {
          attValue: 2,
          label: '未签到'
        }]);
        const colValue = ref('');
        const speValue = ref('');
        const attValue = ref('');

        const tableData = reactive([{
          sID: '190352346',
          sName: '吴所长',
          sCourse: '计算机图像处理',
          tName: '陈志杰',
          sCol: '数学与计算机科学学院',
          sSpe: '计算机技术',
          attendTime: '2020-04-15 8:20',
          attendPlace: '东3-204',
          attendStatus: '已签到'
        }]);

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

        return {
            // ref
            colValue,
            speValue,
            attValue,
            // reactive
            colOptions,
            speOptions,
            attOptions,
            tableData,
            // methods
            deleteInfo,
            deleteAll
        }
    }
}
</script>
<style lang="scss" scoped>
@import "../../styles/config.scss";
.label-wrap {
  &.stuID { @include labelDom(right, 60, 40); }
  &.stuName { @include labelDom(right, 80, 40); }
  &.stuCourse { @include labelDom(right, 95, 40); }
  &.teaName { @include labelDom(right, 110, 40); }
  &.colName { @include labelDom(right, 60, 40); }
  &.speName { @include labelDom(right, 83, 40); }
  &.attendStatus { @include labelDom(right, 100, 40); }
}
.attendInf {
  padding-top: 30px;
  padding-bottom: 5px;
  padding-left: 13px;
  font-size: 20px;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-weight: bold;
}
.opeButton{
  padding-left: 1454px;
}
.pull-l {
  padding-left: 1px;
}
</style>