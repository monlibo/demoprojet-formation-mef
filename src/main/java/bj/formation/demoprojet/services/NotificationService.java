package bj.formation.demoprojet.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Async
    public CompletableFuture<Void> sendMessage(String matricule) {
        try {
            Thread.sleep(10000); // Pause de 10 secondes (10 000 ms)
            logger.info("Notification: Message envoyé à l'agent avec matricule {}", matricule);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Erreur lors de l'envoi du message à l'agent {}", matricule, e);
        }
        return CompletableFuture.completedFuture(null);
    }
}

