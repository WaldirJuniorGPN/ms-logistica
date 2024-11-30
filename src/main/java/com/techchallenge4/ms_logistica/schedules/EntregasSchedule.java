package com.techchallenge4.ms_logistica.schedules;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EntregasSchedule {

    private final EntregasService service;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int STATE_INTERVAL_MINUTES = 10;

    @Scheduled(cron = "0 0 1 * * ?")
    public void processarPedidosPorEstado() {
        EnumSet.allOf(EstadoEnum.class).forEach(state ->
            scheduler.schedule(
                    () -> service.processarPedidosPorEstado(state),
                    state.ordinal() * STATE_INTERVAL_MINUTES,
                    TimeUnit.MINUTES
            )
        );
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void finalizarPedidos() {
        service.finalizarPedidos();
    }

}
