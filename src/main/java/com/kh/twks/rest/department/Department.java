package com.kh.twks.rest.department;

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
@Table(name = "dept")
public class Department {

  @Id
  @Column(name="dept_code", nullable=false, columnDefinition="CHAR(3)", length = 3)
  private String deptCode;


  @Column(name="dept_name", nullable=false)
  private String deptName;

  
}