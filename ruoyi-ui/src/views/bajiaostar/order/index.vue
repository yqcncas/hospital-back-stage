<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="版本号" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="id" prop="taskId">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务编号" prop="taskId">
        <el-input
          v-model="queryParams.eventId"
          placeholder="请输入任务编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
       <el-form-item label="院区选择">
            <el-select
              v-model="queryParams.yqType"
              placeholder="请选择院区"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in yqType"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
      
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable size="small">
          <el-option
            v-for="dict in taskStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      
      <el-form-item label="后勤用户" prop="logisticsId">
        <!-- <el-input
          v-model="queryParams.logisticsId"
          placeholder="请输入后勤用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
          <el-select v-model="queryParams.logisticsId" placeholder="请选择" filterable>
            <el-option
            
              v-for="item in houqingUser"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
          </el-select>
      </el-form-item> 
      
      <el-form-item label="派发时间" prop="taskStatus">
         <el-date-picker
          v-model="value2"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
           value-format="yyyy-MM-dd"
          :picker-options="pickerOptions">
        </el-date-picker>
      </el-form-item>

      <!-- <el-form-item label="积分" prop="taskIntegral">
        <el-input
          v-model="queryParams.taskIntegral"
          placeholder="请输入积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="任务开始时间" prop="startTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择任务开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="任务完成时间" prop="finishTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.finishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择任务完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="派发时间" prop="distributeTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.distributeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择派发时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="用户确认时间" prop="confirmTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.confirmTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择用户确认时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="医护id" prop="doctorsId">
        <el-input
          v-model="queryParams.doctorsId"
          placeholder="请输入医护id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="派发用户id" prop="distributeId">
        <el-input
          v-model="queryParams.distributeId"
          placeholder="请输入派发用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="后勤用户id" prop="logisticsId">
        <el-input
          v-model="queryParams.logisticsId"
          placeholder="请输入后勤用户id"
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
          v-hasPermi="['bajiaostar:order:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:order:export']"
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

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <!-- <el-table-column label="版本号" align="center" prop="version" /> -->
      <el-table-column label="任务编号" align="center" prop="eventId" />
      <el-table-column label="院区" align="center" prop="yqType" :show-overflow-tooltip="true" />
      <el-table-column label="任务状态" align="center" prop="taskStatus" :formatter="taskStatusFormat" />
      <el-table-column label="积分" align="center" prop="taskIntegral" />
    
      <!-- <el-table-column label="医护人员" align="center" prop="doctorsUserName" /> -->
      <el-table-column label="派发用户" align="center" prop="distributeUserName" />
      <el-table-column label="后勤用户" align="center" prop="logisticsIdUserName" />
      <el-table-column label="起始地址" align="center" prop="startAddress" />
      <el-table-column label="目标地址" align="center" prop="endAddress" />
      <el-table-column label="任务描述" align="center" prop="remark" width="240" />
           <el-table-column label="派发时间" align="center" prop="distributeTime" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.distributeTime, '{y}-{m}-{d}') }}</span> -->
          <span>{{ parseTime(scope.row.distributeTime) }}</span>

        </template>
      </el-table-column>
      <el-table-column label="任务开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span> -->
          <span>{{ parseTime(scope.row.startTime) }}</span>

        </template>
      </el-table-column>
  
      <el-table-column label="到达时间" align="center" prop="arrivalTime" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.confirmTime, '{y}-{m}-{d}') }}</span> -->
          <span>{{ parseTime(scope.row.arrivalTime) }}</span>

        </template>
      </el-table-column>
     
   
      <el-table-column label="任务完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span> -->
          <span>{{ parseTime(scope.row.finishTime) }}</span>

        </template>
    
      </el-table-column>
        <!-- <el-table-column label="确认时间" align="center" prop="confirmTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.confirmTime) }}</span>

        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:order:edit']"
          >查看详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:order:remove']"
          >删除</el-button>
           <el-button
            size="mini"
            type="text"
            icon="el-icon-star-off"
            v-hasPermi="['bajiaostar:evaluate:add']"
            @click="showEvaluateBox(scope.row.id)"
            v-show="scope.row.taskStatus == 3"
          >评价</el-button>
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="任务id" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入任务id" />
        </el-form-item>
        <el-form-item label="任务状态">
          <el-select v-model="form.taskStatus" placeholder="请选择任务状态">
            <el-option
              v-for="dict in taskStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="积分" prop="taskIntegral">
          <el-input v-model="form.taskIntegral" placeholder="请输入积分" />
        </el-form-item>
        <el-form-item label="任务开始时间" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择任务开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="任务完成时间" prop="finishTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.finishTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择任务完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="派发时间" prop="distributeTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.distributeTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择派发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户确认时间" prop="confirmTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.confirmTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择用户确认时间">
          </el-date-picker>
        </el-form-item>

       <el-row>
        <el-col :span = 12>
             <el-form-item label="任务分类"  >
          <el-select v-model="form.task.oneTaskType" placeholder="请选择任务类型" disabled>
            <el-option v-for="item in typeOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id" 
                
                 />
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span = 12>
           <el-form-item label="二级分类" v-if="twoTaskArr.length">

          <el-select v-model="form.task.twoTaskType" placeholder="请选择任务类型" disabled>
            <el-option v-for="item in twoTaskArr"
                :key="item.id"
                :label="item.name"
                :value="item.id" />
          </el-select>
       </el-form-item>
        </el-col>
      </el-row>
       

     


        <el-row>
          <el-col :span = 12>
              <el-form-item label="起始地址" prop="startAddress" v-if="form.task.startAddress"> 
                 <el-input v-model="form.task.startAddress"  disabled />
          
              </el-form-item>
          </el-col>
          <el-col :span = 12>
            <el-form-item label="目标地址" prop="endAddress" v-if="form.task.endAddress">
               <el-input v-model="form.task.endAddress"  disabled />
             </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="院区" >
          <el-input v-model="form.yqType" placeholder="" disabled />
        </el-form-item>
        
        <el-form-item label="医护人员" prop="doctorsId">
          <el-input v-model="form.doctorsUserName" placeholder="请输入医护id" disabled />
        </el-form-item>
        <el-form-item label="派发用户" prop="distributeId">
          <el-input v-model="form.distributeUserName" placeholder="请输入派发用户id" disabled />
        </el-form-item>
        <el-form-item label="后勤用户" prop="logisticsId">
          <el-input v-model="form.logisticsIdUserName" placeholder="请输入后勤用户id" disabled />
        </el-form-item>
         <el-form-item label="任务描述" prop="remark">
          <el-input v-model="form.remark" type = "textarea" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="评价" :visible.sync="evaluateShowBox" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form :model="evalform" label-width="120px">
        <el-form-item label="评价内容" >
          <el-input v-model="evalform.content" type = "textarea" maxlength = "500"></el-input>
        </el-form-item>
        <el-form-item label="评分" style="display:flex; align-items: center;" class="evastar">
          <el-rate
            v-model="evalform.score"
            show-score
            text-color="#ff9900"
            score-template="{value}">
          </el-rate>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="evaluateShowBox = false">取 消</el-button>
        <el-button type="primary" @click="handleEvaluate">确 定</el-button>
      </div>
    </el-dialog>
    <globalDialog :dialogVisible = app.dialogVisibleValue></globalDialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder, evaluate, evaluateDetail } from "@/api/bajiaostar/order";
import { userList } from "@/api/bajiaostar/task";
import { enumlistType } from "@/api/bajiaostar/type";
import { mapState } from 'vuex'
export default {
  name: "Order",
  data() {
    return {
      yqType: [],
      value2: '',
      pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 任务状态字典
      taskStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        version: null,
        taskId: null,
        taskStatus: null,
        taskIntegral: null,
        startTime: null,
        finishTime: null,
        distributeTime: null,
        confirmTime: null,
        doctorsId: null,
        distributeId: null,
        logisticsId: null
      },
      // 表单参数
      form: {
        task:{}
      },
      typeOptions: [],
      doctorUser: [],
      houqingUser: [],
      paifaUser: [],
      canEdit: true,
      // 表单校验
      rules: {
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        taskId: [
          { required: true, message: "任务id不能为空", trigger: "blur" }
        ],
        taskStatus: [
          { required: true, message: "任务状态不能为空", trigger: "blur" }
        ],
        // doctorsId: [
        //   { required: true, message: "医护id不能为空", trigger: "blur" }
        // ],
      },
      evaluateShowBox: false,
      evalform: {
        orderId: '',
        content: '',
        score: null
      }
    };
  },
   computed:{
      ...mapState(['app']),
    twoTaskArr() {
      console.log(this.form.oneTaskType)
      // this.form.twoTaskType = ""
      // 
      if (this.form.task.oneTaskType >= 0) {
        let objIndex 
        
        this.typeOptions.forEach((item, index) => {
          if (item.id == this.form.task.oneTaskType) {
            objIndex = index
          }
        })

       return this.typeOptions[objIndex].children

      } else {
        return []
      }
    }
  },
 async created() {
     await this.getDicts("yq_type").then(response => {
      console.log(response)
      this.yqType = response.data
    });
    this.getTreeselect()
     userList({userType: 4}).then(res => {
        console.log(res)
        this.doctorUser = res.data
      })
      userList({userType: 2}).then(res => {
        console.log(res)
        this.houqingUser = res.data
      })
      userList({userType: 0}).then(res => {
        console.log(res)
        this.paifaUser = res.data
      })
    this.getList();
    this.getDicts("task_status").then(response => {
      this.taskStatusOptions = response.data;
    });
  },
  methods: {
     getTreeselect() {
      enumlistType().then(response => {
        this.typeOptions = [];
        let data = {}
        data = this.handleTree(response.data, "id", "pid");
        console.log(data)
        this.typeOptions = data;
        console.log(this.typeOptions)
      });
    },
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
         if (this.orderList.length) {
            this.orderList.forEach(item => {
              item.yqType = this.yqType[item.yqType].dictLabel
            })
          }
        this.orderList.forEach(item => {
          this.doctorUser.forEach(it => {
            if (item.doctorsId == it.userId) {
              item.doctorsUserName = it.nickName
            }
          })
  
          this.paifaUser.forEach(it => {
            if (item.distributeId == it.userId) {
              item.distributeUserName = it.nickName
            }
          })

          this.houqingUser.forEach(it => {
            if (item.logisticsId == it.userId) {
              item.logisticsIdUserName = it.nickName
            }
          })
        })


        this.total = response.total;
        this.loading = false;
      });
    },
    // 任务状态字典翻译
    taskStatusFormat(row, column) {
      return this.selectDictLabel(this.taskStatusOptions, row.taskStatus);
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
        taskId: null,
        taskStatus: null,
        taskIntegral: null,
        startTime: null,
        finishTime: null,
        distributeTime: null,
        confirmTime: null,
        doctorsId: null,
        distributeId: null,
        logisticsId: null,
        task:{

        },
        oneTaskType: null,
        twoTaskType: null
      };
      // this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      console.log(this.value2)
      if (this.value2.length) {
        this.queryParams.beginDistributeTime = this.value2[0]
        this.queryParams.endDistributeTime = this.value2[1]

      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.value2 = ''
      this.queryParams.yqType = ''
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
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        if (this.yqType.length) {
             this.form.yqType = this.yqType[this.form.yqType].dictLabel
        }
        // this.form.forEach(item => {
          console.log(this.doctorUser)

          this.doctorUser.forEach(it => {
            if (this.form.doctorsId == it.userId) {
              this.form.doctorsUserName = it.nickName
            }
          })
  
          this.paifaUser.forEach(it => {
            if (this.form.distributeId == it.userId) {
              this.form.distributeUserName = it.nickName
            }
          })

          this.houqingUser.forEach(it => {
            if (this.form.logisticsId == it.userId) {
              this.form.logisticsIdUserName = it.nickName
            }
          })
        // })

        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            this.yqType.forEach((item, index) => {
              if (item.dictLabel == this.form.yqType) {
                console.log(index)
                console.log(item)
                this.form.yqType = item.dictValue   
              }
            })
            updateOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            this.yqType.forEach((item, index) => {
              if (item.dictLabel == this.form.yqType) {
                console.log(index)
                console.log(item)
                this.form.yqType = item.dictValue   
              }
            })
            addOrder(this.form).then(response => {
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
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
       if (this.value2.length) {
        this.queryParams.beginDistributeTime = this.value2[0]
        this.queryParams.endDistributeTime = this.value2[1]

      }
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    showEvaluateBox (id) {
      console.log(id)
      this.evalform.orderId = id
      evaluateDetail(id).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.evalform.content = res.data.content
          this.evalform.score = res.data.score
           this.evaluateShowBox = true
        }
      }) 
     
    },
    handleEvaluate () {
      evaluate(this.evalform).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.msgSuccess("评价成功");
        } else {
          this.msgError("评价失败")
        }
      })
      this.evaluateShowBox = false
      this.evalform= {
         orderId: '',
         content: '',
         score: null
      }
    }
  }
};
</script>
<style>
.evastar .el-form-item__content{
  margin-left: 0 !important;
}
</style>