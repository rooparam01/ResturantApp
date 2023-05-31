package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.MenuItemRepository;
import com.masai.swiggy.Entity.MenuItem;
import com.masai.swiggy.Exception.SwiggyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class MenuItemServiceImpl implements  MenuItemService{

    @Autowired
    MenuItemRepository menuItemRepository;
    @Override
    public MenuItem additemToMenu(MenuItem menuItem) {
        if(menuItem.getMenuitemId()!=null) throw new SwiggyException("MenuitemId Auto Generated Please Not Pass");
        log.info("Try to add menu item in Service Class");
        MenuItem savedItem = menuItemRepository.save(menuItem);
        return savedItem;
    }
}
