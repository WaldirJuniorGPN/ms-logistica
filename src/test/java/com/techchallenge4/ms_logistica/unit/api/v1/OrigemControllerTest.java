package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.OrigemController;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.OrigemService;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrigemController.class)
class OrigemControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/origem";
    private static final String ID_URL = BASE_URL + "/{id}";

    @MockBean
    private OrigemService service;

    @Nested
    class CreateOrigem {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var request = OrigemUtils.buildOrigemRequest();
            var response = OrigemUtils.buildOrigemResponse();

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
    class GetAllOrigens {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var response = List.of(OrigemUtils.buildOrigemResponse());

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
    class GetOrigemById {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;
            var response = OrigemUtils.buildOrigemResponse();

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
    class UpdateOrigem {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var id = 1L;
            var request = OrigemUtils.buildOrigemRequest();
            var response = OrigemUtils.buildOrigemResponse();

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
            var request = OrigemUtils.buildOrigemRequest();

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
    class DeleteOrigem {
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