package com.hayes.pvtsys.service;

import com.hayes.pvtsys.dto.PermissionDto;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.pojo.PVTPermission;
import com.hayes.pvtsys.pojo.PVTRole;
import com.hayes.pvtsys.pojo.PVTRolePermission;
import com.hayes.pvtsys.repository.PermissionRepository;
import com.hayes.pvtsys.repository.RolePermissionRepository;
import com.hayes.pvtsys.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public List<PVTRole> queryAll(){
        return roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void addRole(List<PVTRole> roles){
        roleRepository.saveAll(roles);
    }

    public void deleteRole(Integer roleId){
        roleRepository.deleteById(roleId);
    }

    @Transactional
    public void setPermission(PermissionDto permissionDto){
        PVTRole pvtRole = roleRepository.findById(permissionDto.getRoleId()).orElseThrow();
        List<PVTPermission> selectPermission = permissionRepository.queryAllByIds(permissionDto.getPermissionIds());

        rolePermissionRepository.deletePVTRolePermissionByRoleID(permissionDto.getRoleId());
        List<PVTRolePermission> add = new ArrayList<>();
        for (PVTPermission permission : selectPermission){
            PVTRolePermission rp = new PVTRolePermission();
            rp.setPrincipalId(pvtRole.getId());
            rp.setPermissionId(permission.getId());
            rp.setPrincipalType(Constants.PRINCIPAL_TYPE_ROLE);
            add.add(rp);
        }
        rolePermissionRepository.saveAll(add);
    }
}
