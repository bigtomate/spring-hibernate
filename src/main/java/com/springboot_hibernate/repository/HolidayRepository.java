package com.springboot_hibernate.repository;

import com.springboot_hibernate.entity.Holiday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
    @Query(value = "select date from holiday where division=?1", nativeQuery = true)
    List<Date> retrieveExistingEntries(String division);
}
