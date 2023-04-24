package com.hncboy.chatgpt.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncboy.chatgpt.base.domain.entity.FrontUserPermissionDO;

/**
 * 前端用户权限业务接口
 *
 * @author CoDeleven
 */
public interface FrontUserPermissionService extends IService<FrontUserPermissionDO> {

    /**
     * 创建一个空的权限用户信息
     *
     * @return 用户信息
     */
    FrontUserPermissionDO createEmptyPermissionUser();

    /**
     * 获取权限用户信息
     *
     * @param permissionUserId 权限用户 id
     * @return 用户信息
     */
    FrontUserPermissionDO findUserInfoById(Integer permissionUserId);

    /**
     * 更新上次登录 ip
     *
     * @param permissionUserId 权限用户 id
     */
    void updateLastLoginIp(Integer permissionUserId);
}