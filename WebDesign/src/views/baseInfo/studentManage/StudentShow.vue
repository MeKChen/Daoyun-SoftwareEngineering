<template>
    <el-dialog title="学生信息查看" :visible.sync="dialogVisible" >
        <el-form size="small" :model="studentData" label-position="right" label-width="90px" class="edit-dialog-form" v-loading="showLoading">

            <el-row :gutter="20">
                <el-col :md="12" :sm="24">
                    <el-form-item label="课程号:" prop="courseNumber">
                        {{studentData.courseNumber}}
                    </el-form-item>
                </el-col>
                <el-col :md="12" :sm="24">
                    <el-form-item label="课程名:" prop="courseName">
                        {{studentData.courseName}} 
                    </el-form-item>
                </el-col>

                <el-col :md="12" :sm="24">
                    <el-form-item label="授课教师:" prop="courseTeacher">
                        {{studentData.courseTeacher}}
                    </el-form-item>
                </el-col>
                <el-col :md="12" :sm="24">
                    <el-form-item label="上课时间:" prop="courseTime">
                        {{studentData.courseTime}}
                    </el-form-item>
                </el-col>
                <el-col :md="12" :sm="24">
                    <el-form-item label="上课地点:" prop="courseLocation">
                        {{studentData.courseLocation}}
                    </el-form-item>
                </el-col>

            </el-row>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible=false">{{"关闭"}}</el-button>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        name: 'StudentShow',
        data () {
            return {
                studentData: {
                    courseNumber: '0009',
                    courseName: 'A',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                },
                dialogVisible: false,
                showLoading: false
            }
        },
    methods: {
        init (ID) {
            this.loadingStart();
            console.log('用户信息初始化 ID:', ID);
            // userAPI.get(ID).then(res=>{
            //     this.userData = res.data;
            //     this.loadingEnd();
            // })
            this.loadingEnd();
        },
        open (row) {
            this.init(row.uid);
            this.dialogVisible = true;
        },
        loadingStart () {
            this.showLoading = true;
        },
        loadingEnd () {
            this.showLoading = false;
        }
    }
    }
</script>

<style scoped>

</style>
