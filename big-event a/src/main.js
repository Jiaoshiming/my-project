import './assets/main.scss'

import { createApp } from 'vue'
import App from './App.vue'
//导入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//导入路由器
import router from '@/rounter'
//导入pinia
import {createPinia} from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin' 
//中文语言包
import locale from 'element-plus/dist/locale/zh-cn.js'
const app = createApp(App);
const persist =  createPersistedState();
const pinia =  createPinia();
pinia.use(persist)
app.use(pinia)
app.use(router)
app.use(ElementPlus,{locale});
app.mount('#app')
