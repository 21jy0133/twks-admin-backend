package com.kh.twks.rest.employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kh.twks.rest.department.Department;
import com.kh.twks.rest.jobTitle.JobTitle;;




@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
@CrossOrigin(origins = "http://localhost:5173")
public interface EmployeeRepository extends CrudRepository<Employee,String> {

    Employee findEmployeeByEmail(String email);
    Employee findEmployeeByEmpId(String empId);
    List<Employee> findEmployeeByDepartment_deptCode(String deptCode);
    List<Employee> findByEmpIdContainsOrNameContains( 
      String empId, 
      String name
    );

    @Query(nativeQuery = true, value = "SELECT max(emp_id) FROM emp u where u.emp_id like concat(:year, '%') ") // 
    String getMaxEmpIdByYear(@Param("year") String year );
}


@Projection(
  name = "employeeView", 
  types = { Employee.class }) 
interface EmployeeView {

    String getEmpId();
    String getName();
    Department getDepartment();
    
}

@Projection(
  name = "employeeDetailedView", 
  types = { Employee.class }) 
interface EmployeeDetailedView {

    String getEmpId();
    String getName();
    String getEmail();
    String getTel();
    String getSex();
    String getPostCode();
    String getAddress();
    String getKana();
    Date getInitDate();
    Date getbirthday();
    JobTitle getJobTitle();
    Department getDepartment();
    
}