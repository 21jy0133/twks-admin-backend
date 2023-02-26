package com.kh.twks.rest.jobTitle;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_title")
public class JobTitle {

  @Id
  @Column(name="job_title_code", nullable=false, columnDefinition="CHAR(1)", length = 1)
  private String code;


  @Column(name="job_title_name", nullable=false)
  private String name;

  
}