package com.hayes.pvtsys.repository;

import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.pojo.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {


}
