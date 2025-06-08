package com.zyy.config;

import com.zyy.interceptor.JwtTokenAdminInterceptor;
import com.zyy.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/activity/{id}", "/activity/list",
                        "/user/login", "/user/register");
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 添加自定义消息转换器来转换时间格式
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建一个消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 设置一个对象转换器，将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        // 将自己的消息转换器加入容器，并放在首位
        converters.add(0, converter);
    }

    /**
     * 配置跨域请求
     * @param registry
     */
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 应用于所有路径
                // 允许的前端域名（根据你的实际部署地址修改）
                .allowedOrigins(
                        "http://localhost:8080",       // 本地开发
                        "http://192.168.31.199",       // 生产前端
                        "http://127.0.0.1",       // 生产前端
                        "http://192.168.214.27"
//                        "https://www.yourfrontend.com" // 生产带www的前端
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*")               // 允许所有请求头
                .allowCredentials(true)            // 允许携带凭证（如cookies）
                .maxAge(3600);                    // 预检请求缓存时间（秒）
    }
}
