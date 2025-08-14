package com.sinse.busapp.controller;

import com.sinse.busapp.model.Bus;
import com.sinse.busapp.model.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusController {

    private BusService busService;
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/buses")
    public List<Bus> getList() throws Exception{
        return busService.parse();
    }
}
