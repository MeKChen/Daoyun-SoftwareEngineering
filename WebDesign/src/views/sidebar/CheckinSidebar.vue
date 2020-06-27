<template>
    <div>
        <el-menu
            default-active="1"
            background-color="#304156"
            text-color="#fff"
            class="el-menu-vertical-demo"
            :unique-opened=true
            :collapse="isCollapse">
            <el-menu-item index="1" @click.native.prevent="handleUserManage">
                <i class="el-icon-user"></i>
                <span slot="title">用户管理</span>
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title">
                <i class="el-icon-setting"></i>
                <span>系统管理</span>
                </template>
                <el-menu-item index="2-1" @click.native.prevent="handleMenuManage">菜单管理</el-menu-item>
                <el-menu-item index="2-2" @click.native.prevent="handleDD">数据字典</el-menu-item>
                <el-menu-item index="2-3" @click.native.prevent="handleRoleManage">角色管理</el-menu-item>  
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">
                <i class="el-icon-document"></i>
                <span>异常页面</span>
                </template>
                <el-menu-item index="3-1" @click.native.prevent="handlefzf">找不到页面</el-menu-item>
                <el-menu-item index="3-2" @click.native.prevent="handlefzt">无权访问</el-menu-item>
                <el-menu-item index="3-3" @click.native.prevent="handlefzz">服务器出错</el-menu-item>  
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
    import {routes} from '@/router'

    export default {
        name: 'sidebar',
        computed: {
            isCollapse () {
                return this.$store.state.app.sidebar.isCollapse
            },
            routes () {
                return this.$store.state.app.routes
            }
        },
        data () {
            return {
            }
        },
        created () {
            console.log(routes);
        },
        methods: {
            handleOpen (key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose (key, keyPath) {
                console.log(key, keyPath);
            },
            handleUserManage () {
                this.$router.push({path: '/userManage/index'})
            },
            handleMenuManage () {
                this.$router.push({path: '/systemManage/menu'})
            },
            handleDD () {
                this.$router.push({path: '/dataDictionary/index'})
            },
            handleRoleManage () {
                this.$router.push({path: '/roleManage/index'})
            },
            handlefzf () {
                this.$router.push({path: '/errPage/404'})
            },
            handlefzt () {
                this.$router.push({path: '/errPage/403'})
            },
            handlefzz () {
                this.$router.push({path: '/errPage/500'})
            },
            countChildNum (item) {
                if (item.children === undefined) return 0;
                let count = 0;
                for (let t of item.children) {
                    if (!t.hidden) { count++; }
                }
                return count;
            }
        }
    }
</script>

<style rel="stylesheet/less" lang="less" scoped>
    .el-menu{
        .svg-icon{
            margin-right: 10px;
        }
    }
</style>
