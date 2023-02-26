package com.kh.twks.rest.restTime;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "rest_time")
public class RestTime {
  @Id
  @Column(name="rest_time_id", length = 150)
  private String restTimeId;


  @Column(name = "rest_start_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;

  @Column(name = "rest_end_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date endTime;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="emp_id", nullable=true)
  private Employee employee;  
}