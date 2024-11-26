package com.techchallenge4.ms_logistica.unit.schedules;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.schedules.EntregasSchedule;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EntregasScheduleTest {

    private final EntregasService service = mock(EntregasService.class);
    private final ScheduledExecutorService scheduler = mock(ScheduledExecutorService.class);
    private final EntregasSchedule schedule = new EntregasSchedule(service);

    @BeforeEach
    void setUp() throws Exception {
        var schedulerField = EntregasSchedule.class.getDeclaredField("scheduler");
        schedulerField.setAccessible(true);
        schedulerField.set(schedule, scheduler);
    }

    @Nested
    class ProcessarPedidosPorEstado {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            doNothing().when(service).processarPedidosPorEstado(any(EstadoEnum.class));
            var captor = ArgumentCaptor.forClass(Runnable.class);

            // When
            schedule.processarPedidosPorEstado();

            // Then
            verify(scheduler, times(EstadoEnum.values().length)).schedule(captor.capture(), any(Long.class), any(TimeUnit.class));
            var capturedTasks = captor.getAllValues();
            assertEquals(EstadoEnum.values().length, capturedTasks.size());
        }
    }

    @Nested
    class FinalizarPedidos {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            doNothing().when(service).finalizarPedidos();

            // When
            schedule.finalizarPedidos();

            // Then
            verify(service).finalizarPedidos();
        }
    }

}