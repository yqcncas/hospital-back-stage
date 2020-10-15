<template>
  <div class="app-container">
    
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入任务地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bajiaostar:address:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:address:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:address:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:address:export']"
        >导出</el-button>
      </el-col>
        <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-download"
          size="mini"
          @click="handleGetQrcode"
          
          v-hasPermi="['bajiaostar:address:export_qrcode']"
        >科室二维码</el-button>
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="addressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="key" align="center" prop="addressKey" />

      <el-table-column label="任务地址" align="center" prop="address" />
      <el-table-column label="位置" align="center" prop="addressPosition" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:address:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:address:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleOnlyGetQrcode(scope.row)"
            v-hasPermi="['bajiaostar:address:export_qrcode']"
          >下载科室二维码</el-button>
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

    <!-- 添加或修改任务地址对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="key" prop="addressKey">
          <el-input v-model="form.addressKey" maxlength="50" placeholder="请输入key值" />
        </el-form-item>
        <el-form-item label="任务地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入任务地址" />
        </el-form-item>
     
         <el-form-item label="位置" prop="addressPosition">
          <el-input v-model="form.addressPosition" placeholder="请输入位置" />
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
import { listAddress, newlistAddress,getAddress, delAddress, addAddress, updateAddress, exportAddress, getQrcode, getOnlyQrcode } from "@/api/bajiaostar/address";
import { mapState } from 'vuex'
export default {
  name: "Address",
  computed: {
    ...mapState(['app']),
  },
  data() {
    return {
      fullscreenLoading: false,
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
      // 任务地址表格数据
      addressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        address: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        version: [
          { required: true, message: "版本号不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "逻辑删标识(0 未删 1 已删)不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "任务地址不能为空", trigger: "blur" }
        ],
        addressKey: [
          { required: true, message: "key值不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 获取二维码
    handleGetQrcode () {
      // this.fullscreenLoading = true;
      const loading = this.$loading({
          lock: true,
          text: '正在准备下载资源中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });

      getQrcode().then(response => {
        console.log(response)
        const downLoadFile = response
         const queryParams = this.queryParams;
    //  this.fullscreenLoading = false;
    loading.close();
      this.$confirm('是否确认下载科室二维码?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
           let blob = new Blob([downLoadFile], {type: "application/zip, charset=UTF-8"});  // res就是接口返回的文件流了
        let objectUrl = URL.createObjectURL(blob); 
        window.location.href = objectUrl; 
        }).then(response => {
        }).catch(function() {
          loading.close();
        });
      })
    },

    handleOnlyGetQrcode (row) {
      getOnlyQrcode(row.id).then(response => {
        console.log(response)
         this.$confirm('是否确认下载科室二维码?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
           let blob = new Blob([response], {type: "application/zip, charset=gb2312"});  // res就是接口返回的文件流了
            let objectUrl = URL.createObjectURL(blob); 
            window.location.href = objectUrl; 
          //  let blob = new Blob([response], {type: 'application/zip'});

          //   if (window.navigator.msSaveOrOpenBlob) {
          //           //兼容ie
          //       window.navigator.msSaveBlob(blob, response);

          //   } else {

          //       let downloadElement = document.createElement('a');
          //       let href = window.URL.createObjectURL(blob); //创建下载的链接
          //       downloadElement.href = href;

          //       downloadElement.download =  response;//下载后文件名


          //       document.body.appendChild(downloadElement);
          //       //此写法兼容火狐
          //       let evt = document.createEvent("MouseEvents");
          //       evt.initEvent("click", false, false);
          //       downloadElement.dispatchEvent(evt);

          //       document.body.removeChild(downloadElement);
          //   }





        }).then(response => {
        }).catch(function() {});
      })
    },
    /** 查询任务地址列表 */
    getList() {
      this.loading = true;
      newlistAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
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
        address: null,
        addressKey: null,
        addressPosition: null
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
      this.title = "添加任务地址";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAddress(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务地址";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAddress(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addAddress(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除任务地址编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAddress(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务地址数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAddress(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
