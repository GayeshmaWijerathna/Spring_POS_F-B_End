package lk.ijse.spring.service.impl;

import antlr.Token;
import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional // manage all the transactions here // AOP
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveItem(ItemDTO item) {
        if (itemRepo.existsById(item.getCode())) {
            throw new RuntimeException(item.getCode()+" is already in System, please insert a new Item Code");
        }
        itemRepo.save(mapper.map(item, Item.class));
    }

    @Override
    public void deleteItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Item is not available, please check again Item code..!");
        }
        itemRepo.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> allItems = itemRepo.findAll();
        return mapper.map(allItems, new TypeToken<List<ItemDTO>>(){
        }.getType());
    }

    @Override
    public ItemDTO findItem(String code) {
        if (!itemRepo.existsById(code)) {
            throw new RuntimeException(code+ " Item is not available, please check Item code again.!");
        }

        Item item = itemRepo.findById(code).get();
        return mapper.map(item, ItemDTO.class);
    }

    @Override
    public void updateItem(ItemDTO i) {
        if (!itemRepo.existsById(i.getCode())) {
            throw new RuntimeException(i.getCode()+ " Item is not available, please re-check item code!");
        }
        Item map = mapper.map(i, Item.class);
        itemRepo.save(map);

    }
}
