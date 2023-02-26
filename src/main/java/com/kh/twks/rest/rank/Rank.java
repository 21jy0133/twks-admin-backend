package com.kh.twks.rest.rank;

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
@Table(name = "rank")
public class Rank {

  @Id
  @Column(name="rank_code", nullable=false, columnDefinition="CHAR(2)", length = 2)
  private String code;

  //@Column(name="rank_name", nullable=false, length = 40)
  //private String name; 
}