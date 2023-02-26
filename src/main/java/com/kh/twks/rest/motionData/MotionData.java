package com.kh.twks.rest.motionData;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.twks.rest.employee.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "motion_data")
public class MotionData {
  @Id
  @Column(name="motion_data_id", length = 150)
  private String motionDataId;

  @Column(name="motion_data", columnDefinition="BIGINT")
  private Integer motionData;

  @Column(name = "motion_data_datetime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date motionDataDatetime;

  @ManyToOne(fetch=FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name="emp_id", nullable=true)
  private Employee employee;  

  //@ManyToOne(fetch=FetchType.LAZY)
  @Column(name="motion_data_section_code", columnDefinition="CHAR(1)", length = 1)
  private String sectionCode; 
}