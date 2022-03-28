package tn.spring.esprit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    private BatchLauncher batchLauncher;

    
    /*1. Lancer le batch (traitement de lots de données)
     *  grace au batch runner*/
    // @Scheduled(cron = "1 0 0 * * ?")
    // @Scheduled(fixedRate = 5000L)
     @Bean
     public void perform() throws Exception {
        log.info("Batch programmé pour tourner toutes les 5 minutes");
        batchLauncher.run();
    }
}
