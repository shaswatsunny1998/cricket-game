package com.game.cricket.controllers;

import com.game.cricket.doa.ClearData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clear-data")
public class ClearDataController {

    @Autowired
    ClearData clearData;

    @DeleteMapping("tables/basic")
    public void clearScores() {
        clearData.eraseData();
    }

    @DeleteMapping("tables/all")
    public String clearAllData() {
        return "ClearAllData";
        //clearData.clearAllData();
    }
}
