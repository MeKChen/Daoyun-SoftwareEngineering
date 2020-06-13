<template>
    <div>
        <page-header title="学生信息管理"></page-header>
        <container-search-group>
            <el-form :inline="true" :model="search" ref="courseSearchForm" size="mini" >
                <el-form-item label="课程号" prop="number">
                    <el-input v-model="search.query.courseNumber.value" placeholder="课程号"></el-input>
                </el-form-item>
                <el-form-item label="课程名称" prop="name">
                    <el-input v-model="search.query.courseName.value" placeholder="课程名称"></el-input>
                </el-form-item>
                <el-form-item label="授课教师" prop="teacher">
                    <el-input v-model="search.query.courseTeache.value" placeholder="授课教师"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                    <el-button @click="resetForm('courseSearchForm')">重置</el-button> 
                </el-form-item>
            </el-form>
        </container-search-group>

        <div class="tool-wrapper">
            <el-button-group>
                <el-button type="primary" icon="el-icon-plus" @click="createOpen" size="mini">新增</el-button>
                <el-button type="primary" icon="el-icon-edit" @click="openEdit" size="mini">编辑</el-button>
<!--                <el-button type="primary" @click="assignRoleOpen" size="mini"><svg-icon icon-class="permission" />用户角色</el-button>-->
                <el-button type="primary" @click="resetPassword" size="mini">重置密码</el-button>
                <el-button type="danger" icon="el-icon-delete" @click="removeOpen" size="mini">删除</el-button>
            </el-button-group>
        </div>

        <student-table :search="search" :rowDblClick="showOpen" ref="StudentTable"></student-table>
        <Pagination
                :size-change="handleSizeChange"
                :pageData="search"
                :current-change="handleCurrentChange">
        </Pagination>
        <student-edit ref="StudentEdit"></student-edit>
        <student-show ref="StudentShow"></student-show>
    </div>
</template>

<script>
    import ContainerSearchGroup from '../../../components/ContainerSearchGroup';
    import StudentTable from './StudentTable';
    import Pagination from '../../../components/Pagination';
    import StudentEdit from './StudentEdit';
    import PageHeader from '../../../components/PageHeader';
    import {showMessage} from '@/utils/message';
    import StudentShow from './StudentShow';

    export default {
        name: 'StudentIndex',
        components: {StudentShow, PageHeader, StudentEdit, Pagination, StudentTable, ContainerSearchGroup},
        data () {
            return {
                search: {
                    query: {
                        courseNumber: {
                            type: 'and',
                            operation: 'like',
                            key: 'courseNumber',
                            value: ''
                        },
                        courseName: {
                            type: 'and',
                            operation: 'like',
                            key: 'courseName',
                            value: ''
                        },
                        courseTeache: {
                            type: 'and',
                            operation: 'like',
                            key: 'courseTeache',
                            value: ''
                        }
                    },
                    sort: {
                        prop: 'courseNumber', order: 'ascending'
                    },
                    page: this.$store.state.app.default.page,
                    pageSize: this.$store.state.app.default.pageSize,
                    total: 0
                }

            }
        },
        methods: {
            // 获取选择列
            getSelected () {
                var row = this.$refs.StudentTable.getSelected();
                if (row === undefined) {
                    showMessage('error', '请先选择一个用户');
                }
                console.log(row);
                return row;
            },

            // 打开创建用户页面
            createOpen () {
                // this.$refs.userEdit.openCreate();
            },

            // 打开编辑用户页面
            openEdit () {
                var row = this.getSelected();
                if (row === undefined) {
                    return;
                }
                this.$refs.StudentEdit.openEdit(row);
            },

            // 删除用户
            removeOpen () {
                // var row = this.getSelected();
                // if (row === undefined) {
                //     return;
                // }
                // const _this = this;
                // showSimpleConfirm('是否确定删除用户' + row.name + '?', function () {
                //     console.log('confirm');
                //     userAPI.remove(row.userID).then(res => {
                //         showSuccess('删除用户成功!');
                //         _this.load();
                //     })
                // }, function () {
                //     console.log('cancel');
                //     showInfo('取消删除');
                // })
            },

            // 打开查看用户页面
            showOpen (row) {
                console.log(row);
                this.$refs.StudentShow.open(row);
            },
            // 加载用户列表
            load () {
                // console.log('init list');
                this.$refs.StudentTable.load();
            },

            // 查询
            query () {
                this.search.page = 1;
                this.load();
            },

            // 页面显示条目数量切换
            handleSizeChange (val) {
                this.search.pageSize = val;
                this.search.page = 1;
                this.load();
            },

            // 页面切换
            handleCurrentChange (val) {
                this.search.page = val;
                this.load();
            },

            onSubmit () {
                console.log('submit!');
            },

            resetForm (formName) {
                // console.log()
                this.$refs[formName].resetFields();
            },

            // 重置密码
            resetPassword () {
                // var row = this.getSelected();
                // if (row === undefined) {
                //     return;
                // }
                // const _this = this;
                // showSimpleConfirm('是否确定重置用户' + row.name + '的密码?重置后密码为000000', function () {
                //     userAPI.resetPassword(row.userID).then(res => {
                //         showSuccess('重置密码成功!');
                //     })
                // }, function () {
                //     showInfo('取消重置密码');
                // })
            },

            assignRoleOpen () {
                var row = this.getSelected();
                if (row === undefined) {
                    return;
                }
                this.$refs.userRoleAssign.open(row);
            }
        }
    }
</script>

<style scoped>

</style>
