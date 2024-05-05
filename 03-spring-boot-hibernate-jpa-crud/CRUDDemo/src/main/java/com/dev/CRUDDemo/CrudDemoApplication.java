package com.dev.CRUDDemo;

import com.dev.CRUDDemo.dao.StudentDAO;
import com.dev.CRUDDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{
			menu(studentDAO);
		};
	}

	private void menu(StudentDAO studentDAO){
		Scanner scanner = new Scanner(System.in);
		boolean run=true;
		while(run){
			System.out.println("====================");
			System.out.println("1-Create student");
			System.out.println("2-Create students");
			System.out.println("3-Find student by ID");
			System.out.println("4-Get all students");
			System.out.println("5-Get all student by Last Name");
			System.out.println("6-Update an existing student");
			System.out.println("7-Delete an existing student");
			System.out.println("8-Delete all existing students");
			System.out.print("Key in choice. Press X to exit: ");
			String choice = scanner.nextLine();
			switch(choice){
				case "1"-> createStudent(studentDAO);
				case "2"-> createStudents(studentDAO);
				case "3"-> {
					System.out.print("Enter the id number: ");
					int inputID = scanner.nextInt();
					scanner.nextLine();
					findStudent(studentDAO,inputID);
				}
				case"4"->findStudents(studentDAO);
				case"5"->{
					System.out.print("Please input last name to query: ");
					String lastNameInput = scanner.nextLine();
					findStudentByLastName(studentDAO, lastNameInput);
				}
				case"6"->{
					System.out.print("Please key in student ID to update: ");
					int updateStudentId = Integer.valueOf(scanner.nextLine().strip());
					System.out.print("Please key in new First Name: ");
					String newFirstName = scanner.nextLine();
					updateStudentFirstName(studentDAO, updateStudentId, newFirstName);

				}
				case "7"->{
					System.out.print("Please key in ID to delete student: ");
					int idToDelete = Integer.valueOf(scanner.nextLine().strip());
					deleteStudentById(studentDAO,idToDelete);
				}
				case"8"->{
					int numDeleted= deleteALlStudents(studentDAO);
					System.out.println("Deleted students from DB: "+numDeleted);
				}
				case "X"-> {
					run=false;
				}
				default -> {
					System.out.println("Please key in valid input!");
				}
			}
		}
	}

	private void createStudent(StudentDAO studentDAO){
		Student newStudent = Student.generateRandomStudent();
		studentDAO.save(newStudent);
		System.out.println(String.format("Saved new student %s! %s",newStudent.getId(),newStudent.toString()));

	}

	private void createStudents(StudentDAO studentDAO){
		for(int i=0; i<20; i++){
			Student newStudent = Student.generateRandomStudent();
			studentDAO.save(newStudent);
			System.out.println(String.format("Saved new student %s! %s",newStudent.getId(),newStudent.toString()));
		}

	}
	private void findStudent(StudentDAO studentDAO, int id){
		Student foundStudent = studentDAO.findById(id);
		System.out.println(String.format("Found the student %s! %s",foundStudent.getId(),foundStudent.toString()));
	}

	private void findStudents(StudentDAO studentDao){
		List<Student>studentlist = studentDao.findAll();
		//~Don't need to sort here - can sort in query entity manager
		/*
		studentlist.sort((a,b)->{
			if(a.getLastName().equals(b.getLastName())) return a.getFirstName().compareTo(b.getFirstName());
			return a.getLastName().compareTo(b.getLastName());
		});
		*/
		//studentlist.sort(Comparator.comparing(Student::getLastName));
		System.out.println("1-Create student");
		System.out.println("---> Here are the results of student query <-------");
		for(Student s: studentlist){
			System.out.println(s.toString());
		}
	}

	private void findStudentByLastName(StudentDAO studentDAO, String inputLastName){
		List<Student> studentList = studentDAO.findByLastName(inputLastName);
		System.out.println("Here is the query by lastname ="+inputLastName);
		for (Student s : studentList){
			System.out.println(s.toString());
		}
	}

	private void updateStudentFirstName(StudentDAO studentDAO, int idToUpdate, String newFirstName){
		Student foundStudent = studentDAO.findById(idToUpdate);
		foundStudent.setFirstName(newFirstName);
		String currEmail = foundStudent.getEmail();
		int subStringPos = currEmail.indexOf(".");
		foundStudent.setEmail(newFirstName+currEmail.substring(subStringPos, currEmail.length()));
		studentDAO.update(foundStudent);
		System.out.println("Successfully updated the student: "+ studentDAO.findById(idToUpdate));
	}

	private void deleteStudentById(StudentDAO studentDAO, int idToDelete){
		studentDAO.delete(idToDelete);
		//this does not work! have to find the student within the DAO, not outside
		/*
		studentDAO.delete2(studentDAO.findById(idToDelete));
		*/
		System.out.println("The following student has been deleted: "+idToDelete);
	}

	private int deleteALlStudents(StudentDAO studentDAO){
		return studentDAO.deleteAll();
	}
}
