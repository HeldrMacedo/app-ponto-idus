package app_server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app_server.entity.TimeClockRecord;
import app_server.entity.User;

public interface TimeClockRecordRepository extends JpaRepository<TimeClockRecord, Long>{
	List<TimeClockRecord> findByUserAndDateHoursBetween(User user, LocalDateTime start, LocalDateTime end);
}
