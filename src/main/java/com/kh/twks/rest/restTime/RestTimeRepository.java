package com.kh.twks.rest.restTime;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "restTime", path = "restTime")
@CrossOrigin(origins = "http://localhost:5173")
public interface RestTimeRepository extends CrudRepository<RestTime,String> {

    @Query(nativeQuery = true, value = "SELECT * FROM rest_time m WHERE emp_id = :empId and CAST( rest_start_time AS DATE) =  CAST( :date AS DATE)")
    List<RestTime> getByDateEmpId(Date date, String empId);
}