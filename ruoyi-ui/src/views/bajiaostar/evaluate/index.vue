<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="110px">
      <!-- <el-form-item label="版本号" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="任务id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入任务id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="任务编号" prop="taskNo">
        <el-input
          v-model="queryParams.taskNo"
          placeholder="请输入任务编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="被评价用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入被评价用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
   
      <el-form-item label="评分 满分10分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入评分 满分10分"
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
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bajiaostar:evaluate:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
     
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:evaluate:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:evaluate:export']"
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

    <el-table v-loading="loading" :data="evaluateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="版本号" align="center" prop="version" /> -->
      <el-table-column label="任务id" align="center" prop="orderId" />
      <el-table-column label="评价内容" align="center" prop="content" />
      <el-table-column label="用户名" align="center" prop="user[nickName]"  />
      <el-table-column label="用户手机号" align="center" prop="user[phonenumber]"  />

      <el-table-column label="任务编号" align="center" prop="taskNo" />
      <el-table-column label="评分" align="center" prop="score" />
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:evaluate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:evaluate:remove']"
          >删除</el-button>
            <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:evaluate:remove']"
          >评价</el-button> -->
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

    <!-- 添加或修改任务评价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="任务id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入任务id" disabled />
        </el-form-item>
        <el-form-item label="评价内容" prop="content">
          <el-input v-model="form.content" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="被评价用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入被评价用户id" disabled/>
        </el-form-item>
        <el-form-item label="任务编号" prop="taskNo">
          <el-input v-model="form.taskNo" placeholder="请输入任务编号" disabled />
        </el-form-item>
        <el-form-item label="评分" prop="score" style="display:flex; align-items: center;" class="evastar">
          <!-- <el-input v-model="form.score" placeholder="请输入评分 满分10分" /> -->
              <el-rate
                v-model="form.score"
                show-score
                text-color="#ff9900"
                score-template="{value}">
          </el-rate>
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
import { listEvaluate, getEvaluate, delEvaluate, addEvaluate, updateEvaluate, exportEvaluate } from "@/api/bajiaostar/evaluate";
import { mapState } from 'vuex'
export default {
  name: "Evaluate",
  computed: {
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
      // 任务评价表格数据
      evaluateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        version: null,
        orderId: null,
        content: null,
        userId: null,
        taskNo: null,
        score: null
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
        orderId: [
          { required: true, message: "任务id不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "评价内容不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "被评价用户id不能为空", trigger: "blur" }
        ],
        taskNo: [
          { required: true, message: "任务编号不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "评分 满分10分不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务评价列表 */
    getList() {
      this.loading = true;
      listEvaluate(this.queryParams).then(response => {
        this.evaluateList = response.rows;
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
        orderId: null,
        content: null,
        userId: null,
        taskNo: null,
        score: null
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
      this.title = "添加任务评价";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEvaluate(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务评价";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEvaluate(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addEvaluate(this.form).then(response => {
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
      this.$confirm('是否确认删除任务评价编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEvaluate(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务评价数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEvaluate(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style>
.evastar .el-form-item__content{
  margin-left: 0 !important;
}
</style>
