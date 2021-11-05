package com.example.demo.uac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.uac.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/2 17:22
 */
@Mapper
public interface PermissionMapper extends BaseMapper<SysPermission> {
    @Select("select p.* from t_sys_permission p inner join t_sys_role_permission rp on rp.permission_id = p.id where rp.role_id = #{roleId}")
    List<SysPermission> findByRoleId(@Param("roleId") Long id);
}
