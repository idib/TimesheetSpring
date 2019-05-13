package com.Intership.Timesheet.Controllers;

import com.Intership.Timesheet.DTO.EmployeesByMounthDTO;
import com.Intership.Timesheet.DTO.MonthsDto;
import com.Intership.Timesheet.DTO.ResultAssesmentDTO;
import com.Intership.Timesheet.Entities.*;
import com.Intership.Timesheet.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class IndexController {
	@Autowired
	private YearRepositories yearRepositories;

	@Autowired
	private MonthsRepositories monthsRepositories;

	@Autowired
	private WorkCalendarDaysRepositories workCalendarDaysRepositories;

	@Autowired
	private EmployeeRepositories employeeRepositories;

	@Autowired
	private DepartmentRepositories departmentRepositories;

	@Autowired
	private AssessmentRepositories assessmentRepositories;

	@Autowired
	private ModelMapper modelMapper;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");

		Iterable<DepartmentEntity> departmentList = departmentRepositories.findAll();
		Iterable<YearEntity> yearList = yearRepositories.findAll();
		model.addObject("departmentList", departmentList);
		model.addObject("yearList", yearList);

		return model;
	}

	@RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
	public ModelAndView getYearForDepartment(@PathVariable int departmentId) {
		ModelAndView model = new ModelAndView("index");

		model.addObject("currentDepId", departmentId);
		Iterable<DepartmentEntity> departmentList = departmentRepositories.findAll();
		model.addObject("departmentList", departmentList);
		Iterable<YearEntity> yearList = yearRepositories.findAll();
		model.addObject("yearList", yearList);

		return model;
	}


	@RequestMapping(value = "/department/{departmentId}/year/{yearId}", method = RequestMethod.GET)
	public ModelAndView getDepartmentByYear(@PathVariable int departmentId, @PathVariable int yearId) {
		ModelAndView model = new ModelAndView("index");

		Iterable<DepartmentEntity> departmentList = departmentRepositories.findAll();
		Iterable<YearEntity> yearList = yearRepositories.findAll();
		Iterable<AssessmentEntity> assessmentList = assessmentRepositories.findAll();
		model.addObject("departmentList", departmentList);
		model.addObject("currentDepId", departmentId);
		model.addObject("yearList", yearList);
		model.addObject("assessmentList", assessmentList);


		Optional<YearEntity> yearOpt = yearRepositories.findById(yearId);
		Optional<DepartmentEntity> departmentOpt = departmentRepositories.findById(departmentId);


		if (!yearOpt.isPresent() || !departmentOpt.isPresent()) return model;

		YearEntity year = yearOpt.get();
		DepartmentEntity department = departmentOpt.get();

		Iterable<MonthsEntity> months = monthsRepositories.findMonthsEntitiesByYear(year);


		List<EmployeeEntity> employees = employeeRepositories.findEmployeeEntitiesByDepartment(department);

		List<MonthsDto> res = new ArrayList<>();

		boolean first = true;

		for (MonthsEntity month : months) {
			List<EmployeesByMounthDTO> employeesDtos = employees.stream()
					.map(x -> getEmployeesByMounthDTO(x, month))
					.collect(Collectors.toList());
			MonthsDto dto = GetMounthDTO(month, employeesDtos);
			dto.setActive(first);
			res.add(dto);
			first = false;
		}

		model.addObject("Months", res);

		return model;
	}

	private MonthsDto GetMounthDTO(MonthsEntity month, List<EmployeesByMounthDTO> employeesByMounthDTOS) {
		MonthsDto dto = modelMapper.map(month, MonthsDto.class);


		List<Integer> listDays = new ArrayList<>();

		int max = month.getCountDay();
		for (int i = 1; i <= max; i++) {
			listDays.add(i);
		}

		dto.setListDays(listDays);

		dto.setEmployeesByMounthDTOS(employeesByMounthDTOS);

		return dto;
	}

	private EmployeesByMounthDTO getEmployeesByMounthDTO(EmployeeEntity employee, MonthsEntity month) {
		EmployeesByMounthDTO dto = modelMapper.map(employee, EmployeesByMounthDTO.class);

		List<WorkCalendarDaysEntity> days =
				workCalendarDaysRepositories.findWorkCalendarDaysEntitiesByEmployeeAndMonth(employee, month);


		List<String> list = days.stream().map(x -> x.getAssessment().getValue()).collect(Collectors.toList());

		Map<String, Integer> resultAssessmentForEmployee = new HashMap<>();

		for (String s : list) {
			resultAssessmentForEmployee.put(s, resultAssessmentForEmployee.getOrDefault(s, 0) + 1);
		}

		List<ResultAssesmentDTO> resultAssesmentDTOSList = new ArrayList<>();

		resultAssessmentForEmployee.forEach((k, v) -> {
			resultAssesmentDTOSList.add(new ResultAssesmentDTO(k, v));
		});

		dto.setResult(resultAssesmentDTOSList);

		int max = month.getCountDay();
		for (int i = list.size(); i < max; i++) {
			list.add("");
		}

		dto.setWorkCalendarDaysEntityLint(list);
		return dto;
	}


}
