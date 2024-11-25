package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PermissionDto;
import com.hayes.pvtsys.pojo.PVTRole;
import com.hayes.pvtsys.service.RoleService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('list-role')")
    public HttpResult<List<PVTRole>> queryAllRoles(){
        List<PVTRole> roles = roleService.queryAll();
        return HttpResult.returnSuccess(roles);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-role')")
    public HttpResult<Boolean> addRoles(@RequestBody List<PVTRole> roles){
        roleService.addRole(roles);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/delete/{roleId}")
    @PreAuthorize("hasAnyAuthority('delete-role')")
    public HttpResult<Boolean> deleteRole(@PathVariable("roleId") Integer roleId){
        roleService.deleteRole(roleId);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/permission/setting")
    @PreAuthorize("hasAnyAuthority('set-permission')")
    public HttpResult<Boolean> setPermission(@RequestBody PermissionDto permissionDto){
        roleService.setPermission(permissionDto);
        return HttpResult.returnSuccess(true);
    }

}
