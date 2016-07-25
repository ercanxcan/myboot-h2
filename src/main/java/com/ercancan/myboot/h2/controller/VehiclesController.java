package com.ercancan.myboot.h2.controller;

import com.ercancan.myboot.h2.entity.Category;
import com.ercancan.myboot.h2.entity.Vehicle;
import com.ercancan.myboot.h2.repository.VehicleRepository;
import com.ercancan.myboot.h2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ercanc
 */
@Controller
public class VehiclesController {

	@Autowired
    VehicleRepository vehicleRepository;

	@Autowired
    CategoryRepository categoryRepository;

	@RequestMapping("/addCategory/{id}")
	public String vehicle(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleRepository.findOne(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "addCategory";
	}

    @RequestMapping(value={"/","/list"},method=RequestMethod.GET)
	public String vehiclesList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "list";
	}

    @RequestMapping(value={"/add"},method=RequestMethod.GET)
    public String addVehicle(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "add";
    }

    @RequestMapping(value="/list",method=RequestMethod.POST)
	public String vehiclesAdd(@RequestParam String website, 
						@RequestParam String vehicleModel, @RequestParam String vehicleBrand,@RequestParam Long categoryId, Model model) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setWebsite(website);
        newVehicle.setVehicleModel(vehicleModel);
        newVehicle.setVehicleBrand(vehicleBrand);
        Category category = categoryRepository.findOne(categoryId);
        List<Category> cat = new ArrayList<>();
        cat.add(category);
        newVehicle.setCategories(cat);
        vehicleRepository.save(newVehicle);

        model.addAttribute("vehicle", newVehicle);
        model.addAttribute("categories", categoryRepository.findAll());
        return "redirect:/addCategory/" + newVehicle.getId();
	}

    @RequestMapping("/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleRepository.findOne(id));
        vehicleRepository.delete(id);
        return "list";
    }
    @RequestMapping(value="/addCategory/{id}/categories", method=RequestMethod.POST)
	public String vehiclesAddCategory(@PathVariable Long id, @RequestParam Long categoryId, Model model) {
    	Category category = categoryRepository.findOne(categoryId);
    	Vehicle vehicle = vehicleRepository.findOne(id);

    	if (vehicle != null) {
    		if (!vehicle.hasCategory(category)) {
    			vehicle.getCategories().add(category);
    		}
    		vehicleRepository.save(vehicle);
            model.addAttribute("vehicle", vehicleRepository.findOne(id));
            model.addAttribute("categories", categoryRepository.findAll());
            return "redirect:/addCategory/" + vehicle.getId();
    	}

        model.addAttribute("list", vehicleRepository.findAll());
        return "redirect:/addCategory";
    }

}
