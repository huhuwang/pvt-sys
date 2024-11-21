package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.PVTUser;
import com.hayes.pvtsys.query.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<PVTUser, Integer> {
    @Query(value = "SELECT u from PVTUser u WHERE (:#{#query.account} is null or :#{#query.account} = '' or u.account = :#{#query.account}) " +
            " and (:#{#query.userName} is null or u.userName LIKE CONCAT('%', :#{#query.userName}, '%'))" +
            " and (:#{#query.role} is null or u.role.id = :#{#query.role})")
    Page<PVTUser> findPage(Pageable pageable, @Param("query") UserQuery query);

}
