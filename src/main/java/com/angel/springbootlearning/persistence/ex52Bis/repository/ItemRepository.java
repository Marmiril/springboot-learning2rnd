package com.angel.springbootlearning.persistence.ex52Bis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsInInvetary;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsName;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsType;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsValue;
import com.angel.springbootlearning.persistence.ex52Bis.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
    /*
    JPQL (Jakarta Persistence Query Language).
    */
    @Query("SELECT new com.angel.spingbootlearning.persistance.es52Bis.dto.ItemsName(i.name) FROM Item i")
    List<ItemsName> findAllItemsName();

    @Query("SELECT new com.angel.spingbootlearning.persistance.es52Bis.dto.ItemsType(i.type) FROM Item i")
    List<ItemsType> findAllItemsType();

    @Query("SELECT new com.angel.spingbootlearning.persistance.es52Bis.dto.ItemsValue(i.value) FROM Item i")
    List<ItemsValue> findAllItemsValue();

    @Query("SELECT new com.angel.spingbootlearning.persistance.es52Bis.dto.ItemsInInventary(i.inventary) FROM Item i")
    List<ItemsInInvetary> findAllItemsInInventary();
}
 