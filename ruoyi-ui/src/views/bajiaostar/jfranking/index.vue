<template>
  <div class="app-container">
    <el-form :model="newQueryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="版本号" prop="version">
        <el-input
          v-model="newQueryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="年月" prop="yearMonthTime">
        <!-- <el-input
          v-model="newQueryParams.yearMonth"
          placeholder="请输入年月"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
         <el-date-picker
            v-model="newQueryParams.yearMonthTime"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择月">
          </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="newQueryParams.userId"
          placeholder="请输入用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="用户名称" prop="nickName">
      <el-input
        v-model="newQueryParams.nickName"
        placeholder="请输入用户名称"
        clearable
        size="small"
        @keyup.enter.native="handleQuery"
      />
    </el-form-item>
      <el-form-item label="用户手机" prop="phonenumber">
        <el-input
          v-model="newQueryParams.phonenumber"
          placeholder="请输入用户手机"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="订单数量" prop="orderNum">
        <el-input
          v-model="newQueryParams.orderNum"
          placeholder="请输入订单数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="积分" prop="integral">
        <el-input
          v-model="newQueryParams.integral"
          placeholder="请输入积分"
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

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bajiaostar:ranking:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:ranking:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:ranking:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:ranking:export']"
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
    </el-row> -->

    <el-table v-loading="loading" :data="rankingList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column type = "index" label = "排名"  align="center" />

      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <!-- <el-table-column label="版本号" align="center" prop="version" /> -->

      <el-table-column label="年月" align="center" prop="yearMonth" />
      <!-- <el-table-column label="用户id" align="center" prop="userId" /> -->

       <el-table-column label="用户名称" align="center" prop="nickName" />

      <el-table-column label="用户手机" align="center" prop="phonenumber" />

      <el-table-column label="订单数量" align="center" prop="orderNum" />
      <el-table-column label="积分" align="center" prop="integral" />
      <!-- <el-table-column label="备注" align="center" prop="remark" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:ranking:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:ranking:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改用户排名对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item>
        <el-form-item label="年月" prop="yearMonthTime">
          <el-input v-model="form.yearMonthTime" placeholder="请输入年月" />
        </el-form-item>
        <!-- <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item> -->
        <el-form-item label="订单数量" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入订单数量" />
        </el-form-item>
        <el-form-item label="积分" prop="integral">
          <el-input v-model="form.integral" placeholder="请输入积分" />
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
import { jflistRanking, getRanking, delRanking, addRanking, updateRanking, exportRanking } from "@/api/bajiaostar/ranking";
import { mapState } from 'vuex'
export default {
  name: "Ranking",
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
      // 用户排名表格数据
      rankingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      // queryParams: {
      //   pageNum: 1,
      //   pageSize: 10,
      //   version: null,
      //   yearMonth: null,
      //   userId: null,
      //   orderNum: null,
      //   integral: null
      // },
      newQueryParams: {
          version: null,
          yearMonthTime: null,
          userId: null,
          orderNum: null,
          integral: null
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
        yearMonthTime: [
          { required: true, message: "年月不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "订单数量不能为空", trigger: "blur" }
        ],
        integral: [
          { required: true, message: "积分不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户排名列表 */
    getList() {
      this.loading = true;
      console.log(this.newQueryParams)
      jflistRanking(this.newQueryParams).then(response => {
        this.rankingList = response.data;
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
        yearMonthTime: null,
        userId: null,
        orderNum: null,
        integral: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // this.queryParams.pageNum = 1;
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
      this.title = "添加用户排名";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRanking(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户排名";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRanking(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addRanking(this.form).then(response => {
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
      this.$confirm('是否确认删除用户排名编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRanking(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // const queryParams = this.queryParams;
      // this.$confirm('是否确认导出所有用户排名数据项?', "警告", {
      //     confirmButtonText: "确定",
      //     cancelButtonText: "取消",
      //     type: "warning"
      //   }).then(function() {
      //     return exportRanking(queryParams);
      //   }).then(response => {
      //     this.download(response.msg);
      //   }).catch(function() {});
    }
  }
};
</script>
