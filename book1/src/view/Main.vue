<template>
  <el-container>
    <el-header>图书借阅管理系统</el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu router background-color="#2B4B6B" text-color="#fff"
                 active-text-color="#409EFF" unique-opened>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-s-tools"></i>
              <span>馆藏类别管理</span>
            </template>
            <el-menu-item index="/category">
              <i class="el-icon-menu"></i>
              <span slot="title">类别信息管理</span>
            </el-menu-item>=
            <el-menu-item index="/book">
              <i class="el-icon-reading"></i>
              <span slot="title">馆藏图书管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-cooperation"></i>
              <span>借阅信息管理</span>
            </template>
            <el-menu-item index="/borrow">
              <i class="el-icon-files"></i>
              <span slot="title">借阅信息管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-s-platform"></i>
              <span>系统信息管理</span>
            </template>
            <el-menu-item index="/users">
              <i class="el-icon-user"></i>
              <span slot="title">用户信息管理</span>
            </el-menu-item>
            <el-menu-item index="/logs">
              <i class="el-icon-document"></i>
              <span slot="title">日志信息管理</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <!-- 路由占位符 -->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
    }
  }
}
</script>

<style scoped>
.el-header{
  background-color:#2B4B6B;
  display:flex;
  justify-content:space-between;padding-left:10px;
  align-items:center;
  color:white;
  font-size:18px;
}

.el-aside{
  color:white;
  background-color:#2B4B6B;
}

.el-main{
  background-color:aliceblue;
}

.el-container{
  height:100%;}
</style>
