package app_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app_server.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
