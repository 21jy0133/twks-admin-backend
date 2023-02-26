package com.kh.twks.rest.jobTitle;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;;

@RepositoryRestResource(collectionResourceRel = "jobTitle", path = "jobTitle")
@CrossOrigin(origins = "http://localhost:5173")
public interface JobTitleRepository extends CrudRepository<JobTitle,String> {
}