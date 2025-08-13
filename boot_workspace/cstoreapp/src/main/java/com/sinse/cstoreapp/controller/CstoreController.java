package com.sinse.cstoreapp.controller;

import com.sinse.cstoreapp.model.cstore.Cstore;
import com.sinse.cstoreapp.model.cstore.CstoreHandler;
import com.sinse.cstoreapp.model.cstore.CstoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CstoreController {

    private CstoreService cstoreService;
    public CstoreController(CstoreService cstoreService) {
        this.cstoreService = cstoreService;
    }

    @GetMapping("/cstores")
    public List<Cstore> getCstores() throws Exception{
        return cstoreService.parse();
    }
}
