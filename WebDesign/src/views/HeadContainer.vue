<template>
    <div class="app">
        <el-container>
            <el-aside class="app-side app-side-left"
                      :class="isCollapse ? 'app-side-collapsed' : 'app-side-expanded'">
                <checkin-sidebar></checkin-sidebar>
            </el-aside>

            <el-container>
                <el-header class="app-header">
                    <checkin-navbar></checkin-navbar>
                </el-header>

                <el-main class="app-body">
                    <transition name="fade" mode="out-in">
                        <router-view/>
                    </transition>
                </el-main>
                <el-footer class="app-footer">第13小组</el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Navbar from './sidebar/Navbar'
    import CheckinSidebar from './sidebar/Sidebar';
    

    export default {
        name: 'home',
        components: {
            CheckinSidebar, Navbar
            // HelloWorld
        },
        computed: {
            isCollapse () {
                return this.$store.state.app.sidebar.isCollapse
            }
        },
        data () {
            return {
                username: '陈非易'
            }
        },
        methods: {
            handleOpen (key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose (key, keyPath) {
                console.log(key, keyPath);
            },
            handleSelect (key, keyPath) {
                console.log(key, keyPath);
            },
            mounted: function () {
                let user = sessionStorage.getItem('user');
                if (user) {
                    this.username = user;
                }
            }
        }
    }
</script>
