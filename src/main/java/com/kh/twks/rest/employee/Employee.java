package com.kh.twks.rest.employee;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.twks.rest.department.Department;
import com.kh.twks.rest.jobTitle.JobTitle;
import com.kh.twks.rest.rank.Rank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.ManyToOne;
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
@Table(name = "emp")
public class Employee {

  @Id
  @Column(name="emp_id", nullable=false, columnDefinition="CHAR(8)", length = 8)
  private String empId;


  @Column(name="emp_name")
  private String name;

  @Column(name="emp_email", length = 100, unique = true)
  private String email;

  @Column(name="emp_password", columnDefinition="CHAR(60)", length = 60)
  @JsonIgnore
  private String password;

  @Column(name="emp_sex", columnDefinition="CHAR(1)", length = 1)
  private String sex;

  @Column(name="emp_tel", columnDefinition="CHAR(13)", length = 13)
  private String tel;

  @Column(name="emp_kana")
  private String kana;

  @Column(name="emp_post_code", columnDefinition="CHAR(7)", length = 7)
  private String postCode;

  @Column(name="emp_address", length = 150)
  private String address;



  @Column(name="emp_birth_date")
  @Temporal(TemporalType.DATE)
  Date birthday;

  @Column(name="emp_init_date")
  @Temporal(TemporalType.DATE)
  Date initDate;

  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="dept_code", nullable=true)
  private Department department;


  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="rank_code", nullable=true)
  private Rank rank;

  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="job_title_code", nullable=true)
  private JobTitle jobTitle;
}