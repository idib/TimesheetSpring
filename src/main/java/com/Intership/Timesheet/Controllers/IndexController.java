package com.Intership.Timesheet.Controllers;

import com.Intership.Timesheet.DTO.*;
import com.Intership.Timesheet.Entities.*;
import com.Intership.Timesheet.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView setSelectAssasment(@PathVariable int departmentId) {
		ModelAndView model = new ModelAndView("index");

		model.addObject("currentDepId", departmentId);
		Iterable<DepartmentEntity> departmentList = departmentRepositories.findAll();
		model.addObject("departmentList", departmentList);
		Iterable<YearEntity> yearList = yearRepositories.findAll();
		model.addObject("yearList", yearList);

		return model;
	}

	@RequestMapping(value = "/setselected/{monthId}/{employeeId}/{day}/{value}", method = RequestMethod.GET)
	public ModelAndView setSelectAssasment(@PathVariable int monthId,
	                                       @PathVariable int employeeId,
	                                       @PathVariable int day,
	                                       @PathVariable int value) {

		ModelAndView model = new ModelAndView("index");

		Optional<EmployeeEntity> employeeOpt = employeeRepositories.findById(employeeId);
		Optional<MonthsEntity> monthOpt = monthsRepositories.findById(monthId);
		Optional<AssessmentEntity> assessmentOpt = assessmentRepositories.findById(value);

		if (!employeeOpt.isPresent() && !monthOpt.isPresent() && !assessmentOpt.isPresent()) return model;

		MonthsEntity month = monthOpt.get();
		EmployeeEntity employee = employeeOpt.get();
		AssessmentEntity assessment = assessmentOpt.get();

		if (day <= 0 || day > month.getCountDay()) return model;

		Optional<WorkCalendarDaysEntity> workCalendarDayOpt =
				workCalendarDaysRepositories.findWorkCalendarDaysEntityByEmployeeAndMonthAndDay(employee, month, day);

		if (workCalendarDayOpt.isPresent()) {
			WorkCalendarDaysEntity workCalendarDay = workCalendarDayOpt.get();
			workCalendarDay.setAssessment(assessment);
			workCalendarDaysRepositories.save(workCalendarDay);
		} else {
			WorkCalendarDaysEntity workCalendarDay = new WorkCalendarDaysEntity();
			workCalendarDay.setId((int) (workCalendarDaysRepositories.count() + 1));
			workCalendarDay.setDay(day);
			workCalendarDay.setEmployee(employee);
			workCalendarDay.setMonth(month);
			workCalendarDay.setAssessment(assessment);
			workCalendarDaysRepositories.save(workCalendarDay);
		}


		return model;
	}


	@RequestMapping(value = "/department/{departmentId}/year/{yearId}", method = RequestMethod.GET)
	public ModelAndView getTimeSheet(@PathVariable int departmentId, @PathVariable int yearId) {
		ModelAndView model = new ModelAndView("index");

		Iterable<DepartmentEntity> departmentList = departmentRepositories.findAll();
		Iterable<YearEntity> yearList = yearRepositories.findAll();
		model.addObject("departmentList", departmentList);
		model.addObject("currentDepId", departmentId);
		model.addObject("yearList", yearList);


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
				workCalendarDaysRepositories.findWorkCalendarDaysEntitiesByEmployeeAndMonthOrderByDay(employee, month);


		List<AssassmentCellDTO> assassmentCellDTOList = new ArrayList<>();
		Iterable<AssessmentEntity> assassmentEntityList = assessmentRepositories.findAll();


		for (WorkCalendarDaysEntity day : days) {
			AssassmentCellDTO assassmentCellDTO = new AssassmentCellDTO();
			String selected = day.getAssessment().getValue();

			String path = "/setselected/" + month.getId() + "/" + employee.getId() + "/" + day.getDay() + "/";

			List<AssassmentDTO> l = cloneAndConvertDTO(assassmentEntityList, selected);

			assassmentCellDTO.setAssassmentList(l);
			assassmentCellDTO.setSelectedValue(selected);
			assassmentCellDTO.setPath(path);
			assassmentCellDTO.setDay(day.getDay());
			assassmentCellDTOList.add(assassmentCellDTO);
		}

		Map<String, Integer> resultAssessmentForEmployee = new HashMap<>();


		Iterable<AssessmentEntity> assessmentList = assessmentRepositories.findAll();

		for (AssassmentCellDTO assassmentCellDTO : assassmentCellDTOList) {
			String value = assassmentCellDTO.getSelectedValue();
			resultAssessmentForEmployee.put(value, resultAssessmentForEmployee.getOrDefault(value, 0) + 1);
		}

		int max = month.getCountDay();
		for (int i = 1; i <= max; i++) {
			int finalI = i;
			if (assassmentCellDTOList.stream().noneMatch(x -> x.getDay() == finalI)) {
				AssassmentCellDTO assassmentCellDTO = new AssassmentCellDTO();

				String path = "/setselected/" + month.getId() + "/" + employee.getId() + "/" + i + "/";

				List<AssassmentDTO> l = cloneAndConvertDTO(assassmentEntityList, "_");
				assassmentCellDTO.setAssassmentList(l);
				assassmentCellDTO.setSelectedValue("_");
				assassmentCellDTO.setPath(path);
				assassmentCellDTO.setDay(i);
				assassmentCellDTOList.add(assassmentCellDTO);
			}
		}

		assassmentCellDTOList.sort(Comparator.comparingInt(AssassmentCellDTO::getDay));

		List<ResultAssesmentDTO> resultAssesmentDTOSList = new ArrayList<>();

		resultAssessmentForEmployee.forEach((k, v) -> {
			resultAssesmentDTOSList.add(new ResultAssesmentDTO(k, v));
		});

		dto.setResult(resultAssesmentDTOSList);


		dto.setWorkCalendarDaysEntityLint(assassmentCellDTOList);
		return dto;
	}

	private List<AssassmentDTO> cloneAndConvertDTO(Iterable<AssessmentEntity> assassmentEntityList, String selected) {
		List<AssassmentDTO> assassmentDTOList = new ArrayList<>();
		for (AssessmentEntity assessmentEntity : assassmentEntityList) {
			assassmentDTOList.add(convertToAssassmentDTO(assessmentEntity, selected));
		}
		return assassmentDTOList;
	}

	private AssassmentDTO convertToAssassmentDTO(AssessmentEntity assessmentEntity, String selected) {
		AssassmentDTO dto = modelMapper.map(assessmentEntity, AssassmentDTO.class);
		dto.setIsSelected(dto.getValue().equals(selected));
		return dto;
	}
}
