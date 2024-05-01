package org.example.itemservice.service;

import org.example.itemservice.dto.ItemDto;
import org.example.itemservice.dto.ItemResponseDto;
import org.example.itemservice.entity.Item;
import org.example.itemservice.exception.ResourceNotFoundException;
import org.example.itemservice.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemResponseDto addItem(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        item.setCreatedAt(LocalDateTime.now());
        Item itemSave = itemRepository.save(item);
        ItemResponseDto saveItem = modelMapper.map(itemSave, ItemResponseDto.class);
        return saveItem;
    }

    @Override
    public List<ItemResponseDto> allItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map((item) -> modelMapper.map(item, ItemResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ItemResponseDto> searchItemsByTitle(String title) {
        List<Item> items = itemRepository.findAllByTitle(title);
        return items.stream().map((item) -> modelMapper.map(item, ItemResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public ItemResponseDto getDetail(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item is not found db with id :" + id));
        ItemResponseDto itemResponseDto = modelMapper.map(item, ItemResponseDto.class);
        return itemResponseDto;
    }

    @Override
    public ItemResponseDto updateItem(long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item is not found db with id :" + id));
        item.setTitle(itemDto.getTitle());
        item.setDescription(itemDto.getDescription());
        item.setUpdatedAt(LocalDateTime.now());
        item = itemRepository.save(item);
        ItemResponseDto itemResponseDto = modelMapper.map(item, ItemResponseDto.class);
        return itemResponseDto;
    }

    @Override
    public ItemResponseDto updateItemTitle(long id, String title) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item is not found db with id :" + id));
        item.setTitle(title);
        item.setUpdatedAt(LocalDateTime.now());
        item = itemRepository.save(item);
        ItemResponseDto itemResponseDto = modelMapper.map(item, ItemResponseDto.class);
        return itemResponseDto;
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item is not found db with id :" + id));
        itemRepository.deleteById(item.getId());
    }
}
