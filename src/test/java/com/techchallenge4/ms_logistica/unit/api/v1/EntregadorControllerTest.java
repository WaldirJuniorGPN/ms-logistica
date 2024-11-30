package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.EntregadorController;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntregadorController.class)
class EntregadorControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/logistica/entregador";
    private static final String ID_URL = BASE_URL + "/{id}";

    @MockBean
    private EntregadorService service;

    @Nested
    class CreateEntregador {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var request = EntregadorUtils.buildEntregadorRequest();
            var response = EntregadorUtils.buildEntregadorResponse();

            when(service.create(request)).thenReturn(response);

            // When
            var result = mockMvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).create(request);
        }
    }

    @Nested
    class GetAllEntregadores {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var response = List.of(EntregadorUtils.buildEntregadorResponse());

            when(service.findAll()).thenReturn(response);

            // When
            var result = mockMvc.perform(get(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findAll();
        }
    }

    @Nested
    class GetEntregadorById {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;
            var response = EntregadorUtils.buildEntregadorResponse();

            when(service.findById(id)).thenReturn(response);

            // When
            var result = mockMvc.perform(get(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findById(id);
        }
        @Test
        void shouldReturnNotFoundWhenResourceNotFound() throws Exception {
            // Given
            var id = 1L;

            when(service.findById(id)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(get(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).findById(id);
        }
    }

    @Nested
    class UpdateEntregador {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;
            var request = EntregadorUtils.buildEntregadorRequest();
            var response = EntregadorUtils.buildEntregadorResponse();

            when(service.update(id, request)).thenReturn(response);

            // When
            var result = mockMvc.perform(put(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).update(id, request);
        }
        @Test
        void shouldReturnNotFoundWhenResourceNotFound() throws Exception {
            // Given
            var id = 1L;
            var request = EntregadorUtils.buildEntregadorRequest();

            when(service.update(id, request)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(put(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).update(id, request);
        }
    }

    @Nested
    class DeleteEntregador {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;

            // When
            var result = mockMvc.perform(delete(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNoContent());
            verify(service).delete(id);
        }
        @Test
        void shouldReturnNotFoundWhenResourceNotFound() throws Exception {
            // Given
            var id = 1L;

            doThrow(new ResourceNotFoundException("not found")).when(service).delete(id);

            // When
            var result = mockMvc.perform(delete(ID_URL, id)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).delete(id);
        }
    }
}