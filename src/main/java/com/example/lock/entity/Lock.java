package com.example.lock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "locks")
public class Lock {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  @Column(name = "table_name")
  public String tableName;

  @Column(name = "table_pk")
  public long tablePk;

  public Lock(String tableName, long tablePk) {
    this.tableName = tableName;
    this.tablePk = tablePk;
  }
}
