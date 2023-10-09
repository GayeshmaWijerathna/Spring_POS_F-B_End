package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@CrossOrigin // will support to cors requests
public class ItemController {

    @Autowired
    ItemService service;


    @PostMapping
    public void saveItem(ItemDTO item) {
        service.saveItem(item);
    }

    public void getAllItems(){
        service.getAllItem();
    }
}
