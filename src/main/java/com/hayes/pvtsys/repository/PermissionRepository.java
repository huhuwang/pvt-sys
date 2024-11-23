package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.PVTPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PVTPermission, Integer> {

    @Query(value = "select p from PVTPermission p where p.id in (:ids)")
    List<PVTPermission> queryAllByIds(@Param("ids") List<Integer> ids);

    @Query(value = "select p from PVTPermission p join PVTRolePermission rp on p.id = rp.permissionId where rp.principalId = :roleId order by p.id")
    List<PVTPermission> queryAllByRole(@Param("roleId") Integer roleId);
}
