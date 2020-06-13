<template>
    <div>
        <div class="sidebar-info">
            <!-- <img src="@/assets/sidebar-logo.png" :height="isCollapse ? '15' : '30'"> -->
        </div>
        <el-menu
             mode="vertical"
             background-color="#304156"
             text-color="#bfcbd9"
             :default-active="$route.path"
             :collapse="isCollapse"
             :unique-opened=true
             router
             active-text-color="#66b1ff">
            <template v-for="item in routes">
                <!--有二级菜单-->
                <el-submenu v-if="!item.hidden && countChildNum(item) > 1 " :index="item.path" :key="item.path" >
                    <!--一级菜单的内容-->
                    <template slot="title"> 
                        <icon :name="item.meta.icon" :w="16" :h="16"></icon>
                        <span>{{item.meta.title}}</span>
                    </template>
                    <!--二级菜单内容-->
                    <el-menu-item v-for="val in item.children" :index="val.path" :key="val.path">
                        <template slot="title">
                            <span>{{val.meta.title}}</span>
                        </template>
                    </el-menu-item>
                </el-submenu>
                <!--只有一级菜单-->
                <el-menu-item v-if="!item.hidden && countChildNum(item)===1" :index="item.children[0].path" :key="item.path">
                    <template slot="title">
                        <icon :name="item.children[0].meta.icon" :w="16" :h="16"></icon>
                        <span>{{item.children[0].meta.title}}</span>
                    </template>
                </el-menu-item>
            </template>
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
