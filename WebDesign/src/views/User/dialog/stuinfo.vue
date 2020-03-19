<template>
    <el-dialog title="新增" :visible.sync="studialog_flag" @close="close" width="500px">
        <el-form :model="form">
            <el-form-item label="学号：" :label-width="formLabelWidth" class="inputpadding">
            <el-input v-model="form.stuID" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="姓名：" :label-width="formLabelWidth" class="inputpadding">
            <el-input v-model="form.stuname" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="学院：" :label-width="formLabelWidth" class="inputpadding">
                <el-select v-model="form.col" placeholder="请选择">
                    <el-option label="数学与计算机科学学院" value="c1"></el-option>
                    <el-option label="环境与资源学院" value="c2"></el-option>
                    <el-option label="电气工程与自动化学院" value="c3"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="专业：" :label-width="formLabelWidth" class="inputpadding">
                <el-select v-model="form.spe" placeholder="请选择">
                    <el-option label="计算机技术" value="s1"></el-option>
                    <el-option label="软件工程" value="s2"></el-option>
                    <el-option label="数学系" value="s3"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="年级：" :label-width="formLabelWidth" class="inputpadding">
                <el-select v-model="form.year" placeholder="请选择">
                    <el-option label="大一" value="y1"></el-option>
                    <el-option label="大二" value="y2"></el-option>
                    <el-option label="大三" value="y3"></el-option>
                    <el-option label="大四" value="y4"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>
export default {
    name: 'stuDialogInfo',
    data(){
        return {
            studialog_flag: false,
            form: {
                stuID: '',
                stuname: '',
                col: '',
                spe: '',
                year: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
                },
            formLabelWidth: '70px'
            }
        },
    methods: {
        close(){
            this.studialog_flag = false;
            this.$emit('close', false);
        }
    },
    // 单向数据流从父级流向子级，不能反向修改
    props: {
        flag: {
            type: Boolean,
            defult: false
        }
    },
    watch: {
        flag: {
            handler(newValue, oldValue){
                this.studialog_flag = newValue
            }
        }
    }
}
</script>
<style lang="scss" scoped>
.inputWidth {
    width: 150px;
}
.inputpadding {
    padding-bottom: 30px;
}
</style>