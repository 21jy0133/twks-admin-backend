package com.kh.twks.rest.screenshot;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "screenshot", path = "screenshot")
@CrossOrigin(origins = "http://localhost:5173")
public interface ScreenshotRepository extends CrudRepository<Screenshot,String> {

    @Query(nativeQuery = true, value = "SELECT * FROM screenshot_data m WHERE emp_id = :empId and CAST( screenshot_data_datetime AS DATE) =  CAST( :date AS DATE)")
    List<Screenshot> getByDateEmpId(Date date, String empId);
}