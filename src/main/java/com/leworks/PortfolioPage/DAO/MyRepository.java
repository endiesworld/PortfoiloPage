package com.leworks.PortfolioPage.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leworks.PortfolioPage.Model.Users;

@Repository
public interface MyRepository extends JpaRepository<Users, Integer> {
	  Users findByUsername(String username) ;
	

}
