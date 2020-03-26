<template>
    <el-dialog title="编辑" :visible.sync="teadialog_flag" @close="close" width="500px">
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
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>
import {
  reactive,
  ref,
  watch
} from "@vue/composition-api";
export default {
    name: 'teaDialogInfo',
    // 单向数据流从父级流向子级，不能反向修改
    props: {
        flag: {
            type: Boolean,
            defult: false
        }
    },
    setup(props, { emit }){ 
        const teadialog_flag = ref(false);
        const formLabelWidth = ref('70px');
        const form = reactive({
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
        })

        // watch
        watch(() => {
            teadialog_flag.value = props.flag
        })

        // methods
        const close = () => {
            teadialog_flag.value = false;
            emit('close', false);
        }

        return {
            // ref
            teadialog_flag,
            formLabelWidth,
            // reactive
            form,
            // methods
            close
        }
    }
}
</script>
<style lang="scss" scoped>
.inputWidth {
    width: 150px;
}
</style>