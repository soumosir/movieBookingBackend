package com.soumosir.bookmydream;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.model.*;
import com.soumosir.bookmydream.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

@SpringBootApplication
@ComponentScan("com.soumosir.bookmydream")
public class BookmydreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmydreamApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
//	@Bean
//	public LoginAttemptService loginAttemptService(){
//		return new LoginAttemptService();
//	};

	@Bean
	CommandLineRunner run(UserService userService, HallService hallService, MovieService movieService, ScreeningService screeningService, ReservationService reservationService,EmailService emailService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			AppUser appUser1  = userService.saveUser(new AppUser(null,"Soumosir Dutta","sdutta","sdutta7@umd.edu","Maryland@1234567",new ArrayList<>()));
			AppUser appUser2  = userService.saveUser(new AppUser(null,"John Doe","jdoe","soumosir@gmail.com","Maryland@1234567",new ArrayList<>()));
			AppUser appUser3  = userService.saveUser(new AppUser(null,"Burry Bacca","bbacca","bscscs@ycns.com","Maryland@1234567",new ArrayList<>()));
			AppUser appUser4  = userService.saveUser(new AppUser(null,"Vincent Yu","vin","soumosirdutta@gmail.com","Maryland@1234",new ArrayList<>()));

			userService.addRoleToUser("sdutta","ROLE_ADMIN");

			Hall hall = hallService.saveHall(new Hall(null,"hyattsville pg plaza",5L,5L,new ArrayList<>()));
			Hall hall1 = hallService.saveHall(new Hall(null,"CP metro",5L,5L,new ArrayList<>()));


			Movie movie = movieService.saveMovie(new Movie(null,"Thor","Ruffalo","Chris Hemsworth","Origins of thor",120L));
			Movie movie1 = movieService.saveMovie(new Movie(null,"Avenger","Ruffalo bros","Chris Evans","Origins of Avenger",120L));


			Screening screening1 = screeningService.saveScreening(new Screening(
					null,
					Timestamp.valueOf(LocalDateTime.of(2022,12,20,10,20)),
					movie,
					hall,
					null
			));
			Screening screening2  = screeningService.saveScreening(new Screening(
					null,
					Timestamp.valueOf(LocalDateTime.of(2022,12,25,10,30)),
					movie,
					hall,
					null
			));

			Reservation reservation1 = reservationService.saveReservation(new Reservation(
					null,
					Timestamp.valueOf(LocalDateTime.now()),
					1L,2L,
					appUser1,
					screening1
			));

			reservationService.saveReservation(new Reservation(
					null,
					Timestamp.valueOf(LocalDateTime.now()),
					1L,3L,
					appUser2,
					screening1
			));

			reservationService.saveReservation(new Reservation(
					null,
					Timestamp.valueOf(LocalDateTime.now()),
					1L,4L,
					appUser2,
					screening1
			));

			reservationService.saveReservation(new Reservation(
					null,
					Timestamp.valueOf(LocalDateTime.now()),
					1L,5L,
					appUser2,
					screening1
			));
//			emailService.sendEmail("ABC TEST","abc.com","123");
		};
	}

}
