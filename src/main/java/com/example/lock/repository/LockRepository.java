package com.example.lock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lock.entity.Lock;

public interface LockRepository extends JpaRepository<Lock, Long> {
  public Lock findByTableNameAndTablePk(String tableName, long TablePk);
}
