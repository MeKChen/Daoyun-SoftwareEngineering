<template>
    <div>
    <el-table
            :data="studentInfoList"
            @row-click="rowClick"
            @row-dblclick="rowDblClick"
            v-loading="tableLoading"
            fit highlight-current-row stripe
            size="mini"
            :default-sort="{prop: 'courseNumber', order: 'ascending'}">
        <el-table-column prop="selectRow" align="center" fixed type="selection" width="50"></el-table-column>
        <el-table-column prop="courseNumber" label="课程号" align="center" width="70"></el-table-column>
        <el-table-column prop="courseName" label="课程名" align="center" width="90"></el-table-column>
        <el-table-column prop="courseTeacher" label="授课教师" align="center" width="100"></el-table-column>
        <el-table-column prop="courseTime" label="上课时间" align="center"></el-table-column>
        <el-table-column prop="courseLocation" label="上课地点" align="center"></el-table-column>
    </el-table>
    </div> 
</template>

<script>
    export default {
        name: 'StudentTable',
        data () {
            return {
                tableList: [
                    {
                    courseNumber: '0009',
                    courseName: 'A',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0004',
                    courseName: 'B',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0008',
                    courseName: 'C',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0007',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0005',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0001',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0002',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0006',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0010',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0008',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0011',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0012',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0013',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0014',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0015',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0016',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0017',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }, {
                    courseNumber: '0018',
                    courseName: 'D  ',
                    courseTeacher: '陈老师',
                    courseTime: '2019-09-08',
                    courseLocation: '上海市普陀区金沙江路 1518 弄'
                }],
                tableLoading: false,
                selectRow: undefined
            }
        },
        props: {
            rowDblClick: {
                type: Function,
                default () {
                }
            },
            search: {type: Object}
            // searchText:""
        },
        computed: {
            studentInfoList () {
                let page = this.search.page !== undefined ? this.search.page : 1;
                let pageSize = this.search.pageSize !== undefined ? this.search.pageSize : 15;
                // console.log(page,pageSize,total);
                // let tmpList = this.tableList.filter((item)=>{
                //     return this.checkStringIn(item.name,this.searchText)
                //         || this.checkStringIn(item.phone,this.searchText)
                // })
                let tmpList = this.tableList;
                let total = tmpList.length;
                this.search.total = total;
                // console.log(total)
                // this.search.total = total;

                return tmpList.slice((page - 1) * pageSize, page * pageSize > total ? total : page * pageSize);
            },
            tableIndex () {
                let page = this.search.page !== undefined ? this.search.page : 1;
                let pageSize = this.search.pageSize !== undefined ? this.search.pageSize : 15;
                return pageSize * (page - 1);
            }
        },
        created () {
            this.load();
        },
        methods: {
            load () {
                this.tableLoading = true;
                this.tableLoading = false;
                // setTimeout(function () {
                //     console.log("aaaa")
                //     // this.search.total = 18;
                //
                //     this.tableLoading = false;
                //
                // },1000);
                // userAPI.list(this.search).then(res => {
                //     this.tableLoading = false;
                    // console.log(res);
                    // this.tableList = res.data.content;
                    // this.search.total = this.tableList.totalElements;
                    // console.log(this.search)
                // })
            },
            getSelected () {
                return this.selectRow;
            },
            rowClick (row) {
                this.selectRow = row;
            }

        }
    }
</script>

<style scoped>

</style>
