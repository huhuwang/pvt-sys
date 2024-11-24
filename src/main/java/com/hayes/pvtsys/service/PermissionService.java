package com.hayes.pvtsys.service;

import com.hayes.pvtsys.pojo.PVTPermission;
import com.hayes.pvtsys.repository.PermissionRepository;
import com.hayes.pvtsys.repository.RolePermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public List<PVTPermission> queryAllPermissions(){
        return permissionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<PVTPermission> queryAllPermissionsByRole(Integer roleId){
        return permissionRepository.queryAllByRole(roleId);
    }

    public void addPermissions(List<PVTPermission> permissions){
        permissionRepository.saveAll(permissions);
    }

    @Transactional
    public void deletePermissions(Integer permissionId){
        permissionRepository.deleteById(permissionId);
        rolePermissionRepository.deletePVTRolePermissionByPermissionID(permissionId);
    }
}
