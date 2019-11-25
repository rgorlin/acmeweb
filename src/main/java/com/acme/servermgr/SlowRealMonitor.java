package com.acme.servermgr;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implement a 'real' server monitor, that gives back results that may differ from what unit tests expect.
 */
@Service
public class SlowRealMonitor   implements IMonitorableServer      {
    @Override
    public String determineServerStatus() {
        Boolean goodStatus = true;
        int     origNumCPUs;
        Runtime rt = Runtime.getRuntime();

        origNumCPUs = rt.availableProcessors();
        wait4seconds();
        if (rt.availableProcessors() != origNumCPUs) {
            goodStatus = false;
        }

        return goodStatus ? "Server is up and running well, #CPUs available is: " + origNumCPUs
                : "Server is up with a varying number of available CPUs";

    }

    /**
     * sleep 4 seconds
     */
    private void wait4seconds()
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
