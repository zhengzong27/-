<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏类别管理</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="name" clearable @change="search" placeholder="请输入借阅人姓名查询"></el-input>
      </el-col>
      <el-col :span="6">
        <el-input v-model="bname" clearable @change="search" placeholder="请输入图书名称查询"></el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" icon="el-icon-plus" @click="openBorrowDialog" >借阅</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData">
      <el-table-column label="图书名称" prop="bname"></el-table-column>
      <el-table-column label="借阅人" prop="name"></el-table-column>
      <el-table-column label="联系方式" prop="tel"></el-table-column>
      <el-table-column label="借出时间" prop="otime"></el-table-column>
      <el-table-column label="归还时间" prop="itime"></el-table-column>
      <el-table-column label="借阅数量" prop="quantity"></el-table-column>
      <el-table-column label="状态" prop="stat">
        <template v-slot="scope">
          <el-tag type="danger" v-if="scope.row.stat==0">已借出</el-tag>
          <el-tag type="success" v-if="scope.row.stat==1">已归还</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template v-slot="scope">
          <el-button type="danger" size="small" v-show="scope.row.stat==0" @click="backBook(scope.row)">归还</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pager.page"
      :page-sizes="[2, 3, 5, 7]"
      :page-size="pager.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pager.total">
    </el-pagination>
<!--借阅功能对话框-->
    <el-dialog title="借阅" :visible.sync="borrowDialogFlag">
      <el-form :model="borrow" :rules="rules" ref="borrow">
        <el-form-item label="馆藏图书" prop="bid">
          <el-select v-model="borrow.bid">
            <el-option v-for="(item,index) in options" :label="item.bname"
                       :value="item.bid" :key="index"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="借阅人" prop="name">
          <el-input v-model="borrow.name" placeholder="请输入借阅人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="tel">
          <el-input v-model="borrow.tel" placeholder="请输入联系方式" ></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="borrow.quantity" placeholder="请输入借阅数量" ></el-input>
        </el-form-item>
      </el-form>
      <!--      按钮  -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="borrowDialogFlag=!borrowDialogFlag">关闭</el-button>
        <el-button type="primary" @click="borrowBook">保存</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script>
export default {
  data(){
    return{
      rules:{
        bid: [
          {required: true,
            message: '请选择借阅图书名称',
            trigger: 'blur'
          }],
        name: [
          {required: true,
            message: '请输入借阅人名称',
            trigger: 'blur'
          }],
        tel: [
          {required: true,
            message: '请输入联系方式',
            trigger: 'blur'
          }],
        quantity: [
          {required: true,
            message: '请输入借阅数量',
            trigger: 'blur'
          }],
      },
      options:[],//存储所有馆藏图书信息
      borrow:{

      },
      borrowDialogFlag:false,
      tableData:[],
      name:'',
      bname:'',
      pager: {
        page: 1,
        size: 5,
        total: 0
      }
    }
  },
  methods:{
    backBook(row){
      this.borrow=row;
      this.$http.put('http://127.0.0.1/borrow/backBook',this.borrow).then(res=>{
        if(res.data.code==200){
          this.$message({
            type:'success',
            message:res.data.message
          })
          this.search();
        }
      })
    },
    listBoooks(){
      this.$http.get('http://127.0.0.1/book/listBook').then(res=>{
        this.options=res.data.data;
      })
    },
    borrowBook(){
      this.$http.post('http://127.0.0.1/borrow/borrowBook',this.borrow).then(res=>{
        if(res.data.data){
          this.$message({
            type:'success',
            message:res.data.message
          })
          this.borrowDialogFlag=!this.borrowDialogFlag;
          this.search();
        }else{
          this.$message.error('操作失败或库存不足哦，无法为您提供借阅服务')
        }
      })
    },
    openBorrowDialog(){
      this.borrowDialogFlag=!this.borrowDialogFlag;

    },
    handleSizeChange(val) {//最大处理页数
      this.pager.size = val;
      this.search();
    },
    handleCurrentChange(val) {//处理改变当前从第几页开始
      this.pager.page = val;
      this.search();
    },
    search() {
      this.$http.get('http://127.0.0.1/borrow/list',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
          name:this.name,
          bname:this.bname
        }
      }).then(res => {
            this.tableData = res.data.data.data;
            this.pager.total=res.data.data.total;
          })
        }
    },
  mounted() {
    this.search()
    this.listBoooks();
  }
}

</script>



<style scoped>
.el-breadcrumb {
  margin: 10px;
}
</style>
