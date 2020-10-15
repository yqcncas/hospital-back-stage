<template>
     <el-dialog title="拒单任务重新分配" :visible.sync="dialogVisible" width="500px" append-to-body :close-on-click-modal = "false">
       
        <el-form  :model="app.taskMeaage" label-width="110px" >
              <el-form-item label="任务编号" prop="orderId" >
                <el-input v-model="app.taskMeaage.orderId" placeholder="暂无编号" disabled />
            </el-form-item>
             <el-form-item label="拒单后勤" prop="updateBy" >
                <el-input v-model="app.taskMeaage.order.nickName" disabled />
            </el-form-item>
             <el-form-item label="任务描述" prop="updateBy" >
                <el-input v-model="app.taskMeaage.order.remark" disabled />
            </el-form-item>
        </el-form>
     
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
</template>

<script>
import { mapActions, mapState } from 'vuex'
import { listTask, getTask, delTask, addTask, updateTask, exportTask, userList, addUserList, mergeAdd, recreate } from "@/api/bajiaostar/task";
export default {
    props: {
        dialogVisible: {
            type: Boolean,
            default: false
        }
    },
    
    mounted() {
        this.getUserList()
    },
    computed: {
        ...mapState(['app'])
    },
    data () {
        return {
            addAllocationUserId: [],
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
            canAllocationUserList: [],
               allocationform:{
                userIds: [],
                taskIntegral: '',
                orderId: ''
            },
        }
    },
    methods: {
        
        submitFormAllocation () {
              if (this.addAllocationUserId.length <= 0) return this.msgError("请选择分配用户");
             if (this.allocationform.taskIntegral < 0 || this.allocationform.taskIntegral.trim() == '') return this.msgError("请输入任务积分");

 
         this.allocationform.userIds = this.addAllocationUserId.join(',')
 
         this.allocationform.orderId = this.$store.state.app.taskMeaage.orderId

         recreate(this.allocationform).then(res => {
              if (res.code === 200) {
                  this.msgSuccess("分配成功");
                  this.dialogVisible = false
                    this.$store.dispatch('app/setUpDialog', false)
                  // this.$router.replace('/task/order')   //无痕浏览，无法回退
              }
            }).catch(() => {
              this.msgError("分配失败");
            })
        

    //   if (this.ids == '' ) {
    //       addUserList(this.allocationform).then(res => {
      
    //           if (res.code === 200) {
    //               this.msgSuccess("分配成功");
    //               this.dialogVisible = false
    //                 this.$store.dispatch('app/setUpDialog', false)
    //               // this.$router.replace('/task/order')   //无痕浏览，无法回退
    //           }
    //         }).catch(() => {
    //           this.msgError("分配失败");
    //         })
    //   } else {
    //       mergeAdd(this.allocationform).then(res => {
      
    //           if (res.code === 200) {
    //               this.msgSuccess("分配成功");
    //                this.dialogVisible = false
    //                 this.$store.dispatch('app/setUpDialog', false)
    //               // this.$router.replace('/task/order')   //无痕浏览，无法回退
    //           }
    //         }).catch(() => {
    //           this.msgError("分配失败");
    //         })
    //   }
            


           
        },
        cancelAllocation () {
            this.dialogVisible = false
            this.$store.dispatch('app/setUpDialog', false)
        },
        getUserList() {
             userList({jobStatus:1, userType: 2}).then(res => {
                // console.log(res)
                this.canAllocationUserList = res.data
                // if (typeof id == "number") {
                // this.allocationform.taskId = id 
                // } else {
                // this.allocationform.taskIds = id.join(',') 
                // }
                
               
            })
        }
    }
}
</script>

<style>

</style>