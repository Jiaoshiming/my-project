package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Article;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.ArticleService;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
//    @RequestHeader(name = "Authorization") String token, HttpServletResponse response
    public Result<String> list(){
//        //验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);//这一行能成功执行，
//            // 证明已经获得jwt令牌，证明已经登录
//            return Result.success("所有的文章数据");
//        } catch (Exception e) {
//            //未登录相应状态码为401
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("所有的文章数据");
    }
    //新增文章
    @PostMapping
    public Result add( @RequestBody @Validated(Article.Add.class) Article article){
        articleService.add(article);
        return Result.success();
    }
    //分页查询
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false)   Integer categoryId,
            @RequestParam(required = false)     String state
    ){
        PageBean<Article>  pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
    //编辑文章
    @PutMapping
    public Result update(@RequestBody @Validated(Article.Update.class) Article article ){
        articleService.update(article);
        return Result.success();
    }
    //删除文章
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }

}
