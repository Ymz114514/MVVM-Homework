<script setup>
import request from '@/api';
import Index from '../EmployeeManagement/index.vue';

</script>
<template>
        <el-container style="height: 100vh;">
            <el-header>
                <el-row align="middle">
                    <el-col :span=12 style="display: flex; align-items: center;">
                        <el-avatar size="large" shape="square" src="src\assets\logo.jpg"></el-avatar>
                        <h1 style="margin: 0px; margin-left: 20px;">IKUN集中营</h1>
                    </el-col>
                    <el-col :span=3 :offset=9 style="display: flex; align-items: center;">
                        <span style="margin-left: 20px;">欢迎Xxx登录！</span>
                    </el-col>
                </el-row>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu >
                        <el-menu-item index="">员工管理</el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <Index></Index>
                    </el-main>
                    <el-footer height="80px"
                        style="display: flex; flex-direction: column; justify-content: center; align-items: center; color: #ccc;">
                        <span>软工2203 2200770165 杨明璋</span></el-footer>
                </el-container>
            </el-container>
        </el-container>
</template>
