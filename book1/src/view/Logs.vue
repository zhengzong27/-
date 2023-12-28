<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏类别管理</el-breadcrumb-item>
      <el-breadcrumb-item>类别信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="tableData">
      <el-table-column label="操作时间" prop="opertime"/>
      <el-table-column label="操作描述" prop="ddesc"/>
      <el-table-column label="操作ip" prop="ip"/>
      <el-table-column label="操作方法" prop="methods"/>
    </el-table>
    <!--    分页组件-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pager.page"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="pager.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pager.total">
    </el-pagination>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      tableData:[],
      pager:{ //定义分页对象
        page:1,
        size:5,
        total:0
      },

    }
  },
  methods: {
    handleCurrentChange(val){  //处理改变当前从第几条分页
      this.pager.page=val;
      this.search();
    },
    handleSizeChange(val){  //处理最大的页数，改变每页现实的最大条数
      this.pager.size=val;
      this.search();
    },
    search() {
      this.$http.get('http://127.0.0.1/logs/list',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
        }
      }).then(res => {
        if (res.data.code == 200) {
          this.tableData = res.data.data.data;
          this.pager.total=res.data.data.total;
        }
      }).catch(error => {
        this.$message.error('网络异常')
      })
    },

  },
  mounted() {
    this.search();
  }
}

</script>
<style scoped>
.el-breadcrumb {
  margin: 10px;
}
</style>
