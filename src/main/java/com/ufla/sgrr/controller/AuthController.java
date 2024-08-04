package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthDTO logar(@RequestBody AuthDTO auth) {
        return authService.logar(auth);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthDTO cadastrar(@RequestBody AuthDTO auth) {
        return authService.cadastrar(auth);
    }
}
