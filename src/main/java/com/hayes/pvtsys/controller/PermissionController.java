package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.PVTPermission;
import com.hayes.pvtsys.service.PermissionService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('list-permisson')")
    public HttpResult<List<PVTPermission>> queryAllPermissions(){
        List<PVTPermission> pvtPermissions = permissionService.queryAllPermissions();
        return HttpResult.returnSuccess(pvtPermissions);
    }

    @GetMapping("/query/{roleId}")
    @PreAuthorize("hasAnyAuthority('query-permission-role')")
    public HttpResult<List<PVTPermission>> queryByRole(@PathVariable("roleId") Integer roleId){
        List<PVTPermission> pvtPermissions = permissionService.queryAllPermissionsByRole(roleId);
        return HttpResult.returnSuccess(pvtPermissions);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-permission')")
    public HttpResult<Boolean> addRoles(@RequestBody List<PVTPermission> permissions){
        permissionService.addPermissions(permissions);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/delete/{permissionId}")
    @PreAuthorize("hasAnyAuthority('delete-permission')")
    public HttpResult<Boolean> deletePermission(@PathVariable("permissionId") Integer permissionId){
        permissionService.deletePermissions(permissionId);
        return HttpResult.returnSuccess(true);
    }
}
