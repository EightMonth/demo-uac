package com.example.demo.uac.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 15:47
 */
@Configuration
@MapperScan("com.example.demo.uac.mapper")
public class MybatisPlusConfig {

    /**
     * 分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求页面大于最后页操作，true回到首页，false继续请求，默认false
        paginationInterceptor.setOverflow(false);
        // 单页最大上限
        paginationInterceptor.setLimit(500);
        // count join优化
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
