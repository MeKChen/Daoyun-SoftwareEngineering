<template>
    <div>
        <el-row>
                <el-col :span="24" class="dataInf">用户管理</el-col>
            </el-row>
    <el-table
        :data="tableData"
        style="width: 100%"
        row-key="id"
        border
        lazy
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column
        type="selection"
        width="50">
        </el-table-column>
        <el-table-column
        prop="name"
        label="名称"
        width="480">
        </el-table-column>
        <el-table-column
        prop="createDate"
        label="创建日期"
        width="680">
        </el-table-column>
        <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="dataDia = true">编辑</el-button>
              <el-button type="danger" size="mini" v-if="!scope.row.editing"  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        tableData: [{
          id: 1,
          name: '福州大学',
          createDate: '2020-04-17',
          children: [{
              id: 3,
              name: '数学与计算机科学学院',
              createDate: '2020-04-17',
            }, {
              id: 4,
              name: '电气工程与自动化学院',
              createDate: '2020-04-17',
          }]
        }, {
          id: 2,
          name: '厦门大学',
          createDate: '2020-04-17',
          children: [{
              id: 5,
              name: '环境与资源学院',
              createDate: '2020-04-17',
            }, {
              id: 6,
              name: '经济与管理学院',
              createDate: '2020-04-17',
          }]
        }]
      }
    },
    
  }
</script>
<style lang="scss" scoped>
.dataInf {
  padding-top: 10px;
  padding-bottom: 15px;
  padding-left: 13px;
  font-size: 20px;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-weight: bold;
}
</style>
