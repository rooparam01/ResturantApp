package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.MenuItem;
import com.masai.swiggy.Service.MenuItemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class MenuItemController {
    @Autowired
    MenuItemService menuItemService;

    @PostMapping("/menuitems")
    public ResponseEntity<MenuItem> addNewMenuItem(@Valid @RequestBody MenuItem menuItem){
       MenuItem savedItem =  menuItemService.additemToMenu(menuItem);

       log.info("Menu item Added Successful");

       return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }



}
