package app_server.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app_server.entity.TimeClockRecord;
import app_server.entity.User;
import app_server.repository.TimeClockRecordRepository;

@Service
public class TimeClockRecordService {
	
	@Autowired
	private TimeClockRecordRepository timeClockRecordRepository;
	
	public TimeClockRecord save(TimeClockRecord timeClock) {
		return this.timeClockRecordRepository.save(timeClock);
	}
	
	public List<TimeClockRecord> getTimeClockRecordForDate(User user, LocalDateTime start, LocalDateTime end){
		return this.timeClockRecordRepository.findByUserAndDateHoursBetween(user, start, end);
	}
}
