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

import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsInventary;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsName;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsTotalInventory;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsType;
import com.angel.springbootlearning.persistence.ex52Bis.dto.ItemsValue;
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
        repository.save(new Item("Battleaxe", ItemType.WEAPON, 200.0, 3));
        repository.save(new Item("Mana Potion", ItemType.POTION, 40.0, 12));
        repository.save(new Item("Enchanted Ring", ItemType.MAGIC, 500.0, 2));
        repository.save(new Item("Steel Hammer", ItemType.TOOL, 75.0, 8));
        repository.save(new Item("Wooden Shield", ItemType.WEAPON, 90.0, 6));        

        List<ItemsName> items = repository.findAllItemsName();
        // Test the JPQL projection for item names.
        items.forEach(item -> logger.info("Item name: {}", item.name()));

        List<ItemsType> itemsType = repository.findAllItemsType();
        itemsType.forEach(item -> logger.info("Item type: {}", item.type()));
/*
        List<ItemsValue> itemsValue = repository.findAllItemsValue();
        itemsValue.forEach(item -> logger.info("Item value: {}", item.value()));
*/
        logger.info("Total value: {}", repository.getTotalValue().total());

        List<ItemsInventary> itemsInventary = repository.findItemsInventary();
        itemsInventary.forEach(item -> logger.info("Item inventary: {}", item.inventary()));

        logger.info("Total inventory: {}", repository.getTotalInventary().total());

        // Test the JPQL projection for the item names and types
        repository.findItemsNameType().forEach(item -> logger.info("Name: {} | Type: {}", item.name(), item.type()));

        // Test the JPQL projection for item types & values
        repository.findItemsTypeValue().forEach(item -> logger.info("Type: {} | Value: {}", item.type(), item.value()));

        // Test the JPQL projection for name & value
        repository.findItemsNameValue().forEach(item -> logger.info("Name: {} | Value: {}", item.name(), item.value()));

        // Test the JPQL projection for name & inventary 
        repository.findItemsNameInventary().forEach(item -> logger.info(" Name: {} | Inventary: {}", item.name(), item.inventary()));

        // Type & inventary
        repository.findItemsTypeInventary().forEach(item -> logger.info("Type: {} | Inventary: {}", item.type(), item.inventary()));

        // Value & inventary
        repository.findItemsValueInventary().forEach(item -> logger.info("Value: {} | Inventary: {}", item.value(), item.inventary()));
    }
}