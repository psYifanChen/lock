package com.example.lock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lock.entity.Lock;
import com.example.lock.repository.InventoryRepository;
import com.example.lock.repository.LockRepository;

@RestController
@RequestMapping("/inventory")
// @Scope("prototype")
public class InventoryController {
  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private LockRepository lockRepository;

  @GetMapping("count")
  public int count() {
    return this.inventoryRepository.findByProductCode("101").getCount();
  }

  @GetMapping("/safe-reduce")
  public void reduceWithLock() {
    try {

      // * lock
      var lock = new Lock("inventories", 1);
      var savedLock = this.lockRepository.save(lock);
      System.out.println("after lock");

      // * query
      var product = inventoryRepository.findByProductCode("101");
      product.setCount(product.getCount() - 1);
      this.inventoryRepository.save(product);
      System.out.println("after query");

      // * unlock
      this.lockRepository.delete(savedLock);
      System.out.println("after unlock");

    } catch (Exception e) {
      // e.printStackTrace();

      try {
        Thread.sleep(50);
        this.reduceWithLock();
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
    }
  }

  @GetMapping("/reduce")
  public void reduce() {
    var product = inventoryRepository.findByProductCode("101");
    product.setCount(product.getCount() - 1);
    this.inventoryRepository.save(product);
  }

  @GetMapping("/insert")
  public void insert() {
    this.lockRepository.save(new Lock("inventories", 1));
  }
}
