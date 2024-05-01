package org.example.itemservice.controller;

import org.example.itemservice.dto.ItemDto;
import org.example.itemservice.dto.ItemResponseDto;
import org.example.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody ItemDto itemDto) {
        ItemResponseDto ItemResponseDto = itemService.addItem(itemDto);
        return ResponseEntity.ok().body(ItemResponseDto);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        return ResponseEntity.ok().body(itemService.allItems());
    }

    @GetMapping("/getItemById/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long id) {
        ItemResponseDto itemResponseDto = itemService.getDetail(id);
        return ResponseEntity.ok().body(itemResponseDto);
    }

    @GetMapping("/searchItemByTitle")
    public ResponseEntity<ItemResponseDto> searchItemByTitle(@RequestParam String title) {
        List<ItemResponseDto> itemResponseDto = itemService.searchItemsByTitle(title);
        return ResponseEntity.ok().body(itemResponseDto.get(0));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        ItemResponseDto ItemResponseDto = itemService.updateItem(id, itemDto);
        return ResponseEntity.ok().body(ItemResponseDto);
    }

    @PutMapping("/update/{id}/title")
    public ResponseEntity<ItemResponseDto> updateItemTitle(@PathVariable Long id, @RequestParam String title) {
        ItemResponseDto ItemResponseDto = itemService.updateItemTitle(id, title);
        return ResponseEntity.ok().body(ItemResponseDto);
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Successfully Deleted Item with db");
    }
}
