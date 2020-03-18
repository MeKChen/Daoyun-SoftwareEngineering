<template>
    <div id="main-wrap">
        <div class="main-content">
            <div class="content">
            <router-view />
            </div>
        </div>     
    </div>
</template>
<script>
import { ref, reactive } from '@vue/composition-api';
export default {
    name: 'layoutMain',
    setup(props, { root }){}
}
</script>
<style lang="scss" scoped>
@import "../../../styles/config.scss";
#main-wrap {
    height: 100vh;
    /*position: fixed;
    left: $navMenu;
    top: 70px;
    right: 0;
    bottom: 0;
    border: 30px solid #f7f7f7;
    border-bottom: none;
    background-color: #fff;
    -webkit-box-sizing: border;*/
}
.main-content {
    width: 100%;
    height: 100%;
    padding-top: $layoutHeader + 30;
    padding-left: $navMenu + 30;
    padding-right: 30px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
.content {
    width: 100%;
    height: 100%;
    padding: 30px 30px 0 30px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    background-color: #fff;
}
</style>