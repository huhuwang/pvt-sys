package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.PVTPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PVTPermission, Integer> {


}
