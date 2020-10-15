import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'

import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";

import globalDialog from './views/components/globalDialog'
Vue.component('globalDialog', globalDialog)

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

// 全局组件挂载
Vue.component('Pagination', Pagination)

Vue.use(permission)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

import { createSocket } from '@sven0706/websocket'
import { bdToken } from "@/api/getIndex";
import { Message } from 'element-ui';
// import { mapActions } from 'vuex'
let timer = setInterval(() => {
  console.log(document.cookie.includes('Admin-Token'))
  if (localStorage.getItem('userId') && document.cookie.includes('Admin-Token')){
    console.log('**************')
    console.log('链接进入')
  
    console.log('**************')
    clearInterval(timer)
    let userId = localStorage.getItem('userId')
    localStorage.setItem('webSocketUrl', `ws://134.175.151.248:8097/websocket/${userId}`)
    createSocket(`ws://134.175.151.248:8097/websocket/${userId}`)
    
    const getsocketData = e => {  // 创建接收消息函数
      

      let messageData = e.detail.data
      bdToken().then(res => {
        let bdToken = res.data.access_token
        console.log(JSON.parse(messageData))

        if (JSON.parse(messageData).data == '"taskUpd"') {
          // console.log('111111')
          var a = "https://img.bajiaostar.com/tishi.mp3";
          var aMp3 = new Audio(a);
          aMp3.play(); //播放 mp3这个音频对象

         } else if (JSON.parse(messageData).data == 1) {
           
            // console.log(store)
          // mapActions
            
         } else {
          
          console.log( JSON.parse(messageData).data)
          // sessionStorage.setItem('taskMessage', JSON.parse(messageData).data)
          store.dispatch('app/setUpDialog', true)

          store.dispatch('app/setUpTask', JSON.parse(JSON.parse(messageData).data))
          
          Message.warning({message: JSON.parse(messageData).msg, showClose: true})
          let strurl = "http://tsn.baidu.com//text2audio?tex="
          let strtext  = encodeURI(JSON.parse(messageData).msg)
          strtext = encodeURI(strtext);
          strurl += strtext;
          strurl += `&lan=zh&tok=${bdToken}&ctp=1&cuid=${localStorage.getItem('userId')}`;
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
      })
    }
    window.addEventListener('onmessageWS', getsocketData)

    
    


  } else {
    console.log('**************')
    console.log('链接失败')
  
    console.log('**************')
  }
}, 5000) 




Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
