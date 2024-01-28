<script setup>
import { Plus, Upload } from '@element-plus/icons-vue'
import {ref} from 'vue'
import avatar from '@/assets/default.png'
import { userTokenStore } from '@/stores/token';
const TokenStore =userTokenStore()
const uploadRef = ref()

//导入pinia
import userUserInfoStore from '@/stores/userInfo.js'
const userInfoStore = userUserInfoStore();
//用户头像地址
const imgUrl = ref(userInfoStore.info.userPic)
//图片上传成功后的回调函数
const uploadSuccess = (result)=>{
    imgUrl.value= result.data
}
//头像修改
import { ElMessage } from 'element-plus';
import {userAvatarService} from '@/api/user.js'
const updataAvatar = async()=>{
   let result = await userAvatarService(imgUrl.value);
   ElMessage.success(result.msg?result.msg:'修改成功')
   //修改pinai中的数据
   userInfoStore.info.userPic = imgUrl.value
}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>更换头像</span>
            </div>
        </template>
        <el-row>
            <!-- 上传成功后的回调函数on-success -->
            <el-col :span="12">
                <el-upload 
                    :auto-upload="true"
                    action="/api/upload"
                    name="file"
                    :headers="{'Authorization':TokenStore.token}"
                    :on-success="uploadSuccess"  
                    ref="uploadRef"
                    class="avatar-uploader" 
                    :show-file-list="false"
                    >
                    <img v-if="imgUrl" :src="imgUrl" class="avatar" />
                    <img v-else :src="avatar" width="278" />
                </el-upload>
                <br />
                <el-button type="primary" :icon="Plus" size="large"  @click="uploadRef.$el.querySelector('input').click()">
                    选择图片
                </el-button>
                <el-button type="success" :icon="Upload" size="large" @click="updataAvatar">
                    上传头像
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
    :deep() {
        .avatar {
            width: 278px;
            height: 278px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 278px;
            height: 278px;
            text-align: center;
        }
    }
}
</style>