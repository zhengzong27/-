<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏类别管理</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="10">
      <el-col :span="6">
        <el-input v-model="bname" clearable @change="search" placeholder="请输入图书名称查询"></el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" icon="el-icon-plus" @click="openAddDialog"> 添加</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData">
      <el-table-column type="expand">
        <template v-slot="scope">
          <el-tag>{{scope.row.ddesc}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="编号" prop="bnum"></el-table-column>
      <el-table-column label="图书名称" prop="bname">
        <template v-slot="scope">
          <el-popover trigger="hover" placement="top">
            <p>{{ scope.row.ddesc }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.bname }}</el-tag>
            </div>
          </el-popover>
        </template>

      </el-table-column>
      <el-table-column label="作者" prop="author"></el-table-column>
      <el-table-column label="单价" prop="price"></el-table-column>
      <el-table-column label="数量" prop="quantity"></el-table-column>
      <el-table-column label="所属类别" prop="catename"></el-table-column>
      <el-table-column label="创建时间" prop="createtime"></el-table-column>
      <el-table-column label="修改时间" prop="modifytime"></el-table-column>
      <el-table-column label="操作" width="220">
        <template v-slot="scope">
          <el-button type="warning" size="mini" @click="openEditDialog(scope.row)" icon="el-icon-edit" > 修改</el-button>
          <el-button type="danger" size="small" @click="remove(scope.row)" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--    分页组件-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pager.page"
      :page-sizes="[2, 3, 5, 7]"
      :page-size="pager.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pager.total">
    </el-pagination>
<!--馆藏图书添加对话框-->
    <el-dialog title="馆藏图书添加" :visible.sync="addDialogFlag">
      <el-form :model="book" :rules="rules" ref="book" label-width="80px">
        <el-form-item label="所属类别" prop="bnum">
          <el-select v-model="book.cateid">
            <el-option v-for="(item,index) in options" :label="item.catename"
                       :value="item.cateid" :key="index"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图书编号" prop="bnum">
          <el-input v-model="book.bnum" placeholder="请输入图书编号" ></el-input>
        </el-form-item>
        <el-form-item label="图书书名" prop="bname">
          <el-input v-model="book.bname" placeholder="请输入图书名称" ></el-input>
        </el-form-item>
        <el-form-item label="图书单价" prop="price">
          <el-input v-model="book.price" placeholder="请输入图书单价" ></el-input>
        </el-form-item>
        <el-form-item label="图书作者" prop="author">
          <el-input v-model="book.author" placeholder="请输入图书作者" ></el-input>
        </el-form-item>
        <el-form-item label="馆藏数量" prop="quantity">
          <el-input v-model="book.quantity" placeholder="请输入图书馆藏数量" ></el-input>
        </el-form-item>
        <el-form-item label="图书简介" >
          <el-input type='textarea' v-model="book.ddesc" placeholder="请输入图书简介" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFlag= false">关闭</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-dialog>

    <!--馆藏图书修改对话框-->
    <el-dialog title="馆藏图书修改" :visible.sync="editDialogFlag">
      <el-form :model="book" :rules="rules" ref="book" label-width="80px">
        <el-form-item label="所属类别" prop="bnum">
          <el-select v-model="book.cateid">
            <el-option v-for="(item,index) in options" :label="item.catename"
                       :value="item.cateid" :key="index"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图书编号" prop="bnum">
          <el-input v-model="book.bnum" placeholder="请输入图书编号" ></el-input>
        </el-form-item>
        <el-form-item label="图书书名" prop="bname">
          <el-input v-model="book.bname" placeholder="请输入图书名称" ></el-input>
        </el-form-item>
        <el-form-item label="图书单价" prop="price">
          <el-input v-model="book.price" placeholder="请输入图书单价" ></el-input>
        </el-form-item>
        <el-form-item label="图书作者" prop="author">
          <el-input v-model="book.author" placeholder="请输入图书作者" ></el-input>
        </el-form-item>
        <el-form-item label="馆藏数量" prop="quantity">
          <el-input v-model="book.quantity" placeholder="请输入图书馆藏数量" ></el-input>
        </el-form-item>
        <el-form-item label="图书简介" >
          <el-input type='textarea' v-model="book.ddesc" placeholder="请输入图书简介" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFlag= false">关闭</el-button>
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      options:[],//填充类别下拉列表的数据
      book:{},//定义图书对象封装图书数据
      bname:'',
      pager: {
        page: 1,
        size: 5,
        total: 0
      },
      rules: {
        bnum: [
          {required: true,
            message: '请输入图书编号',
            trigger: 'blur'
          },],
        bname:[
          {required: true,
            message: '请输入图书名称',
            trigger: 'blur'
          },],
        price:[
          {required: true,
            message: '请输入图书单价',
            trigger: 'blur'
          },],
        author:[
          {required: true,
            message: '请输入图书作者',
            trigger: 'blur'
          },],
        quantity:[
          {required: true,
            message: '请输入馆藏数量',
            trigger: 'blur'
          },]
      },
      tableData: [],//添加对话框显示标签 false 表示不显示
      catename:'',
      addDialogFlag: false,//添加对话框标记
      editDialogFlag: false,//修改对话框标记
      category: {}, // 用来封装表单输入的数据的
    }
  },
  methods: {
    update(){
      this.$refs['book'].validate(valid=>{
        if(valid){
          this.$http.put('http://127.0.0.1/book/update',this.book).then(res => {
            if (res.data.code == 200) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.search()
              this.editDialogFlag=!this.editDialogFlag;
            }
          })
        }
      })
    },
    searchCategories(){//查询所有类别信息，初始化类别的下拉列表
      this.$http.get('http://127.0.0.1/category/searchCategories').then(res=>{
        this.options=res.data.data;
      })
    },
    save(){
      this.$refs['book'].validate(valid=>{
        if(valid){
          this.$http.post('http://127.0.0.1/book/save',this.book).then(res => {
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
    handleSizeChange(val) {//最大处理页数
      this.pager.size = val;
      this.search();
    },
    handleCurrentChange(val) {//处理改变当前从第几页开始
      this.pager.page = val;
      this.search();
    },
    openAddDialog() {//该函数用来打开添加对话框
      this.addDialogFlag = !this.addDialogFlag
    },
    openEditDialog(row) {//该函数用来打开修改对话框
      this.editDialogFlag = !this.editDialogFlag  //打开修改对话框
      this.book=row
    },

    search() {
      this.$http.get('http://127.0.0.1:80/book/list', {
        params: {
          page: this.pager.page,
          size: this.pager.size,
          bname: this.bname
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
    remove(row) {
      this.$http.delete('http://127.0.0.1:80/book/remove?bid=' + row.bid)
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
    this.searchCategories()
    this.search()
  }
}

</script>
<style scoped>
.el-breadcrumb {
  margin: 10px;
}
</style>
