package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Auth;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.entity.Restaurante;
import com.ufla.sgrr.domain.enums.TipoUsuario;
import com.ufla.sgrr.domain.mapper.MapperAuth;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import com.ufla.sgrr.repository.AuthRepository;
import com.ufla.sgrr.service.impl.AuthServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private RestauranteService restauranteService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MapperCliente mapperCliente;

    @Mock
    private MapperAuth mapperAuth;

    @Mock
    private MapperRestaurante mapperRestaurante;

    private final EasyRandom easyRandom = new EasyRandom();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrar() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);
        authDTO.setTipoUsuario(TipoUsuario.CLIENTE);
        var cliente = easyRandom.nextObject(Cliente.class);
        var auth = easyRandom.nextObject(Auth.class);

        when(authRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn(easyRandom.nextObject(String.class));
        when(authRepository.save(any(Auth.class))).thenReturn(easyRandom.nextObject(Auth.class));
        when(clienteService.criar(any(ClienteDTO.class))).thenReturn(authDTO.getCliente());
        when(mapperCliente.executar(any(ClienteDTO.class))).thenReturn(cliente);
        when(mapperAuth.executar(any(AuthDTO.class))).thenReturn(auth);

        AuthDTO result = authService.cadastrar(authDTO);

        assertNotNull(result);
        verify(authRepository, times(1)).existsByEmail(anyString());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(authRepository, times(1)).save(any(Auth.class));
        verify(clienteService, times(1)).criar(any(ClienteDTO.class));
    }

    @Test
    public void testLogar() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);

        Auth auth = easyRandom.nextObject(Auth.class);
        auth.setTipoUsuario(TipoUsuario.CLIENTE);

        var cliente = easyRandom.nextObject(Cliente.class);

        when(authRepository.findAuthByEmail(anyString())).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(clienteService.buscarPorCpf(anyString())).thenReturn(cliente);

        AuthDTO result = authService.logar(authDTO);

        assertNotNull(result);
        assertEquals(TipoUsuario.CLIENTE, result.getTipoUsuario());
        verify(authRepository, times(1)).findAuthByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(clienteService, times(1)).buscarPorCpf(anyString());
    }

    @Test
    public void testCadastrarComEmailExistente() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("test@test.com");

        when(authRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.cadastrar(authDTO));
        verify(authRepository, times(1)).existsByEmail(anyString());
    }

    @Test
    public void testLogarComUsuarioNaoEncontrado() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("test@test.com");

        when(authRepository.findAuthByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authService.logar(authDTO));
        verify(authRepository, times(1)).findAuthByEmail(anyString());
    }

    @Test
    public void testLogarComSenhaIncorreta() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("test@test.com");
        authDTO.setSenha("password");

        Auth auth = new Auth();
        auth.setEmail("test@test.com");
        auth.setSenha("encodedPassword");

        when(authRepository.findAuthByEmail(anyString())).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authService.logar(authDTO));
        verify(authRepository, times(1)).findAuthByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @Test
    public void testCadastrarRestaurante() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);
        authDTO.setTipoUsuario(TipoUsuario.RESTAURANTE);
        var restaurante = easyRandom.nextObject(Restaurante.class);
        var auth = easyRandom.nextObject(Auth.class);

        when(authRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn(easyRandom.nextObject(String.class));
        when(authRepository.save(any(Auth.class))).thenReturn(easyRandom.nextObject(Auth.class));
        when(restauranteService.criar(any(RestauranteDTO.class))).thenReturn(authDTO.getRestaurante());
        when(mapperRestaurante.executar(any(RestauranteDTO.class))).thenReturn(restaurante);
        when(mapperAuth.executar(any(AuthDTO.class))).thenReturn(auth);

        AuthDTO result = authService.cadastrar(authDTO);

        assertNotNull(result);
        verify(authRepository, times(1)).existsByEmail(anyString());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(authRepository, times(1)).save(any(Auth.class));
        verify(restauranteService, times(1)).criar(any(RestauranteDTO.class));
    }

    @Test
    public void testLogarRestaurante() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);
        authDTO.setTipoUsuario(TipoUsuario.RESTAURANTE);

        Auth auth = easyRandom.nextObject(Auth.class);
        auth.setTipoUsuario(TipoUsuario.RESTAURANTE);

        var restaurante = easyRandom.nextObject(Restaurante.class);

        when(authRepository.findAuthByEmail(anyString())).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(restauranteService.buscarPorCnpj(anyString())).thenReturn(restaurante);

        AuthDTO result = authService.logar(authDTO);

        assertNotNull(result);
        assertEquals(TipoUsuario.RESTAURANTE, result.getTipoUsuario());
        verify(authRepository, times(1)).findAuthByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(restauranteService, times(1)).buscarPorCnpj(anyString());
    }

    @Test
    public void testLogarAdministrador() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);
        authDTO.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

        Auth auth = easyRandom.nextObject(Auth.class);
        auth.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

        when(authRepository.findAuthByEmail(anyString())).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        AuthDTO result = authService.logar(authDTO);

        assertNotNull(result);
        assertEquals(TipoUsuario.ADMINISTRADOR, result.getTipoUsuario());
        verify(authRepository, times(1)).findAuthByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }
}
