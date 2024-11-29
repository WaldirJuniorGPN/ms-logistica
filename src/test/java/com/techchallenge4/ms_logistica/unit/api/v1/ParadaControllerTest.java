package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.ParadaController;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.ParadaService;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParadaController.class)
class ParadaControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/logistica/parada";
    private static final String ID_URL = BASE_URL + "/{id}";
    private static final String ID_STATUS_URL = ID_URL + "/status";

    @MockBean
    private ParadaService service;

    @Nested
    class PatchParadaStatus {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;
            var statusRequest = ParadaUtils.buildParadaStatusRequest();

            when(service.patchStatus(id, statusRequest)).thenReturn(ParadaUtils.buildParadaResponse());

            // When
            var response = mockMvc.perform(patch(ID_STATUS_URL, id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(statusRequest)));

            // Then
            response.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(ParadaUtils.buildParadaResponse())));
            verify(service).patchStatus(id, statusRequest);
        }
        @Test
        void shouldReturnNotFoundWhenResourceNotFoundException() throws Exception {
            // Given
            var id = 1L;
            var statusRequest = ParadaUtils.buildParadaStatusRequest();

            when(service.patchStatus(id, statusRequest)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var response = mockMvc.perform(patch(ID_STATUS_URL, id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(statusRequest)));

            // Then
            response.andExpect(status().isNotFound());
            verify(service).patchStatus(id, statusRequest);
        }
    }

}
