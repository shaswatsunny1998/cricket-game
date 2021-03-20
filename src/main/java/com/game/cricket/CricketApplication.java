package com.game.cricket;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication

public class CricketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CricketApplication.class, args);
    }

}
