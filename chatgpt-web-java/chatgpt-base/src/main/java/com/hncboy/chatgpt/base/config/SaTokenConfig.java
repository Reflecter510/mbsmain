package com.hncboy.chatgpt.base.config;

import cn.dev33.satoken.fun.SaFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.hncboy.chatgpt.base.domain.entity.FrontUserBaseDO;
import com.hncboy.chatgpt.base.domain.entity.FrontUserExtraBindingDO;
import com.hncboy.chatgpt.base.domain.entity.FrontUserPermissionDO;
import com.hncboy.chatgpt.base.exception.ServiceException;
import com.hncboy.chatgpt.base.service.FrontUserBaseService;
import com.hncboy.chatgpt.base.service.FrontUserExtraBindingService;
import com.hncboy.chatgpt.base.service.FrontUserPermissionService;
import com.hncboy.chatgpt.base.util.StpAdminUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

/**
 * @author hncboy
 * @date 2023/3/28 12:48
 * SaToken 配置，目前针对管理端鉴权
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    @Resource
    private FrontUserBaseService baseUserService;

    @Resource
    private FrontUserPermissionService permissionService;

    @Resource
    private FrontUserExtraBindingService bindingService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
                    // 管理端接口都必须管理端登录
                    SaRouter.match("/admin/**").check(r -> StpAdminUtil.checkLogin());
                    // 非管理端接口都必须 front 用户登录
                    SaRouter.notMatch("/admin/**").check(new SaFunction() {
                        @Override
                        public void run() {
                            StpUtil.checkLogin();
                            int baseUserId = Integer.parseInt(String.valueOf(StpUtil.getTokenInfo().getLoginId()));
                            FrontUserExtraBindingDO bindingDO = bindingService.findExtraBindingByUser(baseUserId);
                            if (Objects.isNull(bindingDO)) {
                                StpUtil.logout();
                            } else {
                                FrontUserPermissionDO userPermissionDO = permissionService.findUserInfoById(bindingDO.getPermissionId());
                                System.out.println(baseUserId + " check " + (Objects.nonNull(userPermissionDO) ? userPermissionDO.getValidityTime() : null));
                                if (Objects.isNull(userPermissionDO) || userPermissionDO.getValidityTime().getTime() < System.currentTimeMillis()) {
                                    StpUtil.logout();
                                    System.out.println(baseUserId + " kickout");
                                }
                            }
                        }
                    });
                }))
                // 放行管理端登录接口
                .excludePathPatterns("/admin/sys_user/login")
                // 放行用户端校验邮箱验证码
                .excludePathPatterns("/user/verify_email_code")
                // 放行用户端邮箱注册
                .excludePathPatterns("/user/register/email")
                // 放行用户端图形验证码
                .excludePathPatterns("/user/get_pic_code")
                // 放行用户端邮箱登录
                .excludePathPatterns("/user/login/email")
                // swagger 放行
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**");
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }
}
