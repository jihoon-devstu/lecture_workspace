package com.sinse.electroshop.controller.admin;


import com.sinse.electroshop.controller.dto.StoreDto;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.exception.StoreNotFoundException;
import com.sinse.electroshop.model.store.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final StoreService storeService;

    @GetMapping("/admin/main")
    public String adminMain() {
        return "admin/index";
    }

    @GetMapping("/admin/loginform")
    public String loginForm() {
        return "admin/login";
    }

    //상점의 로그인 요청 처리
    @ResponseBody
    @PostMapping("/admin/login")
    public ResponseEntity<String> login(StoreDto storeDto, HttpSession session) {
        Store store = new Store();
        store.setBusinessId(storeDto.getId());
        store.setPassword(storeDto.getPwd());
        Store obj = storeService.login(store);
        session.setAttribute("store", obj);
        return ResponseEntity.ok("success");
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<String> handleStoreNotFoundException(StoreNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                    .body(ex.getMessage());
    }


}
