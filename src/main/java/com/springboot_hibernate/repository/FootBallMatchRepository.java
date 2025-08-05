package com.springboot_hibernate.repository;

import com.springboot_hibernate.entity.FootBallMatch;
import org.springframework.data.repository.CrudRepository;


public interface FootBallMatchRepository extends CrudRepository<FootBallMatch, Integer> {

}
