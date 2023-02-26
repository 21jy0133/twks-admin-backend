package com.kh.twks.rest.department;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;;

@RepositoryRestResource(collectionResourceRel = "department", path = "department")
@CrossOrigin(origins = "http://localhost:5173")
public interface DepartmentRepository extends CrudRepository<Department,String> {
}