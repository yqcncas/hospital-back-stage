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
      <!-- <el-form-item label="任务类型" prop="taskType">
        <el-select v-model="queryParams.taskType" placeholder="请选择任务类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
      <el-form-item label="任务类型" prop="agreedFlag">
        <!-- <el-input
          v-model="queryParams.agreedFlag"
          placeholder="请输入预约标识(0即时 1预约)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.agreedFlag" placeholder="请选择任务类型">
            <el-option v-for="item in optionsArr"
                :key="item.dictSort"
                :label="item.dictLabel"
                :value="item.dictSort"  />
        </el-select>
      </el-form-item>


      <el-form-item label="创建时间" prop="taskNo">
         <el-date-picker
            v-model="queryParams.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
      </el-form-item>


      <el-form-item label="预约时间" prop="taskNo">
         <el-date-picker
            v-model="queryParams.agreedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
      </el-form-item>

      <el-form-item label="院区选择">
            <el-select
              v-model="queryParams.yqType"
              placeholder="院区选择"
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

        <el-form-item label="任务分类" prop="agreedFlag">
        <el-select v-model="queryParams.oneTaskType" placeholder="请选择任务分类">
            <el-option v-for="item in searchtypeOptions"
                :key="item.name"
                :label="item.name"
                :value="item.name"  />
        </el-select>
      </el-form-item>

         <!-- <el-form-item label="二级分类" prop="agreedFlag">

          <el-input v-model="queryParams.twoTaskType" placeholder="请输入任务分类" />

      </el-form-item> -->
      <el-form-item label="任务状态" prop="distributeFlag">
        <el-select v-model="queryParams.distributeFlag" placeholder="请选择任务状态" filterable>
            <el-option v-for="item in serchTaskStats"
                :key="item.id"
                :label="item.name"
                :value="item.id"  />
        </el-select>
      </el-form-item>
      

      <el-form-item label="起始地址" >
        <el-select v-model="queryParams.startKey" placeholder="请选择任务状态" filterable>
            <el-option v-for="item in addressList"
                :key="item.id"
                :label="item.address"
                :value="item.addressKey"  />
        </el-select>
      </el-form-item>

      <el-form-item label="目标地址" >
        <el-select v-model="queryParams.endKey" placeholder="请选择任务状态" filterable>
            <el-option v-for="item in addressList"
                :key="item.id"
                :label="item.address"
                :value="item.addressKey"  />
        </el-select>
      </el-form-item>

      <el-form-item label="病人查询" prop="xm">
        <el-input
          v-model="queryParams.xm"
          placeholder="请输入病人查询"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      
      

      <!-- <el-form-item label="预约标识" prop="agreedFlag">
          <el-select v-model="form.agreedFlag" placeholder="请选择任务类型">
            <el-option v-for="item in optionsArr"
                :key="item.dictSort"
                :label="item.dictLabel"
                :value="item.dictSort"  />
          </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="预约时间" prop="agreedTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.agreedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择预约时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="起始地址" prop="startAddress">
        <el-input
          v-model="queryParams.startAddress"
          placeholder="请输入起始地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标地址" prop="endAddress">
        <el-input
          v-model="queryParams.endAddress"
          placeholder="请输入目标地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态(1待执行 2进行中 3已完成 )" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="积分" prop="taskIntegral">
        <el-input
          v-model="queryParams.taskIntegral"
          placeholder="请输入积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择任务开始时间">
        </el-date-picker> -->
      <!-- </el-form-item>
      <el-form-item label="完成时间" prop="finishTime">
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
      </el-form-item> -->
      <!-- <el-form-item label="用户确认时间" prop="confirmTime">
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bajiaostar:task:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bajiaostar:task:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bajiaostar:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bajiaostar:task:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-s-tools"
          size="mini"
          @click="handleAllocation(ids, 0)"
          v-hasPermi="['bajiaostar:order:batch_merge_add']"
        >合并分配</el-button>
      </el-col>
         <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-s-tools"
          size="mini"
          @click="handleAllocation(ids, 1)"
          v-hasPermi="['bajiaostar:order:batch_add']"
        >批量分配</el-button>
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

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <!-- <el-table-column label="版本号" align="center" prop="version" /> -->
     
      <!-- <el-table-column label="任务类型" align="center" prop="taskType" /> -->
      <el-table-column label="任务编号" align="center" prop="taskNo" />
      <el-table-column label="任务类型" align="center" prop="taskListApFlag" />
      <el-table-column label="院区" align="center" prop="yqType" :show-overflow-tooltip="true" />
   
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />

      <el-table-column label="预约时间" align="center" prop="agreedTime" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.agreedTime, '{y}-{m}-{d} {h}:{}') }}</span> -->
          <span>{{ parseTime(scope.row.agreedTime)}}</span>

        </template>
      </el-table-column>
      <el-table-column label="任务状态" align="center" prop="distributeFlagText" />
       <el-table-column label="任务描述" align="center" prop="remark" width="300" />

      <el-table-column label="任务分类" align="center" prop="oneTaskType" />

      <el-table-column label="二级分类" align="center" prop="twoTaskType" />
      
      <el-table-column label="起始地址" align="center" prop="startAddress" />
      <el-table-column label="目标地址" align="center" prop="endAddress" />
      <el-table-column label="交通工具" align="center" prop="sjfs" />

      <!-- <el-table-column label="任务状态(1待执行 2进行中 3已完成 )" align="center" prop="taskStatus" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="派发时间" align="center" prop="distributeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.distributeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户确认时间" align="center" prop="confirmTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.confirmTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="派发人" align="center" prop="doctorsNickUserName" /> -->
      <!-- <el-table-column label="派发用户id" align="center" prop="distributeId" /> -->
      <!-- <el-table-column label="后勤用户id" align="center" prop="logisticsId" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bajiaostar:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bajiaostar:task:remove']"
          >删除</el-button>
          <el-button type="primary" size="small" @click = "handleAllocation(scope.row.id, 0)" v-hasPermi="['bajiaostar:order:batch_add']" >分配</el-button>
           
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

    <!-- 添加或修改任务管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <!-- <el-form-item label="版本号" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本号" />
        </el-form-item> -->
        <!-- <el-form-item label="逻辑删标识(0 未删 1 已删)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入逻辑删标识(0 未删 1 已删)" />
        </el-form-item> -->
        <el-form-item label="任务类型">
          <el-select v-model="form.agreedFlag" placeholder="请选择任务类型">
            <el-option v-for="item in optionsArr"
                :key="item.dictSort"
                :label="item.dictLabel"
                :value="item.dictSort"  />
          </el-select>
        </el-form-item>
         <el-form-item label="预约时间" prop="agreedTime" v-show="form.agreedFlag">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.agreedTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择预约时间">
          </el-date-picker>
   
        </el-form-item>

        <el-form-item label="任务编号" prop="taskNo" v-if="form.taskNo">
          <el-input v-model="form.taskNo" placeholder="请输入任务编号" />
        </el-form-item>
        

         <el-form-item label="任务分类" prop="oneTaskType"  >
          <el-select v-model="form.oneTaskType" placeholder="请选择任务类型" @change="handleOneTask" filterable>
            <el-option v-for="item in typeOptions"
                :key="item.name"
                :label="item.name"
                :value="item.name" 
                
                 />
          </el-select>
        </el-form-item>

      <el-form-item label="二级分类" prop="twoTaskType" v-if="twoTaskArr.length">

          <el-select v-model="form.twoTaskType" placeholder="请选择任务类型">
            <el-option v-for="item in twoTaskArr"
                :key="item.name"
                :label="item.name"
                :value="item.name" />
          </el-select>
       </el-form-item>
        <el-form-item label="院区选择" prop = "yqType">
              <el-select v-model="form.yqType" placeholder="请选择">
                <el-option
                  v-for="dict in yqType"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>

        <el-form-item label="起始地址" prop="startAddress">
          <el-select v-model="form.startAddress" placeholder="请输入起始地址" filterable @change = "handleStartAddress">
            <el-option v-for="item in addressList"
                
                :key="item.address"
                :label="item.address"
                :value="{address: item.address, addressKey: item.addressKey}"  />
          </el-select>
          
        </el-form-item>
        <el-form-item label="目标地址" prop="endAddress">
          <el-select v-model="form.endAddress" placeholder="请输入目标地址" filterable @change = "handlendAddress">
            <el-option v-for="item in addressList"
                :key="item.address"
                :label="item.address"
                :value="{address: item.address, addressKey: item.addressKey}"  />
          </el-select>
        </el-form-item>
        <el-form ref="allocationform" :rules = "allocationformRule" :model="allocationform" label-width="110px">
        <el-form-item label="分配后勤" prop="userIds">
          <el-select v-model="addAllocationUserId" placeholder="请选择分配后勤" multiple filterable >
            <el-option v-for="item in canAllocationUserList"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"  />
          </el-select>
        </el-form-item>

        <el-form-item label="积分" prop="taskIntegral" >
          <el-input v-model="allocationform.taskIntegral" placeholder="请输入积分" />
        </el-form-item>
    
      </el-form>

      <!-- <div class="startShowBox" v-if="isShowStart"> -->
        <!-- <el-form-item label="任务状态">
          <el-radio-group v-model="form.taskStatus">
              
            <el-radio :label="item.dictValue" v-for="(item, index) in radioArr" :key = "index" >{{item.dictLabel}}</el-radio>
            
          </el-radio-group>
        </el-form-item> -->

        <!-- <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择任务开始时间">
          </el-date-picker>
        </el-form-item> -->
        <!-- <el-form-item label="完成时间" prop="finishTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择任务完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="派发时间" prop="distributeTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.distributeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择派发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户确认时间" prop="confirmTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.confirmTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择用户确认时间">
          </el-date-picker>
        </el-form-item> -->
        <!-- <el-form-item label="医护id" prop="doctorsId" v-if="form.doctorsId">
          <el-input v-model="form.doctorsId" placeholder="请输入医护id" />
        </el-form-item> -->
        <!-- <el-form-item label="派发用户id" prop="distributeId" >
          <el-input v-model="form.distributeId" placeholder="请输入派发用户id" />
        </el-form-item>
        <el-form-item label="后勤用户id" prop="logisticsId">
          <el-input v-model="form.logisticsId" placeholder="请输入后勤用户id" />
        </el-form-item> -->
        <!-- </div> -->
         <el-form-item label="任务描述" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
          </el-form-item>

          <!-- <el-form-item label="分配后勤" prop="userIds">
          <el-select v-model="addAllocationUserId" placeholder="请选择分配后勤" multiple>
            <el-option v-for="item in canAllocationUserList"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"  />
          </el-select>
        </el-form-item>

        <el-form-item label="积分" prop="taskIntegral" >
          <el-input v-model="allocationform.taskIntegral" placeholder="请输入积分" />
        </el-form-item> -->
          
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>



     <!-- 添加或修改任务管理对话框 -->
    <el-dialog title="分配管理" :visible.sync="AllocationOpen" width="500px" append-to-body :close-on-click-modal = "false">
      <el-form ref="allocationform" :rules = "allocationformRule" :model="allocationform" label-width="110px">
        <el-form-item label="分配后勤" prop="userIds">
          <el-select v-model="addAllocationUserId" placeholder="请选择分配后勤" multiple filterable >
            <el-option v-for="item in canAllocationUserList"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"  />
          </el-select>
        </el-form-item>

        <el-form-item label="积分" prop="taskIntegral" >
          <el-input v-model="allocationform.taskIntegral" placeholder="请输入积分" />
        </el-form-item>
    
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormAllocation">确 定</el-button>
        <el-button @click="cancelAllocation">取 消</el-button>
      </div>
    </el-dialog>
    <globalDialog :dialogVisible = app.dialogVisibleValue></globalDialog>
  </div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask, exportTask, userList, addUserList, mergeAdd, batchAllot } from "@/api/bajiaostar/task";
import { listAddress } from "@/api/bajiaostar/address";
import { enumlistType } from "@/api/bajiaostar/type";

import { getDicts, getTaskType } from "@/api/system/dict/data";
import { mapState } from 'vuex'
// Vue.prototype.getDicts = getDicts

export default {
  name: "Task",
  computed:{
     ...mapState(['app']),
    twoTaskArr() {
      console.log(this.form.oneTaskType)
      // this.form.twoTaskType = ""
      // 
      if (this.form.oneTaskType != undefined) {
        let objIndex 
        console.log(this.typeOptions)
        this.typeOptions.forEach((item, index) => {
          if (item.name == this.form.oneTaskType) {
            objIndex = index
          }
        })

       return this.typeOptions[objIndex].children

      } else {
        return []
      }
    }
  },
 
  data() {
    return {
      yqType: [],
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
      // 任务管理表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        version: null,
        taskType: null,
        taskNo: null,
        agreedFlag: null,
        agreedTime: null,
        startAddress: "",
        endAddress: null,
        taskStatus: null,
        taskIntegral: null,
        startTime: null,
        finishTime: null,
        distributeTime: null,
        confirmTime: null,
        doctorsId: null,
        distributeId: null,
        logisticsId: null,
        isShowStart: true,
        oneTaskType: -1,
        oneTaskType: '',
        twoTaskType: '',
        startKey: null,
        endKey: null,
        distributeFlag: 0
      },
      // 表单参数
      form: {},
      AllocationOpen: false,
      allocationform:{
        userIds: [],
        taskIntegral: '',
        taskId: ''
      },
      canAllocationUserList: [],
      addAllocationUserId: [],
      // 表单校验
      allocationformRule: {
        userIds: [
          { required: true, message: "分配用户不能为空", trigger: "blur" }
        ],
        taskIntegral:[
           { required: true, message: "积分不能为空", trigger: "blur" },
        ],
        remark: [
          { required: true, message: "积分不能为空", trigger: "blur" }
        ],
        
      },
      levelOne: [],
      levelTwo: [], 
      serchTaskStats: [{name: '未分配', id: 0}, {name: '已分配', id: 1}],
      searchtypeOptions: [],
      doctorUser: [],
      rules: {
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        // version: [
        //   { required: true, message: "版本号不能为空", trigger: "blur" }
        // ],
        // delFlag: [
        //   { required: true, message: "逻辑删标识(0 未删 1 已删)不能为空", trigger: "blur" }
        // ],
        taskType: [
          { required: true, message: "任务类型不能为空", trigger: "blur" }
        ],
        // taskNo: [
        //   { required: true, message: "任务编号不能为空", trigger: "blur" }
        // ],
        startAddress: [
          { required: true, message: "起始地址不能为空", trigger: "blur" }
        ],
        endAddress: [
          { required: true, message: "目标地址不能为空", trigger: "blur" }
        ],
        yqType: [
           { required: true, message: "院区不能为空", trigger: "blur" },
        ],
        remark: [
          { required: true, message: "任务描述不能为空", trigger: "blur" }
        ],
        // taskStatus: [
        //   { required: true, message: "任务状态(1待执行 2进行中 3已完成 )不能为空", trigger: "blur" }
        // ],
        // doctorsId: [
        //   { required: true, message: "医护id不能为空", trigger: "blur" }
        // ],
        optionsArr: [],
        radioArr: [],
        addressList: [],
        typeOptions: [],
        allotType: 0
      }
    };
  },
 async created() {
   await this.getDicts("yq_type").then(response => {
      console.log(response)
      this.yqType = response.data
    });
     getTaskType({level: 1}).then(res => {
      console.log(res)
      this.levelOne = res.data
    })
    getTaskType({level: 2}).then(res => {
     this.levelTwo = res.data
    })
    userList({userType: 4}).then(res => {
        console.log(res)
        this.doctorUser = res.data
      })
    
    this.getTreeselect()
    this.getList();
     
    this.getAddressList();

  },
  mounted() {
    this.getDicts("agreed_flag").then(response => {
      this.optionsArr = response.data
    });

    this.getDicts("task_status").then(response => {
      console.log(response)
      this.radioArr = response.data
    });
  
  },

  methods: {
    //  起始地址
    handleStartAddress (e) {
      const {address, addressKey } = e
      console.log(address, addressKey)
      this.form.startAddress = address
      this.form.startKey = addressKey
    },
    handlendAddress(e) {
      const {address, addressKey } = e
      
      this.form.endAddress = address
      this.form.endKey = addressKey
    },
    /** 查询任务管理列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
         if (this.taskList.length) {
            this.taskList.forEach(item => {
              item.yqType = this.yqType[item.yqType].dictLabel
            })
          }
        if (this.taskList.length) {
          this.taskList.forEach(item => {
            item.taskListApFlag = item.agreedFlag == 0 ? '即时' : '预约'
            item.distributeFlagText = item.distributeFlag == 0 ? '未分配' : '已分配'
            this.levelOne.forEach((it, index) => {
              if (it.id == item.oneTaskType) {
                item.oneTaskTypeText = it.name
              } else {
                // item.oneTaskTypeText = ''
              }
            })

            if (item.twoTaskType != '') {
                this.levelTwo.forEach((it, index) => {
                if (it.id == item.twoTaskType) {
                  item.twoTaskTypeText = it.name
                } else {
                  //  item.twoTaskTypeText = ''
                }
              })
            } else {
              item.twoTaskTypeText = ''
            }

            this.doctorUser.forEach((it, index) => {
              if (item.doctorsId == it.userId){
                item.doctorsNickUserName = it.nickName
              }
              

            })

            

          })
        }
        this.total = response.total;
        this.loading = false;
      });
    },
    handlebatch(id) {
      if (id == '') {
        return this.msgError('请先选择要分配的任务')
      }
       this.AllocationOpen = true
    },
    handleAllocation(id, type) {
      this.allotType = type
      if (id == '') {
      return this.msgError('请先选择要分配的任务')
      }
       this.allocationform = {
          userIds: [],
          taskIntegral: '',
          taskId: '',
          taskIds: ''
       }
      userList({jobStatus:1, userType: 2}).then(res => {
        // console.log(res)
        this.canAllocationUserList = res.data
        if (typeof id == "number") {
         this.allocationform.taskId = id 
        } else {
          this.allocationform.taskIds = id.join(',') 
        }
      })
      this.AllocationOpen = true
    },
    submitFormAllocation() {
      if (this.addAllocationUserId.length <= 0) return this.msgError("请选择分配用户");
      if (this.allocationform.taskIntegral < 0 || this.allocationform.taskIntegral.trim() == '') return this.msgError("请输入任务积分");

      // this.allocationform.usersId = this.addAllocationUserId

      this.allocationform.userIds = this.addAllocationUserId.join(',')
      console.log(this.allotType)
        if (this.allotType == 0) {
          if (this.ids == '' ) {
              addUserList(this.allocationform).then(res => {
          
                  if (res.code === 200) {
                      this.msgSuccess("分配成功");
                      this.open = false;
                      // this.$router.replace('/task/order')   //无痕浏览，无法回退
                  }
                }).catch(() => {
                  this.msgError("分配失败");
                })
          } else {
              mergeAdd(this.allocationform).then(res => {
          
                  if (res.code === 200) {
                      this.msgSuccess("分配成功");
                      this.open = false;
                      // this.$router.replace('/task/order')   //无痕浏览，无法回退
                  }
                }).catch(() => {
                  this.msgError("分配失败");
                })
          }
      } else {
        console.log(this.ids)
        if (this.ids == '' ) {
              addUserList(this.allocationform).then(res => {
          
                  if (res.code === 200) {
                      this.msgSuccess("分配成功");
                      this.open = false;
                      // this.$router.replace('/task/order')   //无痕浏览，无法回退
                  }
                }).catch(() => {
                  this.msgError("分配失败");
                })
          } else {
              batchAllot(this.allocationform).then(res => {
          
                  if (res.code === 200) {
                      this.msgSuccess("分配成功");
                      this.open = false;
                      // this.$router.replace('/task/order')   //无痕浏览，无法回退
                  }
                }).catch(() => {
                  this.msgError("分配失败");
                })
          }

      }
     
  


      this.AllocationOpen = false
    },
    cancelAllocation() {
       this.AllocationOpen = false
    },
    getAddressList() {
       listAddress(this.queryParams).then(response => {
         console.log(response)
        this.addressList = response.data;
      });
    },
    handleOneTask () {
     this.$set(this.form, 'twoTaskType', "")
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    taskIndex(e) {
      console.log(e)
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
        taskType: null,
        taskNo: null,
        agreedFlag: null,
        agreedTime: null,
        startAddress: null,
        endAddress: null,
        taskStatus: "1",
        taskIntegral: null,
        startTime: null,
        finishTime: null,
        distributeTime: null,
        confirmTime: null,
        doctorsId: null,
        distributeId: null,
        logisticsId: null,
        remark: null
      };
      this.resetForm("form");
    },
    getTreeselect() {
      enumlistType().then(response => {
        this.searchtypeOptions = response.data;
        this.typeOptions = [];
        let data = {}
        data = this.handleTree(response.data, "id", "pid");
        console.log(data)
        this.typeOptions = data;
        console.log(this.typeOptions)
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams ={
        pageNum: 1,
        pageSize: 10,
        version: null,
        taskType: null,
        taskNo: null,
        agreedFlag: null,
        agreedTime: null,
        startAddress: null,
        endAddress: null,
        taskStatus: null,
        taskIntegral: null,
        startTime: null,
        finishTime: null,
        distributeTime: null,
        confirmTime: null,
        doctorsId: null,
        distributeId: null,
        logisticsId: null,
        isShowStart: true,
        oneTaskType: -1,
        oneTaskType: '',
        twoTaskType: '',
        yqType: ''
      },
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
        userList({jobStatus:1, userType: 2}).then(res => {
        // console.log(res)
        this.canAllocationUserList = res.data
        if (typeof id == "number") {
         this.allocationform.taskId = id 
        } else {
          this.allocationform.taskIds = id.join(',') 
        }
      })


      this.open = true;
      this.isShowStart = false
      this.title = "添加任务管理";
      
    },
    
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      
      getTask(id).then(response => {
        this.form = response.data;
        this.form.yqType = this.yqType[this.form.yqType].dictLabel
        this.open = true;
        this.title = "修改任务管理";
        this.isShowStart = true
        console.log(this.form)
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

            updateTask(this.form).then(response => {
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

            addTask(this.form).then(response => {
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
      this.$confirm('是否确认删除任务管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTask(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTask(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
