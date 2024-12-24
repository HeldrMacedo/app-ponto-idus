package app_server.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.auth.Usuario;
import app_server.entity.User;
import app_server.entity.WorkShift;

public interface WorkShiftRepository extends JpaRepository<WorkShift, Long>{
	List<WorkShift> findByUserAndDate(User user, LocalDate date);
}
