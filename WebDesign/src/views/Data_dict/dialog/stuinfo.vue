<template>
    <el-dialog title="编辑" :visible.sync="studialog_flag" @close="close" width="500px">
        <el-form :model="form">
            <el-form-item label="参数名称：" :label-width="formLabelWidth" class="inputpadding">
            <el-input v-model="form.paramId" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="key值：" :label-width="formLabelWidth" class="inputpadding">
            <el-input v-model="form.paramKey" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="参数类型：" :label-width="formLabelWidth" class="inputpadding">
                <el-select v-model="form.paramType" placeholder="请选择">
                    <el-option label="标准参数" value="c1"></el-option>
                    <el-option label="系统参数" value="c2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="备注：" :label-width="formLabelWidth" class="inputpadding">
            <el-input v-model="form.remarks" autocomplete="off" class="inputWidth"></el-input>
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
    name: 'stuDialogInfo',
    // 单向数据流从父级流向子级，不能反向修改
    props: {
        flag: {
            type: Boolean,
            defult: false
        }
    },
    setup(props, { emit }){ 
        const studialog_flag = ref(false);
        const formLabelWidth = ref('100px');
        const form = reactive({
            paramId: '',
            paramKey: '',
            paramType: '',
            remarks: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
        })

        // watch
        watch(() => {
            studialog_flag.value = props.flag
        })

        // methods
        const close = () => {
            studialog_flag.value = false;
            emit('close', false);
        }

        return {
            // ref
            studialog_flag,
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