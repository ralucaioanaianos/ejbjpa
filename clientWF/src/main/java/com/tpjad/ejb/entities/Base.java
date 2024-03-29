package com.tpjad.ejb.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class Base {
  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = 0L;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}