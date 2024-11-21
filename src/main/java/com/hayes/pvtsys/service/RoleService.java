package com.hayes.pvtsys.service;

import com.hayes.pvtsys.pojo.PVTRole;
import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<PVTRole> queryAll(){
        return roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void addRole(List<PVTRole> roles){
        roleRepository.saveAll(roles);
    }

    public void deleteRole(Integer roleId){
        roleRepository.deleteById(roleId);
    }
}
