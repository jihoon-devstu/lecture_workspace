package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.StoreDto;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.model.shop.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/store/loginform")
    public String loginForm() {
        return "store/login";
    }

    //상점의 로그인 요청 처리
    @ResponseBody
    @PostMapping("/store/login")
    public ResponseEntity<String> login(StoreDto storeDto){
        Store store = new Store();
        store.setBusiness_id(storeDto.getId());
        store.setPassword(storeDto.getPwd());
        store.setStore_name(storeDto.getStore_name());
        storeService.regist(store);
        return ResponseEntity.ok("success");
    }
}
