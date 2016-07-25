package com.ercancan.myboot.h2.repository;

import com.ercancan.myboot.h2.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ercanc
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
