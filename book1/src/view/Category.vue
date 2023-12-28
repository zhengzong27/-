<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏类别管理</el-breadcrumb-item>
      <el-breadcrumb-item>类别信息管理</el-breadcrumb-item>
    </el-breadcrumb>
<!-- 布局栏   -->
    <el-row :gutter="10">
      <el-col :span="6"><el-input v-model="catename" clearable @change="search"  placeholder="请输入类别名称查询"></el-input></el-col>
      <el-col span="6"><el-button type="primary"  icon="el-icon-plus" @click="openAddDialog">添加</el-button></el-col>
    </el-row>

    <el-table :data="tableData">
      <el-table-column label="类别名称" prop="catename"/>
      <el-table-column label="创建时间" prop="createtime"/>
      <el-table-column label="修改时间" prop="modifytime"/>
      <el-table-column label="操作">
<template v-slot="scope">
    <el-button type="warning" size="small" @click="openEditDialog(scope.row)" icon="el-icon-edit" >修改</el-button>
    <el-button type="danger" size="small" @click="remove(scope.row)" icon="el-icon-delete">删除</el-button>
</template>
      </el-table-column>
    </el-table>
<!--分页组件-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="pager.page"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="pager.size"
      layout="sizes, prev, pager, next"
      :total="pager.total">
    </el-pagination>
<!--添加的对话框-->
    <el-dialog title="添加类别" :visible.sync="addDialogFlag">
      <el-form :model="category" :rules="rules" ref="category">
        <el-form-item label="类别名称" prop="catename"><!--对话框现实的那一行-->
          <el-input v-model="category.catename" placeholder="请输入类别名称" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFlag= false">关闭</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-dialog>

    <!--修改的对话框-->
    <el-dialog title="修改类别" :visible.sync="editDialogFlag">
      <el-form :model="category" :rules="rules" ref="category">
        <el-form-item label="类别名称" prop="catename">
          <el-input v-model="category.catename" placeholder="请输入类别名称" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogFlag= false">关闭</el-button>
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>


<script>
export default {
  data(){
    return{
      pager:{//定义分页对象
        page:1,
        size:5,
        total:0
      },
      rules:{
        catename:[{ required: true, message: '请输入类别名称', trigger: 'blur' },]
      },
      editDialogFlag:false,//修改对话框显示标记，false不显示
      addDialogFlag:false,//添加对话框显示标记，false不显示
      tableData:[],
      catename:'',
      category: {},//用来封装表单输入的数据
    }
  },
  methods:{
    handleCurrentChange(val){//处理改变当前从第几条分页
      this.pager.page=val;
      this.search();
    },
    handleSizeChange(val){//处理最大的页数，改变每页显示的最大条数
      this.pager.size=val;
      this.search();
    },
    openEditDialog(row){//打开修改对话框的函数,并不是真的修改
      this.editDialogFlag=!this.editDialogFlag;//打开修改对话框
      this.category=row;
    },
    update(){
      this.$http.put('http://127.0.0.1/category/update',this.category).then(res => {
        if(res.data.code==200){
          this.$message({
            message: res.data.message,
            type: 'success'
          });
          this.search();
          this.editDialogFlag=!this.editDialogFlag;
        }
      })
    },
    openAddDialog(){//打开添加对话框的函数
     this.addDialogFlag=!this.addDialogFlag;
    },
    save(){
      this.$refs['category'].validate(valid=>{
        if (valid){
          this.$http.post('http://127.0.0.1/category/save',this.category).then(res => {
            if(res.data.code==200){
              this.$message({
                message:res.data.message,
                type: 'success'
              });
              this.addDialogFlag=!this.addDialogFlag;
              this.search()
            }
          }).catch(error => {
            this.$message.error('网络异常')
          })

        }
      })


    },
    search() {
      this.$http.get('http://127.0.0.1/category/list',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
          catename:this.catename
        }
      })
        .then(res => {
          if (res.data.code==200){
            this.tableData = res.data.data.data;
            this.pager.total=res.data.data.total;
          }
        }).catch(error => {
        this.$message.error('网络异常')
      })
    },
      remove(row){
        this.$http.delete('http://127.0.0.1/category/remove?cateid='+ row.cateid).then(res=>{
            if(res.data.code==200){
              this.$message({
                message: res.data.message,
                type: 'success'
              });
                this.search();
            }
        }).catch(error => {
          this.$message.error('网络异常')
        })
      }

  },
    mounted(){
    this.search()
    }
  }

</script>



<style scoped>
.el-breadcrumb{
  margin: 10px;
}
</style>
