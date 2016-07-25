package com.ercancan.myboot.h2.repository;

import java.util.List;

import com.ercancan.myboot.h2.entity.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ercanc
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
	public List<Category> findByName(String name);
}
