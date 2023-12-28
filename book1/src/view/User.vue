
<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <br>
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="uname" clearable @change="search" placeholder="请输入用户名称查询"></el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" icon="el-icon-plus" @click="openAddDialog"> 添加</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData">
      <el-table-column label="用户" prop="uname"/>
      <el-table-column label="密码" prop="password"/>
      <el-table-column label="联系地址" prop="images"/>
      <el-table-column label="借阅状态" prop="stats"/>
      <el-table-column label="用户创建时间" prop="createtime"/>
      <el-table-column label="用户修改时间" prop="modifytime"/>
      <el-table-column label="操作" width="200">
        <template v-slot="scope" >
          <el-button type="warning" size="mini" @click="openEditDialog(scope.row)" icon="el-icon-edit" > 修改</el-button>
          <el-button type="danger" size="small" @click="remove(scope.row)" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  分页组件-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pager.page"
      :page-sizes="[5,10,15,20]"
      :page-size="100"
      layout="total,sizes,prev,pager,next,jumper"
      :total="pager.total">
    </el-pagination>
<!--    用户添加-->
    <el-dialog title="用户添加" :visible.sync="addDialogFlag">
      <el-form :model="user" :rules="rules" ref="user" label-width="80px">
        <el-form-item label="用户" prop="uname">
          <el-input v-model="user.uname" placeholder="请输入用户名称" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="user.password" placeholder="请输入用户密码" ></el-input>
        </el-form-item>
        <el-form-item label="联系地址" prop="images">
          <el-input v-model="user.images" placeholder="请输入用户地址" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="this.addDialogFlag= false">关闭</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-dialog>

<!--    弹出表单修改信息-->
    <el-dialog title="用户修改" :visible.sync="editDialogFlag">
      <el-form :model="user" :rules="rules" ref="user" label-width="80px">
        <el-form-item label="用户" prop="uname">
          <el-input v-model="user.uname" placeholder="请输入用户名称" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="user.password" placeholder="请输入用户密码" ></el-input>
        </el-form-item>
        <el-form-item label="联系地址" prop="images">
          <el-input v-model="user.images" placeholder="请输入用户地址" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="quxiao">关闭</el-button>
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>


<script >
import axios from "axios";
export default {
  data() {
    return {
      pager: { /*定义分页*/
        page: 1,
        size: 5,
        total: 0
      },

      addDialogFlag:false,
      editDialogFlag:false,

      rules: {
        uname: [
          {required: true,
            message: '请输入用户名称',
            trigger: 'blur'
          },],
        password:[
          {required: true,
            message: '请输入用户密码',
            trigger: 'blur'
          },],
        images:[
          {required: true,
            message: '请输入用户地址',
            trigger: 'blur'
          },],
      },
      tableData: [],
      uname: '',
      user: {},
     /*用来封装表单输入的数据的*/
    }
  },
  methods: {
    quxiao(){
      this.editDialogFlag=false;
      this.$message({
        message: '取消操作',
        type: 'waring'
      })

    },
    handleCurrentChange(val) {
      this.pager.page = val;
      this.search();
    },
    handleSizeChange(val) {/*处理最大页数*/
      this.pager.size = val;
      this.search();
    },
    openAddDialog() {//该函数用来打开添加对话框
      this.addDialogFlag = true;
    },
    openEditDialog(row) {//该函数用来打开修改对话框
     this.editDialogFlag=true;
     this.user = row//打开修改对话框
    },

    search() {
      axios.get('http://localhost/user/list', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          uname: this.uname
        }
      })
        .then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data.data;
            this.pager.total = res.data.data.total
          }
        }).catch(() => {
        this.$message.error('网络异常');
      })
    },

    update(){
      this.$refs['user'].validate(valid=>{
        if(valid){
          this.$http.put('http://localhost/user/update',this.user).then(res => {
            if (res.data.code == 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.search()
            this.editDialogFlag=false;
            }
          }).catch(err=>{
            this.$message({
              message: '操作成功',
              type: 'error',
            })
            console.log(err)
          })
        }
      })
    },

    save(){
      this.$refs['user'].validate(valid=>{
        if(valid){
          this.$http.post('http://localhost/user/save',this.user).then(res => {
            if (res.data.code == 200) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.search()
              this.addDialogFlag=!this.addDialogFlag;
            }
          })
        }
      })
    },

    remove(row) {
      this.$http.delete('http://localhost/user/remove?uid=' + row.uid)
        .then(res => {
          if (res.data.code == 200) {
            this.$message({
              message: res.data.message,
              type: 'success'
            });
            this.search();
          }
        }).catch(() => {
        this.$message.error('网络异常');
      })
    }
  },

  mounted() {
    this.search();
  }
}

</script>

<style scoped>

</style>
