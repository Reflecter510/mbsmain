package com.hncboy.chatgpt.base.service.impl;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.base.domain.entity.FrontUserPermissionDO;
import com.hncboy.chatgpt.base.mapper.FrontUserPermissionMapper;
import com.hncboy.chatgpt.base.service.FrontUserPermissionService;
import org.springframework.stereotype.Service;

/**
 * 用户权限服务实现类
 *
 * @author Reflecter510
 */
@Service
public class FrontUserPermissionServiceImpl extends ServiceImpl<FrontUserPermissionMapper, FrontUserPermissionDO> implements FrontUserPermissionService {

    @Override
    public FrontUserPermissionDO createEmptyPermissionUser() {
        FrontUserPermissionDO userDO = new FrontUserPermissionDO();
//        FrontUserPermissionDO.("StarGPT_" + RandomUtil.randomString(6));
//        FrontUserPermissionDO.setLastLoginIp(null);
//        FrontUserPermissionDO.setDescription(null);
//        FrontUserPermissionDO.setAvatarVersion(0);
        this.save(userDO);
        return userDO;
    }

    @Override
    public FrontUserPermissionDO findUserInfoById(Integer permissionUserId) {
        return getById(permissionUserId);
    }

    @Override
    public void updateLastLoginIp(Integer permissionUserId) {
//        update(new FrontUserPermissionDO(), new LambdaUpdateWrapper<FrontUserPermissionDO>()
//                .set(FrontUserPermissionDO::getLastLoginIp, WebUtil.getIp())
//                .eq(FrontUserPermissionDO::getId, permissionUserId));
    }
}