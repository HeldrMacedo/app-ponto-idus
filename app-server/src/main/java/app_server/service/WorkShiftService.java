package app_server.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app_server.entity.User;
import app_server.entity.WorkShift;
import app_server.repository.WorkShiftRepository;

@Service
public class WorkShiftService {
	
	@Autowired
	private WorkShiftRepository workShiftRespository;
	
	public WorkShift save(WorkShift workShift) {
		return this.workShiftRespository.save(workShift);
	}
	
	public List<WorkShift> getWorkShiftForUserAndDate(User user, LocalDate date){
		return this.workShiftRespository.findByUserAndDate(user, date);
	}
	
}
