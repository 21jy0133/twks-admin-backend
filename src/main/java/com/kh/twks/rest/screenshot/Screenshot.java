package com.kh.twks.rest.screenshot;

import java.util.Date;


import com.kh.twks.rest.employee.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "screenshot_data")
public class Screenshot {
  @Id
  @Column(name="screenshot_data_id", length = 150)
  private String id;

  @Column(name="screenshot_data_path", length = 150)
  private String path;

  @Column(name = "screenshot_data_datetime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date datetime;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="emp_id", nullable=true)
  private Employee employee;  
}