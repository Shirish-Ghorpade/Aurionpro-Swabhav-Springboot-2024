package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.PageResponse;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.InstructorRepository;
import com.aurionpro.mapping.repository.StudentRepository;

@Service
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	InstructorRepository instructorRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired

	StudentRepository studentRepository;

	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {

		Instructor instructor = InstructorDtoInstructor(instructorDto);
		instructorRepository.save(instructor);
		return (InstructorToInstructorDto(instructor));

	}

	@Override
	public InstructorDto getInstructorById(int id) {
		Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
		Instructor instructor = optionalInstructor.get();
		return (InstructorToInstructorDto(instructor));

	}

	private Instructor InstructorDtoInstructor(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setQualification(instructorDto.getQualification());
		instructor.setEmail(instructorDto.getEmail());
		return instructor;
	}

	private InstructorDto InstructorToInstructorDto(Instructor instructor) {
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setInstructorName(instructor.getInstructorName());
		instructorDto.setQualification(instructor.getQualification());
		instructorDto.setEmail(instructor.getEmail());
		return instructorDto;
	}

	@Override
	public List<CourseDto> getInstructorCourses(int instructorId) {
		Optional<Instructor> optionInstructor = instructorRepository.findById(instructorId);
		if (!optionInstructor.isPresent())
			return null;

		Instructor instructor = optionInstructor.get();

		List<Course> optionalCourse = instructor.getCourses();

		List<CourseDto> courseDto = new ArrayList<>();
		optionalCourse.forEach((course) -> {
			courseDto.add(toCourseDtoMapper(course));
		});

		return courseDto;

	}

	private CourseDto toCourseDtoMapper(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(course.getCourseId());
		courseDto.setFees(course.getFees());
		return courseDto;
	}

	@Override
	public PageResponse<InstructorDto> getAllInstructor(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Instructor> dbInstructorPage = instructorRepository.findAll(pageable);

		PageResponse<InstructorDto> instructorDtoPageResponse = new PageResponse<>();
		instructorDtoPageResponse.setTotalElements(pageSize);
		instructorDtoPageResponse.setSize(dbInstructorPage.getSize());
		instructorDtoPageResponse.setTotalElements(dbInstructorPage.getTotalElements());
//		Here it is not working so we use logic below
//		instructorPageResponse.setContent(instructorPage.getContent());
		instructorDtoPageResponse.setLastPage(dbInstructorPage.isLast());

//		setContent logic
		List<InstructorDto> instructorDtos = new ArrayList<>();

		dbInstructorPage.getContent().forEach(instructor -> {
			instructorDtos.add(InstructorToInstructorDto(instructor));
		});
		instructorDtoPageResponse.setContent(instructorDtos);

		return instructorDtoPageResponse;

	}

	@Override
	public Instructor allocateCourses(int instructorId, List<Course> courses) {
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
		if (!optionalInstructor.isPresent()) {
			return null;
		}

		Instructor dbInstructor = optionalInstructor.get();

		List<Course> dbCourses = dbInstructor.getCourses();

		courses.forEach((course) -> {
			Course temp = courseRepository.findById(course.getCourseId()).get();
			temp.setInstructor(dbInstructor);
			dbCourses.add(temp);
		});

		dbInstructor.setCourses(dbCourses);
		return instructorRepository.save(dbInstructor);
	}

	@Override
	public InstructorDto allocateCourses2(int instructionId, List<Integer> courseIds) {
		Instructor dbInstructor = InstructorDtoInstructor(getInstructorById(instructionId));
		dbInstructor.setInstructorId(instructionId);

		List<Course> dbCourses = dbInstructor.getCourses();

		if (dbCourses == null) {
			dbCourses = new ArrayList<>();
		}

		List<Course> coursesToadd = new ArrayList<>();

		courseIds.forEach(courseId -> {
			Course course = courseRepository.findById(courseId)
					.orElseThrow(() -> new NullPointerException("course does not exist"));

			course.setInstructor(dbInstructor);
			coursesToadd.add(course);
		});
		dbCourses.addAll(coursesToadd);
		dbInstructor.setCourses(dbCourses);
		return InstructorToInstructorDto(instructorRepository.save(dbInstructor));
	}

	@Override
	public List<CourseDto> getCoursesByInstructorId(int instructorId) {
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
		if (!optionalInstructor.isPresent())
			throw new NullPointerException("No Instructor found !!");

		List<Course> listOfCourses = optionalInstructor.map((instructor) -> instructor.getCourses()).orElse(null);

		if (listOfCourses == null)
			throw new NullPointerException("No courses found !!");

		List<CourseDto> listOfCoursesDto = new ArrayList<>();
		listOfCourses.forEach((course) -> {
			listOfCoursesDto.add(toCourseDtoMapper(course));
		});
		return listOfCoursesDto;
	}
	
	public StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollNumber(student.getRollNumber());
		return studentDto;
	}
	

	@Override
	public List<StudentDto> getAllStudentsByInstructorId(int instructorId) {
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);

		if (!optionalInstructor.isPresent())
			throw new NullPointerException("No instructor found !!");
		Instructor instructor = optionalInstructor.get();
		List<Course> courses = instructor.getCourses();
		List<Student>students = studentRepository.findByCoursesIn(courses);
		
		List<StudentDto>studentsDto = new ArrayList<>();
		
		students.forEach(student-> {
			studentsDto.add(toStudentDtoMapper(student));
		});
		
		return studentsDto;
	}
}
