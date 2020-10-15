<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="120px">
      <!-- <el-form-item label="父id" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="任务类型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入任务类型名称"
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
          v-hasPermi="['bajiaostar:type:add']"
        >新增</el-button>
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

    <el-table
      v-loading="loading"
      :data="typeList"
      
      row-key="id"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="任务类型名称" align="center" prop="name" />
      <el-table-column label="id" align="center" prop="id" />
      <!-- <el-table-column label="父id" align="center" prop="pid" /> -->
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:type:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:type:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改任务类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="父id" prop="pid">
          <treeselect v-model="form.pid" :options="typeOptions" :normalizer="normalizer" placeholder="请选择父id" />
        </el-form-item>
        <el-form-item label="任务类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入任务类型名称" />
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
import { listType, getfatherId, getType, delType, addType, updateType, exportType } from "@/api/bajiaostar/type";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { mapState } from 'vuex'
export default {
  name: "Type",
  components: { Treeselect },
  computed : {
    ...mapState(['app']),
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 任务类型表格数据
      typeList: [],
      // 任务类型树选项
      typeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pid: null,
        name: null
      },
      typeOptions: [],
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
        fatherList: []
        // version: [
        //   { required: true, message: "版本号不能为空", trigger: "blur" }
        // ],
        // delFlag: [
        //   { required: true, message: "逻辑删标识(0 未删 1 已删)不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
    this.getfather()
  },
  methods: {
    /** 查询任务类型列表 */
    getList() {
      this.loading = true;
      listType(this.queryParams).then(response => {
        this.typeList = this.handleTree(response.rows, "id", "pid");
        
        this.loading = false;
      });
    },
    getfather() {
      getfatherId().then(res => {
        console.log(res)
        let fatherArr = []
        let obj = {}
        if (res.rows.length) {
            res.rows.forEach(item => {
              obj = {
                id: item.id,
                name: item.name,
                children: []
              }
              fatherArr.push(obj)
            })
        }
        this.fatherList = fatherArr

      })
    },
    /** 转换任务类型数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
	/** 查询部门下拉树结构 */
    getTreeselect() {
      listType().then(response => {
        this.typeOptions = [];
        let data = [{ id: 0, name: '顶级节点', children: [] }];
        data = [...data, ...this.fatherList]

        // data.children = this.handleTree(response.rows, "id", "pid");
  
        this.typeOptions = data;
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
        pid: null,
        name: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
	  this.getTreeselect();
      this.open = true;
      this.title = "添加任务类型";
      this.getfather()
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
    this.getTreeselect();
    this.getfather()
      if (row != null) {
        this.form.pid = row.id;
      }
      getType(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateType(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addType(this.form).then(response => {
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
      this.$confirm('是否确认删除任务类型编号为"' + row.id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delType(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>
