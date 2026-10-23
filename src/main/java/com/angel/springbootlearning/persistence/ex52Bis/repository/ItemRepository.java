package com.angel.springbootlearning.persistence.ex52Bis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsInInventary;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsName;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameInventary;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameType;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameValue;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsType;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsTypeInventary;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsTypeValue;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsValue;
import com.angel.springbootlearning.persistence.ex52Bis.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
    /*
    JPQL (Jakarta Persistence Query Language).
    */
    @Query("SELECT new com.angel.srpingbootlearning.persistence.ex52Bis.dto.ItemsName(i.name) FROM Item i")
    List<ItemsName> findAllItemsName();

    @Query("SELECT new com.angel.srpingbootlearning.persistence.ex52Bis.dto.ItemsType(i.type) FROM Item i")
    List<ItemsType> findAllItemsType();

    @Query("SELECT new com.angel.srpingbootlearning.persistence.ex52Bis.dto.ItemsValue(i.value) FROM Item i")
    List<ItemsValue> findAllItemsValue();

    @Query("SELECT new com.angel.srpingbootlearning.persistence.ex52Bis.dto.ItemsInInventary(i.inventary) FROM Item i")
    List<ItemsInInventary> findAllItemsInInventary();

    @Query("SELECT new com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameType(i.name, i.type) FROM Item i")
    List<ItemsNameType> findItemsNameType();

    @Query("SELECT new com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameValue(i.name, i.value) FROM Item i")
    List<ItemsNameValue> findItemsNameValue();

    @Query("SELECT new com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsNameInventary(i.name, i.inventary) FROM Item i")
    List<ItemsNameInventary> findItemsNameInventary();

    @Query("SELECT new com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsTypeValue(i.type, i.value) FROM Item i")
    List<ItemsTypeValue> findItemsTypeValue();

    @Query("SELECT new com.angel.springbootlearning.persistence.ex52Bis.ItemsTypeInventory(i.type, i.inventary) FROM Item i")
    List<ItemsTypeInventary> findItemsTypeInventary();


}
 