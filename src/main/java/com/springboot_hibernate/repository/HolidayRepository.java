package com.springboot_hibernate.repository;

import com.springboot_hibernate.entity.Holiday;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
}
