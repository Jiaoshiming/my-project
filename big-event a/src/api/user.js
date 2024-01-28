//导入request.js请求工具
import request from '@/util/request.js'

//提供调用注册接口的函数
export const userRegisterService =(registerData)=>{
    //借助于UrlSearchParams完成传递
    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key])
    }
  return  request.post('/user/register',params)
}
//提供调用登录接口的函数
export const userLoginService = (loginDate)=>{
    //借助于UrlSearchParams完成传递
   const params =  new URLSearchParams()
   for(let key in loginDate){
    params.append(key,loginDate[key])
   }
   return request.post('/user/login',params)
}
//获取用户详细信息
export const userInfoService=()=>{
 return  request.get('/user/userInfo')
}
//修改用户信息
export const userInfoUpdateService=(userInfoDate)=>{
  return request.put('/user/update',userInfoDate)
}
//修改用户头像
export const userAvatarService=(avatarUrl)=>{
 const params= new URLSearchParams();
 params.append('avatarUrl',avatarUrl)
  return request.patch('/user/updateAvatar',params)
}