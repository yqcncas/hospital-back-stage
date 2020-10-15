<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="版本号" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="app类型(1-android,2-ios,0-其他)" prop="appType">
        <el-select v-model="queryParams.appType" placeholder="请选择app类型(1-android,2-ios,0-其他)" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="下载文件路径" prop="filePath">
        <el-input
          v-model="queryParams.filePath"
          placeholder="请输入下载文件路径"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="苹果下载路径" prop="iosUrl">
        <el-input
          v-model="queryParams.iosUrl"
          placeholder="请输入苹果下载路径"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否是大版本更新 (0 否 1 是)" prop="bigUpdFlag">
        <el-input
          v-model="queryParams.bigUpdFlag"
          placeholder="请输入是否是大版本更新 (0 否 1 是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bajiaostar:version:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:version:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:version:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:version:export']"
        >导出</el-button>
      </el-col> -->


      
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="versionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="版本号" align="center" prop="version" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="app类型(1-android,2-ios,0-其他)" align="center" prop="appType" />
      <el-table-column label="下载文件路径" align="center" prop="filePath" />
      <el-table-column label="苹果下载路径" align="center" prop="iosUrl" />
      <el-table-column label="是否是大版本更新 (0 否 1 是)" align="center" prop="bigUpdFlag" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:version:edit']"
          >修改</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:version:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改app版本管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="版本号" prop="version">
          <el-input v-model="form.appVersion" placeholder="请输入版本号" />
        </el-form-item>
        <!-- <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="app类型">
          <el-select v-model="form.appType" placeholder="请选择app类型">
           <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下载文件路径" prop="filePath">
          <el-row>
            <el-col :span = "18">
                <el-input v-model="form.filePath" placeholder="请输入下载文件路径"  />
            </el-col>  
            <el-col :span = "6">
              <input type="file"  @change="handleUpload($event, 'az')">
               <!-- <el-input type="file" class="uploadButton" @change="handleUpload" /> -->
              <!-- <input  name="" id=""> -->
              <!-- <el-button type="primary" @click = "uploadFile(1)">上传</el-button> -->

              <!-- <el-upload
                class="upload-demo"
                action="https://up.qbox.me/"
                :data = "qnToken"
                multiple
                :limit="1"
                >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload> -->

            </el-col>
          </el-row>          
        </el-form-item>
        <el-form-item label="苹果下载路径" prop="iosUrl">
            <el-row>
            <el-col :span = "18">
                <el-input v-model="form.iosUrl" placeholder="请输入下载文件路径" />
            </el-col>  
            <el-col :span = "6">
              <!-- <el-button type="primary" @click = "uploadFile(2)">上传</el-button> -->
              <!-- <el-input type="file" class="uploadButton" @change="handleUpload"/> -->
               <input type="file"  @change="handleUpload($event, 'ios')">
            </el-col>
          </el-row>  
   
        </el-form-item>
        <el-form-item label="是否是大版本更新" prop="bigUpdFlag" >
          <el-input v-model="form.bigUpdFlag" placeholder="请输入是否是大版本更新 (0 否 1 是)" />
        </el-form-item>
         <el-form-item label="备注" prop="remark" >
          <el-input v-model="form.remark" placeholder="请输入更新内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <globalDialog :dialogVisible = app.dialogVisibleValue></globalDialog>
  </div>
</template>

<script>
import { listVersion, getVersion, delVersion, addVersion, updateVersion, exportVersion, getQnToken } from "@/api/bajiaostar/version";
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name: "Version",
  computed : {
    ...mapState(['app']),
  },
  data() {
    return {
      qnToken: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // app版本管理表格数据
      versionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        version: null,
        appType: null,
        filePath: null,
        iosUrl: null,
        bigUpdFlag: null
      },
      // 表单参数
      form: {},
      options: [{
          value: '0',
          label: '其他'
        }, {
          value: '1',
          label: '安卓'
        }, {
          value: '2',
          label: 'ios'
      }],
      // 表单校验
      rules: {
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        appVersion: [
          { required: true, message: "版本号不能为空", trigger: "blur" }
        ],
        // delFlag: [
        //   { required: true, message: "逻辑删标识(0 未删 1 已删)不能为空", trigger: "blur" }
        // ],
        // iosUrl: [
        //   { required: true, message: "苹果下载路径不能为空", trigger: "blur" }
        // ],
        bigUpdFlag: [
          { required: true, message: "是否是大版本更新 (0 否 1 是)不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getQNtoken()
  },
  methods: {
    getQNtoken () {
      getQnToken().then(res => {
        
        this.qnToken = res.msg
        console.log( this.qnToken)
      })
    },

    handleUpload (e, type) {
        console.log(e)
        console.log(type)
        var formData = new FormData();
        formData.append('file', e.target.files[0]);
        formData.append('key', e.target.files[0].name);
        // formData.append("token", `[[$(this.qnToken)]]`);
        formData.append("token", [[this.qnToken]]);

            axios({
                url: "https://up.qbox.me/",
                type: 'post',
                method: 'post',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                dataType: "json",
              
            }).then(res => {
                
                  console.log(res)
                  if (type == 'az') {
                    this.form.filePath = 'https://img.bajiaostar.com/' + res.data.key
                    this.msgSuccess("文件上传成功")
                  } else {
                    this.form.iosUrl = 'https://img.bajiaostar.com/' + res.data.key
                    this.msgSuccess("文件上传成功")

                  }
                    this.msgSuccess("文件上传完毕")
            
                
            }).catch(e => {
              console.log(e)
              this.msgError('文件上传失败')
            });


    },

    /** 查询app版本管理列表 */
    uploadFile(type){
      //  $.modal.alertWarning("文件上传中...请上传完成后在点击提交");
      //       var formData = new FormData();
      //       formData.append('file', obj.files[0]);
      //       formData.append('key', obj.files[0].name);
      //       formData.append("token", '[[${upToken}]]');
      //       $.ajax({
      //           url: "https://up.qbox.me/",
      //           type: 'post',
      //           cache: false,
      //           data: formData,
      //           processData: false,
      //           contentType: false,
      //           dataType: "json",
      //           success: function(result) {
      //               // $("#filePath").val( upUrl +result.key );
      //               if (obj.id == 'androidUrl' ){
      //                   $("#filePath").val( upUrl +result.key );
      //                   $.modal.alertSuccess("文件上传成功");
      //               }else if (obj.id == 'iosFile'){
      //                   $("#iosUrl").val( upUrl +result.key );
      //                   $.modal.alertSuccess("文件上传成功");
      //               }
      //               $.modal.alertSuccess("文件上传成功");
      //           },
      //           error: function(error) {
      //               $.modal.alertWarning("文件上传失败。");
      //           }
      //       });
    },
    getList() {
      this.loading = true;
      listVersion(this.queryParams).then(response => {
        this.versionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        version: null,
        remark: null,
        delFlag: null,
        appType: null,
        filePath: null,
        iosUrl: null,
        bigUpdFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加app版本管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVersion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改app版本管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVersion(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addVersion(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除app版本管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delVersion(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有app版本管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportVersion(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style >
.uploadButton > .el-input__inner{
  padding: 0 !important;
}
</style>