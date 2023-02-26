package com.kh.twks.rest.rank;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;;

@RepositoryRestResource(collectionResourceRel = "rank", path = "rank")
@CrossOrigin(origins = "http://localhost:5173")
public interface RankRepository extends CrudRepository<Rank,String> {
}