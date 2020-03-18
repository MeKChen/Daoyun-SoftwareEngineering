<template>
    <div id="header-wrap">
        <div class="pull-left header-title">到云后台管理系统</div>
        <div class="pull-right">
            <div class="user-info pull-left">
                <img src="../../../assets/images/face.jpg" alt="">
                管理员
            </div>
            <div class="header-icon pull-left" @click="exit">
                <svg-icon iconClass="exit" className="exit" />
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'layoutHeader',
    setup(props, { root }){
        const username = computed(() => root.$store.state.app.username);
        // 退出
        const exit = () => {
            root.$store.dispatch('app/exit').then(() => {
                root.$router.push({
                    name: 'Login'
                })
            })
        }
        return {
            username,
            exit
        }
    }
}
</script>
<style lang="scss" scoped>
@import "../../../styles/config.scss";
#header-wrap {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 70px;
    background-color: #344a5f;
    //-webkit-box-shadow: 0 3px 16px 0 rgba(0, 0, 0, .1);
    line-height: 70px;
}
.header-icon {
    padding: 0 32px;
    svg { 
        margin-bottom: -6px;
        font-size: 25px;
        cursor: pointer; 
        fill: #fff;    
    }
}
.header-title {
    margin-top: -2px;
    padding: 0 32px;
    fill: currentColor;
    color: #fff;
    font-size: 30px;
}
.user-info {
    height: 100%;
    padding: 0 32px;
    border-right: 1px solid #ededed;
    fill: currentColor;
    color: #fff;
    img {
        display: inline-block;
        margin-bottom: -12px;
        margin-right: 18px;
        border-radius: 50px;
    }
}
</style>