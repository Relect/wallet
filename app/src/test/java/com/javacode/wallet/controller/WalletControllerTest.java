package com.javacode.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacode.wallet.custom.Type;
import com.javacode.wallet.dto.RequestWalletDto;
import com.javacode.wallet.dto.WalletDto;
import com.javacode.wallet.model.Wallet;
import com.javacode.wallet.service.WalletService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mvc;
    @Mock
    WalletService service;
    @InjectMocks
    WalletController walletController;

    @Test
    public void getWalletTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/wallets/8d1208fc-f401-496c-9cb8-483fef121234")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"walletId\":\"8d1208fc-f401-496c-9cb8-483fef121234\",\"amount\":100}"));
    }

    @Test
    public void addAmountTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(service.addAmount(Mockito.any(RequestWalletDto.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body("{\"walletId\":\"8d1208fc-f401-496c-9cb8-483fef555555\",\"amount\":500}"));

        RequestWalletDto requestWalletDto = new RequestWalletDto(UUID.fromString("8d1208fc-f401-496c-9cb8-483fef555555"),
                Type.DEPOSIT, 500);
        ResponseEntity<String> responseEntity = walletController.addAmount(requestWalletDto);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
