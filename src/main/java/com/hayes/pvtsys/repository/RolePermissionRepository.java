package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.PVTRolePermission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolePermissionRepository extends JpaRepository<PVTRolePermission, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from PVTRolePermission WHERE principalId = :role")
    void deletePVTRolePermissionByRoleID(@Param("role") Integer role);

}
