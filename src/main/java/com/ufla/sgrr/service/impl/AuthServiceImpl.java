package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.entity.Auth;
import com.ufla.sgrr.domain.enums.TipoUsuario;
import com.ufla.sgrr.domain.mapper.MapperAuth;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import com.ufla.sgrr.repository.AuthRepository;
import com.ufla.sgrr.service.AuthService;
import com.ufla.sgrr.service.ClienteService;
import com.ufla.sgrr.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestauranteService restauranteService;
    private final ClienteService clienteService;
    private final MapperAuth mapperAuth;
    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final MapperCliente mapperCliente;
    private final MapperRestaurante mapperRestaurante;

    @Override
    public AuthDTO cadastrar(AuthDTO auth) {
        cadastrarUsuario(auth);
        AuthDTO novoAuth = new AuthDTO();
        switch (auth.getTipoUsuario()) {
            case CLIENTE -> novoAuth.setCliente(clienteService.criar(auth.getCliente()));
            case RESTAURANTE -> novoAuth.setRestaurante(restauranteService.criar(auth.getRestaurante()));
        }
        return novoAuth;
    }

    @Override
    public AuthDTO logar(AuthDTO auth) {
        var usuario = autenticarUsuario(auth);
        switch (usuario.getTipoUsuario()) {
            case CLIENTE -> {
                var cliente = clienteService.buscarPorCpf(usuario.getClienteCpf());
                auth.setCliente(mapperCliente.executar(cliente));
                auth.setTipoUsuario(TipoUsuario.CLIENTE);
                return auth;
            }
            case RESTAURANTE -> {
                var restaurante = restauranteService.buscarPorCnpj(usuario.getRestauranteCnpj());
                auth.setRestaurante(mapperRestaurante.executar(restaurante));
                auth.setTipoUsuario(TipoUsuario.RESTAURANTE);
                return auth;
            }
        }
        auth.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        return auth;
    }

    private Auth autenticarUsuario(AuthDTO authDTO) {
        var usuario = repository.findAuthByEmail(authDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!passwordEncoder.matches(authDTO.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        return usuario;
    }

    private void cadastrarUsuario(AuthDTO authDTO) {
        if (repository.existsByEmail(authDTO.getEmail())) {
            throw new IllegalArgumentException("Este email já está em uso.");
        }
        var usuario = mapperAuth.executar(authDTO);
        usuario.setSenha(criptografarSenha(authDTO.getSenha()));
        repository.save(usuario);
    }

    private String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }
}
