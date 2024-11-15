package com.techchallenge4.ms_logistica.schedules;

import com.techchallenge4.ms_logistica.enums.CepEnum;
import com.techchallenge4.ms_logistica.service.v1.implementation.EntregasServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EntregasSchedule {

    private final EntregasServiceImpl entregasServiceImpl;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int STATE_INTERVAL_MINUTES = 10;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleAllStates() {
        EnumSet.allOf(CepEnum.class).forEach(state -> {
            int delayMinutes = state.ordinal() * STATE_INTERVAL_MINUTES;
            scheduler.schedule(
                    () -> entregasServiceImpl.processDeliveriesForState(state),
                    delayMinutes,
                    TimeUnit.MINUTES
            );
        });
    }
}
