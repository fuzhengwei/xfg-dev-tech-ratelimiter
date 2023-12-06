package cn.bugstack.xfg.dev.tech;

import cn.bugstack.xfg.dev.tech.annotation.AccessInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@SpringBootApplication
@Configurable
@RestController()
@RequestMapping("/api/ratelimiter/")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * curl http://localhost:8091/api/ratelimiter/login?fingerprint=uljpplllll01009&uId=1000&token=8790
     * <p>
     * AccessInterceptor
     * key: 以用户ID作为拦截，这个用户访问次数限制
     * fallbackMethod：失败后的回调方法，方法出入参保持一样
     * permitsPerSecond：每秒的访问频次限制
     * blacklistCount：超过多少次都被限制了，还访问的，扔到黑名单里24小时
     */
    @AccessInterceptor(key = "fingerprint", fallbackMethod = "loginErr", permitsPerSecond = 1.0d, blacklistCount = 10)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(String fingerprint, String uId, String token) {
        log.info("模拟登录 fingerprint:{}", fingerprint);
        return "模拟登录：登录成功 " + uId;
    }

    public String loginErr(String fingerprint, String uId, String token) {
        return "频次限制，请勿恶意访问！";
    }

}
