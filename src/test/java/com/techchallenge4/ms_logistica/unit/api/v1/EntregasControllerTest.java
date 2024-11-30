package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.EntregasController;
import com.techchallenge4.ms_logistica.schedules.EntregasSchedule;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntregasController.class)
public class EntregasControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/logistica/interno/entregas";

    @MockBean
    private EntregasSchedule service;

    @Nested
    class ExecuteEntregasSchedule {
        @Test
        void shouldExecuteSuccessfully() throws Exception {
            // Given
            doNothing().when(service).processarPedidosPorEstado();

            // When
            mockMvc.perform(post(BASE_URL))
                    .andExpect(status().isNoContent());

            // Then
            verify(service, times(1)).processarPedidosPorEstado();
        }
    }

}
