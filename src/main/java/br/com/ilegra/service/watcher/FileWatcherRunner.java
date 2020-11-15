package br.com.ilegra.service.watcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
