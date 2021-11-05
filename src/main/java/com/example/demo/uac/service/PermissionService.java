package com.example.demo.uac.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.uac.entity.SysPermission;
import com.example.demo.uac.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:23
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, SysPermission> {

    public List<SysPermission> findByRoleId(Long roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}
