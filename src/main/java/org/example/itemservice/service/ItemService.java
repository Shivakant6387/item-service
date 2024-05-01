package org.example.itemservice.service;

import org.example.itemservice.dto.ItemDto;
import org.example.itemservice.dto.ItemResponseDto;
import org.example.itemservice.entity.Item;

import java.util.List;

public interface ItemService {
    ItemResponseDto addItem(ItemDto itemDto);

    List<ItemResponseDto> allItems();

    List<ItemResponseDto> searchItemsByTitle(String title);

    ItemResponseDto getDetail(long id);

    ItemResponseDto updateItem(long id, ItemDto itemDto);

    ItemResponseDto updateItemTitle(long id, String title);

    void deleteItem(long id);

}
