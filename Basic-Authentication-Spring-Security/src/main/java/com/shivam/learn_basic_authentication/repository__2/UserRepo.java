package com.shivam.learn_basic_authentication.repository__2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivam.learn_basic_authentication.model__1.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	Optional<Users> findByUsername(String username);
	

}
