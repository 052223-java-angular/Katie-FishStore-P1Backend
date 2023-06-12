package com.revature.katieskritters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;
import com.revature.katieskritters.services.RoleService;

@SpringBootApplication
public class KatieskrittersApplication {

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KatieskrittersApplication.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            String hostname = "localhost";
            System.out.println("Server started at: http://" + hostname + ":" + serverPort);

            // Save the "USER" Role if it doesn't exist in the 'roles' table
            String roleName = "USER";
            roleService.createUserRoleIfNotExists(roleName);

        };
    }
}



