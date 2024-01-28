import request from '@/util/request.js'
import { userTokenStore } from '@/stores/token.js'
//文章分类列表查询
export const ArticleCategoryListService = ()=>{
   // const  tokenStore =userTokenStore();
    //在pinia中定义的响应式数据，都不需要.value，可以直接使用
   // return request.get('/category',{headers:{'Authorization':tokenStore.token}})
   return request.get('/category')
}

//文章分类添加接口
export const ArticleCategoryAddSercice=(categoryData)=>{
  return request.post('/category',categoryData)
}

//文章分类修改接口
export const AircleCategoryUpdateService=(categoryData)=>{
  return request.put('/category',categoryData)
}
//文章分类删除接口
export const AircleCategoryDeleteService=(id)=>{
  return request.delete('/category?id='+id)
}
//文章列表查询
export const ArticleListService=(params)=>{
   //传递类型为queryString
   return request.get('/article',{params:params})
}
//文章添加接口
export const ArticleAddService=(articleData)=>{
 return  request.post('/article',articleData)
}
//文章修改接口
export const ArticleUpdateService=(articleData)=>{
  return request.put('/article',articleData)
}
//文章删除接口
export const ArticleDeleteService=(id)=>{
   return request.delete('/article?id='+id)
}