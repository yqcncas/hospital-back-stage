<template>

<!-- 我是单文件修改 -->
  <div class="index">
    <el-row style="position: absolute; right: 30px;top: 10px">
      <el-col >
         <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
      </el-col>
    </el-row>
    <el-row style="width: 100%; padding: 50px; ">
      <el-col>
         <el-card  style="width: 100%;text-align:center;margin-top: 0px" shadow="always" >
              <div class="online-title" style="color: #515a6e;font-size: 16px;font-weight:bold;padding-bottom: 20px;">任务追踪</div>
              
                <el-table
                  :data="taskList"
                  border
                  stripe
                  max-height="300"
                  
  
                  style="width: 100%">
                  <el-table-column
                    prop="nickName"
                    label="勤务人"
                    align="center"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="startAddress"
                    label="起始地点"
                    align="center"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="endAddress"
                    align="center"
                    label="送达地点">
                  </el-table-column>
                    <el-table-column
                    prop="taskStatusText"
                    align="center"
                    label="任务状态"
                    width="180">
                  </el-table-column>

                    <el-table-column
                    prop="createTime"
                    label="任务开始时间"
                    align="center"
                    width="180">
                  </el-table-column>
                    <el-table-column
                    prop="remark"
                    label="任务备注"
                    align="center"
                    width="180">
                  </el-table-column>
                    <el-table-column
                    prop="sjfs"
                    label="交通工具"
                    align="center"
                    width="180">
                  </el-table-column>
                </el-table>
            </el-card>
      </el-col>
    </el-row>
    <el-row style="padding-left: 50px; padding-right: 50px;">
      <el-col :span="11">
            <el-card class="box-card" style="width: 100%;text-align:center;" shadow="always" >
              <div class="online-title" style="color: #515a6e;font-size: 16px;font-weight:bold;padding-bottom: 20px;">在线勤务</div>
                <el-table
                  :data="onlineUserList"
                  border
                  stripe
                  max-height="400"
                 
                  style="width: 100%">
                   <el-table-column
                      type="index"
                      label= "#"
                      align="center"
                      width="50">
                    </el-table-column>
                  <el-table-column
                    prop="nickName"
                    align="center"
                    label="用户名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="jobStatusText"
                    align="center"
                    label="状态">
                  </el-table-column>
                </el-table>
            </el-card>
      </el-col>
      <el-col :span="11">
      <div class="index-left" style="display: flex;align-items: center; width: 100%;margin-left: 135px">
        <div class="online-box" style="width: 100%;">
              <el-card class="box-card" style="width: 100%;text-align:center;" shadow="always" >
              <div class="online-title" style="color: #515a6e;font-size: 16px;font-weight:bold;padding-bottom: 20px;">积分排名</div>
              
                <el-table
                  :data="rankList"
                  border
                  stripe
                   max-height="400"
             
                  style="width: 100%">
                   <el-table-column
                      type="index"
                      align="center"
                      label= "排名"
                      width="50">
                    </el-table-column>
                  <el-table-column
                    prop="nickName"
                    label="用户名"
                    align="center"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="integral"
                    align="center"
                    label="积分">
                  </el-table-column>
                </el-table>
            </el-card>
        </div>
          
      </div>
      </el-col>

    </el-row>
      <!-- <div class="index-left" style="display: flex;align-items: center; width: 40%;margin-left: 20px">
        <div class="online-box" style="width: 100%;">
            <el-card class="box-card" style="width: 100%;text-align:center;" shadow="always" >
              <div class="online-title" style="color: #515a6e;font-size: 16px;font-weight:bold;padding-bottom: 20px;">在线勤务</div>
              
                <el-table
                  :data="onlineUserList"
                  border
                  stripe
                  max-height="250"
                  style="width: 100%">
                   <el-table-column
                      type="index"
                      label= "#"
                      align="center"
                      width="50">
                    </el-table-column>
                  <el-table-column
                    prop="nickName"
                    align="center"
                    label="用户名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="jobStatusText"
                    align="center"
                    label="状态">
                  </el-table-column>
                </el-table>
            </el-card>
        </div>
          
      </div> -->
      <!-- <div class="index-right" style="width: 55%;margin-left: 2%;">
              <el-card class="box-card" style="width: 100%;text-align:center; margin-top: 50px" shadow="always" >
              <div class="online-title" style="color: #515a6e;font-size: 16px;font-weight:bold;padding-bottom: 20px;">积分排名</div>
              
                <el-table
                  :data="rankList"
                  border
                  stripe
                   max-height="250"
                  style="width: 100%">
                   <el-table-column
                      type="index"
                      align="center"
                      label= "排名"
                      width="50">
                    </el-table-column>
                  <el-table-column
                    prop="nickName"
                    label="用户名"
                    align="center"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="integral"
                    align="center"
                    label="积分">
                  </el-table-column>
                </el-table>
            </el-card>


           
      </div> -->

     <audio src="../assets/tishi.mp3"  ref="audioRef"></audio>
     <audio id = "bdAudio" ref = "bdRef" src= ""></audio>
    
    <globalDialog :dialogVisible = app.dialogVisibleValue></globalDialog>
     
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import { getOnlineUser, getRank, getTask, bdToken } from "@/api/getIndex";
import { mapState } from 'vuex'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  mounted() {
    this.getBdToken()
    this.getOnlineUserList()
    this.getRankList()
    this.getTaskList()
    // this.initWebSocket();
  },
  computed: {
    ...mapState(['app']),

  },

  data() {
    return {
      bdToken: '',
      lineChartData: lineChartData.newVisitis,
      onlineUserList: [],
      rankList: [],
      tableData: [],
      taskList: []
    }
  },
  methods: {
    getBdToken () {
      bdToken().then(res => {
        this.bdToken = res.data.access_token
      })
    },
    initWebSocket () {
        let userId = localStorage.getItem('userId')
        const wsuri = `ws://192.168.8.8:8097/websocket/${userId}`;
        // console.log(wsuri)
        this.websock = new WebSocket(wsuri);
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        // this.websock.onclose = this.websocketclose;
    },
    websocketonopen(){ //连接建立之后执行send方法发送数据
      // let actions = {"test":"12345"};
      // this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror(){//连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e){ //数据接收
     console.log(JSON.parse(e.data).data)
    if (JSON.parse(e.data) == 'taskUpd') {
     this.$refs.audioRef.play()

    } else if (JSON.parse(e.data) == 1) {

    } else {
      let strurl = "http://tsn.baidu.com//text2audio?tex="
      let strtext  = encodeURI(JSON.parse(e.data).data)
      strtext = encodeURI(strtext);
      strurl += strtext;
      strurl += `&lan=zh&tok=${this.bdToken}&ctp=1&cuid=${localStorage.getItem('userId')}`;
      console.log(strurl)
        let isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
        if (!isChrome) {
        var n = new Audio(strurl);
        n.src = strurl;
        n.play();
    } else {
       window.AudioContext = window.AudioContext || window.webkitAudioContext || window.mozAudioContext || window.msAudioContext;
        try {
             var context = new window.AudioContext();;
             var source = null;
             var audioBuffer = null;
             function playSound() {
                 source = context.createBufferSource();
                 source.buffer = audioBuffer;
                 source.loop = false;
                 source.connect(context.destination);
                 source.start(0); //立即播放
             }
 
             function initSound(arrayBuffer) {
                 context.decodeAudioData(arrayBuffer, function (buffer) { //解码成功时的回调函数
                     audioBuffer = buffer;
                     playSound();
                 }, function (e) { //解码出错时的回调函数
                     console.log('Error decoding file', e);
                 });
             }
 
             function loadAudioFile(url) {
                 var xhr = new XMLHttpRequest(); //通过XHR下载音频文件
                 xhr.open('GET', url, true);
                 xhr.responseType = 'arraybuffer';
                 xhr.onload = function (e) { //下载完成
                     initSound(this.response);
                 };
                 xhr.send();
             }
              
             loadAudioFile(strurl);
         } catch (e) {
             console.log('!Your browser does not support AudioContext');
         }
             
    }
     
      

      
    }
    
    //  this.$refs.audioRef.oncanplay = () => {
    //     console.log(this.$refs.audioRef.duration)
    //     this.$refs.audioRef.play()
    //  }
    
      // const redata = JSON.parse(e.data);
    },
    websocketsend(Data){//数据发送
      this.websock.send(Data);
    },
    websocketclose(e){  //关闭
      console.log('断开连接',e);
    },

      
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    getOnlineUserList () {
      getOnlineUser().then(res => {
        // console.log(res)
        this.onlineUserList = res.data
        this.onlineUserList.forEach(item => {
          item.jobStatus == 0 ? item.jobStatusText = '离线': item.jobStatus == 1 ? item.jobStatusText = '空闲' : item.jobStatus == 2 ? item.jobStatusText = '工作中' : item.jobStatusText = '错误'
        })
      })
    },
    getRankList () {
      getRank().then(res => {
        // console.log(res)
        this.rankList = res.data
      })
    },
    getTaskList () {
      getTask().then(res => {
        console.log(res)
        this.taskList = res.data
        this.taskList.forEach(item => {
          item.taskStatus == 1 ? item.taskStatusText = '待执行' : item.taskStatus == 2 ? item.taskStatusText = '进行中': item.taskStatus == 3 ? item.taskStatusText = '已完成' :  item.taskStatus == 4 ? item.taskStatusText = '已取消' :  item.taskStatus == 5 ? item.taskStatusText = '已拒绝' : item.taskStatusText = '错误'
        })
      })
    },
    handleQuery () {
      // this.onlineUserList = [],
      // this.rankList = [],
      // this.tableData = []
      this.getOnlineUserList()
      this.getRankList()
      this.getTaskList()
       this.$message({
          message: '刷新成功',
          type: 'success'
        });
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
.index{
  width: 100%;
  height: 100%;
  min-height: calc(100vh - 84px);
  // display: flex;
  // align-content: center;
}

</style>
