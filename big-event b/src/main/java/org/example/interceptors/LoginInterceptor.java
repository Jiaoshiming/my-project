package org.example.interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
//HandlerInterceptor是Spring MVC中的一个接口，用于在请求处理过程中进行拦截和处理。
// 它的主要作用是在请求被Controller处理之前或之后执行一些自定义的操作，例如权限验证、日志记录等。要使用HandlerInterceptor，
// 需要实现其方法并注册到Spring MVC的拦截器链中。
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //令牌验证
        String token = request.getHeader("Authorization");
        try {
            //从redis获取
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();;
            String redisToken = operations.get(token);
            if (redisToken==null){
                //token失效了
                throw new RuntimeException();
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);//这一行能成功执行，
            ThreadLocalUtil.set(claims);
            // 证明已经获得jwt令牌，证明已经登录,放行
            return true;
        } catch (Exception e) {
            //未登录相应状态码为401
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
