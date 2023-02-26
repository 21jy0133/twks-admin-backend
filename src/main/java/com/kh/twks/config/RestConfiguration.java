package com.kh.twks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.kh.twks.rest.department.Department;
import com.kh.twks.rest.employee.Employee;
import com.kh.twks.rest.jobTitle.JobTitle;
import com.kh.twks.rest.motionData.MotionData;
import com.kh.twks.rest.restTime.RestTime;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Department.class);
        config.exposeIdsFor(Employee.class);
        config.exposeIdsFor(JobTitle.class);
        config.exposeIdsFor(MotionData.class);
        config.exposeIdsFor(RestTime.class);
    }
}