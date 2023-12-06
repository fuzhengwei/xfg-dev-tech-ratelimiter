package cn.bugstack.xfg.dev.tech.config;

import cn.bugstack.xfg.dev.tech.aop.RateLimiterAOP;
import com.google.common.cache.Cache;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RateLimiterAOPConfig {

    @Bean
    public RateLimiterAOP rateLimiter(){
        return new RateLimiterAOP();
    }

}
