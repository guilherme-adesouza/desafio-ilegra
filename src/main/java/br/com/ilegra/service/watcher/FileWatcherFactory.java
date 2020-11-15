package br.com.ilegra.service.watcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileWatcherFactory implements CommandLineRunner {

    @Autowired
    private FileWatcherService fileWatcherService;

    @Override
    public void run(String... args) throws Exception {
        fileWatcherService.run();
    }
}
