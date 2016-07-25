package com.ercancan.myboot.h2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ercancan.myboot.h2.entity.Category;
import com.ercancan.myboot.h2.entity.Vehicle;
import com.ercancan.myboot.h2.repository.VehicleRepository;
import com.ercancan.myboot.h2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ercanc
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
	VehicleRepository vehicleRepository;

    @Autowired
	CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		Category diesel = new Category("diesel", "Diesel");
		Category gasoline = new Category("gasoline", "Gasoline");
		Category manuel = new Category("manuel", "Manuel");
		Category automatic = new Category("automatic", "Automatic");

		categoryRepository.save(diesel);
		categoryRepository.save(gasoline);
		categoryRepository.save(manuel);
		categoryRepository.save(automatic);

		List<Vehicle> vhicles = new LinkedList<Vehicle>();
		vhicles.add(new Vehicle("Fiat", "Egea", "www.fiat.com", Arrays.asList(new Category[] { diesel, automatic })));
		vhicles.add(new Vehicle("Volkswagen", "Jetta", "www.vw.com", Arrays.asList(new Category[] { gasoline, automatic })));
		vhicles.add(new Vehicle("Ford", "Focus", "www.ford.com", Arrays.asList(new Category[] { gasoline, manuel })));
		vehicleRepository.save(vhicles);
	}

}
