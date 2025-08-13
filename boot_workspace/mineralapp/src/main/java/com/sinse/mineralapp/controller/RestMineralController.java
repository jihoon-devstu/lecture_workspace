package com.sinse.mineralapp.controller;

import com.sinse.mineralapp.model.mineral.Mineral;
import com.sinse.mineralapp.model.mineral.MineralService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestMineralController {

    private final MineralService mineralService;

    public RestMineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    @GetMapping("/minerals")
    public List<Mineral> getMinerals() throws Exception {
        // 서비스에서 XML 파싱 후 mineral 리스트를 받아서 그대로 반환
        return mineralService.parse();
    }
}
