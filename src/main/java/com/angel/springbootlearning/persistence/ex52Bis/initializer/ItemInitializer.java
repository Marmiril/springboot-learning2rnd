/*
 * Exercise 52 Bis - Item Initializer
 * Purpose: Insert sample items into H2 and test JPA queries.
 * URLs: Not available.
 */

package com.angel.springbootlearning.persistence.ex52Bis.initializer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsName;
import com.angel.springbootlearning.persistence.ex52Bis.models.Item;
import com.angel.springbootlearning.persistence.ex52Bis.models.ItemType;
import com.angel.springbootlearning.persistence.ex52Bis.repository.ItemRepository;

@Component 
public class ItemInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ItemInitializer.class);

    private final ItemRepository repository;

    public ItemInitializer(ItemRepository repository) { this.repository = repository; }

    @Override 
    public void run(String... args) {
        // Insert sample items into the database
        repository.save(new Item("Health potion", ItemType.POTION, 25.0, 15));
        repository.save(new Item("Iron Sword", ItemType.WEAPON, 120.0, 5));
        repository.save(new Item("Magic Wand", ItemType.MAGIC, 350.0, 3));
        repository.save(new Item("Pickaxe", ItemType.TOOL, 45.0, 10));

        List<ItemsName> items = repository.findAllItemsName();
        // Test the JPQL projection for item names.
        items.forEach(item -> logger.info("Item name: {}", item.name()));

        // Test the JPQL projection for the item names and types
        repository.findItemsNameType().forEach(item -> logger.info("Name: {} | Type: {}", item.name(), item.type()));

        // Test the JPQL projection for item types & values
        repository.findItemsTypeValue().forEach(item -> logger.info("Type: {} | Value: {}", item.type(), item.value()));
    }
}