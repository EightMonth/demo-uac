package com.example.demo.uac.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.uac.entity.SysRole;
import com.example.demo.uac.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:19
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, SysRole> {
    @Autowired
    private PermissionService permissionService;

    public List<SysRole> findByUserId(Long userId) {
        List<SysRole> sysRoleList = baseMapper.findByUserId(userId);
        fillPermission(sysRoleList);
        return sysRoleList;
    }

    public List<SysRole> list() {
        List<SysRole> sysRoleList = super.list();
        fillPermission(sysRoleList);
        return sysRoleList;
    }

    private void fillPermission(SysRole sysRole) {
        sysRole.setPermissions(permissionService.findByRoleId(sysRole.getId()));
    }

    private void fillPermission(List<SysRole> sysRoleList) {
        sysRoleList.forEach(this::fillPermission);
    }
}
