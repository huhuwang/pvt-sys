package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.KVConstants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KVRepository extends JpaRepository<KVConstants, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from KVConstants kv WHERE kv.templateId = :templateId")
    void deleteKVConstantsByTemplateId(@Param("templateId") Integer templateId);

}
