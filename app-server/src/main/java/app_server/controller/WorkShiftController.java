package app_server.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app_server.entity.User;
import app_server.entity.WorkShift;
import app_server.service.WorkShiftService;

@RestController
@RequestMapping("workShift")
public class WorkShiftController {
	@Autowired 
	private WorkShiftService workShiftService; 
	@GetMapping("/{userId}") 
	public ResponseEntity<List<WorkShift>> listarJornadas(
			@PathVariable Long userId, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) { 
		User user = new User(); 
		user.setId(userId); 
		List<WorkShift> workShift = workShiftService.getWorkShiftForUserAndDate(user, date);
		return ResponseEntity.ok(workShift);
	}
}
