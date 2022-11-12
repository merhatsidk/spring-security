package edu.miu.cs545.restApi;

import edu.miu.cs545.restApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class RestApiPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiPracApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.sa
//		}
//	}




}
