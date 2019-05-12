package com.Intership.Timesheet.Controllers;

import com.Intership.Timesheet.DTO.EmployeesByMounthDTO;
import com.Intership.Timesheet.Entities.EmployeeEntity;
import com.Intership.Timesheet.Entities.MonthsEntity;
import com.Intership.Timesheet.Entities.WorkCalendarDaysEntity;
import com.Intership.Timesheet.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class IndexController {


	@Autowired
	private MonthsRepositories monthsRepositories;

	@Autowired
	private WorkCalendarDaysRepositories workCalendarDaysRepositories;

	@Autowired
	private EmployeeRepositories employeeRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");

		MonthsEntity month = monthsRepositories.findById(1).get();
		EmployeeEntity employee = employeeRepositories.findById(1).get();


//		List<WorkCalendarDaysEntity> tt
//				=


//		List<WorkCalendarDaysEntity> tt = workCalendarDaysRepositories.findWorkCalendarDaysEntitiesByEmployeeAndMonth(employee, month);


		Iterator<EmployeeEntity> employees = employeeRepositories.findAll().iterator();

		Iterable<EmployeeEntity> iterable = () -> employees;
		Stream<EmployeeEntity> targetStream = StreamSupport.stream(iterable.spliterator(), false);

		List<EmployeesByMounthDTO> res = targetStream.map(x -> convertToDto(x,
				workCalendarDaysRepositories.findWorkCalendarDaysEntitiesByEmployeeAndMonth(x, month)
				)).collect(Collectors.toList());


		model.addObject("employees", res);

		return model;
	}


	private EmployeesByMounthDTO convertToDto(EmployeeEntity employee, List<WorkCalendarDaysEntity> days) {
		EmployeesByMounthDTO postDto = modelMapper.map(employee, EmployeesByMounthDTO.class);
		postDto.setWorkCalendarDaysEntityLint(days);
		return postDto;
	}
}
