package app_server.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app_server.entity.TimeClockRecord;
import app_server.entity.User;
import app_server.service.TimeClockRecordService;

@RestController
@RequestMapping("/time")
public class TimeClockRecordController {
	
	@Autowired
	private TimeClockRecordService timeClockRecordService;
	
	@PostMapping
	public ResponseEntity<TimeClockRecord> register(@RequestBody TimeClockRecord time) {
		TimeClockRecord timeClockRecord = this.timeClockRecordService.save(time);
		return ResponseEntity.status(HttpStatus.CREATED).body(timeClockRecord);
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<TimeClockRecord>> listTimeClockRecord(
			@PathVariable Long userId, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
		
		User user = new User();
		user.setId(userId);
		List<TimeClockRecord> timeClockRecordList = this.timeClockRecordService.getTimeClockRecordForDate(user, start, end);
		return ResponseEntity.ok(timeClockRecordList);
	}

}
