package com.hayes.pvtsys.service;

import com.hayes.pvtsys.pojo.PVTPermission;
import com.hayes.pvtsys.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<PVTPermission> queryAllPermissions(){
        return permissionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<PVTPermission> queryAllPermissionsByRole(Integer roleId){
        return permissionRepository.queryAllByRole(roleId);
    }

    public void addPermissions(List<PVTPermission> permissions){
        permissionRepository.saveAll(permissions);
    }

    public void deletePermissions(Integer permissionId){
        permissionRepository.deleteById(permissionId);
    }
}
