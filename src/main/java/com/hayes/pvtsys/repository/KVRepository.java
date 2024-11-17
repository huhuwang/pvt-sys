package com.hayes.pvtsys.repository;


import com.hayes.pvtsys.pojo.KVConstants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KVRepository extends JpaRepository<KVConstants, Integer> {

    void deleteKVConstantsByTemplateId(Integer templateId);


}
