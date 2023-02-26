package com.kh.twks.rest.motionData;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "motionData", path = "motionData")
@CrossOrigin(origins = "http://localhost:5173")
public interface MotionDataRepository extends CrudRepository<MotionData,String> {

    @Query(nativeQuery = true, value = "SELECT * FROM motion_data m WHERE emp_id = :empId and CAST( motion_data_datetime AS DATE) =  CAST( :date AS DATE)")
    List<MotionData> getByDateEmpId(Date date, String empId);
}