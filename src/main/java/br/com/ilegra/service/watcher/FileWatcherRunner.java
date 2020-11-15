package br.com.ilegra.service.watcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class FileWatcherRunner implements CommandLineRunner {

    private final FileWatcherService fileWatcherService;

    public FileWatcherRunner(FileWatcherService fileWatcherService) {
        this.fileWatcherService = fileWatcherService;
    }

    @Override
    public void run(String... args) throws Exception {
        fileWatcherService.run();
    }
}
