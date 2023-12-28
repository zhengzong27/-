<template>
  <!-- template模板编写的是ui组件，网页代码template标签中vue2.0规定只有一个根标签存在-->
  <el-row>
    <el-button>默认按钮</el-button>
    <el-button type="primary">主要按钮</el-button>
    <el-button type="success">成功按钮</el-button>
    <el-button type="info">信息按钮</el-button>
    <el-button type="warning">警告按钮</el-button>
    <el-button type="danger">危险按钮</el-button>
    {{categories}}
  </el-row>
</template>
<!--行为区，主要编写脚本代码，javascript代码编辑区-->
<script>
export default {
  data(){
    return{
      //其实定义变量的
      categories:[]
    }
  },
  mounted() {//声明周期函数，该函数特点是浏览器渲染vue组件使立马执行
    this.list();
  },
  methods:{//定义自己的js方法
    list(){
      this.$http.get('http://localhost:8080/books/category/list')
        .then(response =>{
          this.categories=response.data;
        })
    }

  }
}
</script>
<!--样式区-->
<style scoped>

</style>
