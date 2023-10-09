package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseUtil saveItem(ItemDTO code) {
        itemService.saveItem(code);
        return new ResponseUtil("Ok", "Successfully Saved", code);
    }

    @GetMapping
    public ResponseUtil loadAllItems() {
        return new ResponseUtil("Ok", "Successfully Loaded!", itemService.getAllItem());

    }

    @DeleteMapping({"code"})
    public ResponseUtil deleteItem(String code) {
        itemService.deleteItem(code);
        return new ResponseUtil("Ok", "Successfully Deleted!", code);

    }

    @GetMapping(params = {"code"})
    public ResponseUtil findItem(String code) {
        itemService.findItem(code);
        return new ResponseUtil("Ok", "Successfull", itemService.findItem(code));

    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO i) {
        itemService.updateItem(i);
        return new ResponseUtil("Ok", "Update Successful", i);
    }


}