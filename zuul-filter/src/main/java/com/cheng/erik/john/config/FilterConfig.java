package com.cheng.erik.john.config;

import com.cheng.erik.john.filter.PasswordFilter;
import com.cheng.erik.john.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ：FilterConfig
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/30 10:59
 * @Description:
 */
@Configuration
public class FilterConfig {
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public PasswordFilter PasswordFilter() {
        return new PasswordFilter();
    }
}
