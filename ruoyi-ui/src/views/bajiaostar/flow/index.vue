<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务编号" prop="taskNo">
        <el-input
          v-model="queryParams.taskNo"
          placeholder="请输入任务编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="版本号" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <!-- <el-form-item label="任务id" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="积分" prop="optIntegral">
        <el-input
          v-model="queryParams.optIntegral"
          placeholder="请输入积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="原积分" prop="originalIntegral">
        <el-input
          v-model="queryParams.originalIntegral"
          placeholder="请输入原积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新后积分" prop="afterIntegral">
        <el-input
          v-model="queryParams.afterIntegral"
          placeholder="请输入更新后积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="流水类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择流水类型" clearable size="small">
           <el-option
              v-for="item in orderStatus"
              :key="item.dictSort"
              :label="item.dictLabel"
              :value="item.dictSort">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="勤务姓名" >
        <el-select v-model="queryParams.userId" placeholder="请选择查询用户" clearable size="small">
           <el-option
              v-for="item in userIdList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="订单id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
 
 
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
          v-hasPermi="['bajiaostar:flow:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:flow:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:flow:export']"
        >导出</el-button>
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

    <el-table v-loading="loading" :data="flowList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <!-- <el-table-column label="版本号" align="center" prop="version" /> -->
      <!-- <el-table-column label="任务id" align="center" prop="taskId" /> -->
      <el-table-column label="任务编号" align="center" prop="taskNo" />

      <el-table-column label="勤务姓名" align="center" prop="userName" />
      <el-table-column label="积分" align="center" prop="optIntegral" />
      <el-table-column label="原积分" align="center" prop="originalIntegral" />
      <el-table-column label="更新后积分" align="center" prop="afterIntegral" />
      <el-table-column label="流水类型" align="center" prop="type" />
      <!-- <el-table-column label="订单id" align="center" prop="orderId" /> -->
      <el-table-column label="备注" align="center" prop="remark" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:flow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:flow:remove']"
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

    <!-- 添加或修改用户流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item> -->
        <!-- <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="积分" prop="optIntegral">
          <el-input v-model="form.optIntegral" placeholder="请输入积分" />
        </el-form-item>
        <!-- <el-form-item label="原积分" prop="originalIntegral">
          <el-input v-model="form.originalIntegral" placeholder="请输入原积分" />
        </el-form-item>
        <el-form-item label="更新后积分" prop="afterIntegral">
          <el-input v-model="form.afterIntegral" placeholder="请输入更新后积分" />
        </el-form-item> -->
        <el-form-item label="流水类型">
          <el-select v-model="form.type" placeholder="请选择流水类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单id" />
        </el-form-item> -->
        <el-form-item label="任务编号" prop="taskNo">
          <el-input v-model="form.taskNo" placeholder="请输入任务编号" />
        </el-form-item>
        <!-- <el-form-item label="任务id" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入任务id" />
        </el-form-item> -->
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
import { listFlow, getFlow, delFlow, addFlow, updateFlow, exportFlow } from "@/api/bajiaostar/flow";
import { userList } from "@/api/bajiaostar/task";
import { mapState } from 'vuex'
export default {
  name: "Flow",
  computed : {
    ...mapState(['app']),
  },
  data() {
    return {
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
      // 用户流水表格数据
      flowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        version: null,
        userId: null,
        optIntegral: null,
        originalIntegral: null,
        afterIntegral: null,
        type: null,
        orderId: null,
        taskNo: null,
        taskId: null,
        userName: null,
        userId: null
      },
      orderStatus: [],
      // 表单参数
      form: {},
      userIdList: [],
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
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        optIntegral: [
          { required: true, message: "积分不能为空", trigger: "blur" }
        ],
        originalIntegral: [
          { required: true, message: "原积分不能为空", trigger: "blur" }
        ],
        afterIntegral: [
          { required: true, message: "更新后积分不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "流水类型不能为空", trigger: "blur" }
        ],
        taskNo: [
          { required: true, message: "任务编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
 async created() {
    await userList ({userType: 2}).then(res => {
      console.log(res)
      this.userIdList = res.data
    }) 
    this.getList();
      this.getDicts("integral_flow_type").then(response => {
        console.log(response)
      this.orderStatus = response.data;
    });
  },
  mounted() {
    
  },
  methods: {
    /** 查询用户流水列表 */
    getList() {
      this.loading = true;
      listFlow(this.queryParams).then(response => {
        this.flowList = response.rows;

        if (this.userIdList.length) {
          this.flowList.forEach(item => {
            this.userIdList.forEach(it => {
              if (item.userId == it.userId) {
                item.userName = it.userName
              }
            })
          })
        }
        console.log(this.flowList)
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
        userId: null,
        optIntegral: null,
        originalIntegral: null,
        afterIntegral: null,
        type: null,
        orderId: null,
        taskNo: null,
        taskId: null,
        userName: null
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
      this.queryParams.userId = null
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
      this.title = "添加用户流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFlow(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户流水";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFlow(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addFlow(this.form).then(response => {
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
      this.$confirm('是否确认删除用户流水编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFlow(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户流水数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportFlow(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
