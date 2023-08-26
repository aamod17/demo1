//NamedParameterJdbcTemplate example using Map directly

package com.durgasoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.durgasoft.beans.Student;
import com.durgasoft.dao.StudentDao;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext ;
		StudentDao studentDao;
		Student student;
		String status;
		try {
			
			applicationContext = new ClassPathXmlApplicationContext("com/durgasoft/resources/applicationContext.xml");
			studentDao = (StudentDao) applicationContext.getBean("studentDaoBean");
			
			/*//add data to student table
			student = new Student();
			student.setSid("S222");
			student.setSname("BBB");
			student.setSaddr("Banglore");
			status = studentDao.add(student);
			System.out.println("Status: "+status);
			*/
			//search data from student table
			/*student = studentDao.search("S222");
			if (student == null) {
				System.out.println("Status: Student Not Existed");
			} else {
				System.out.println("Student Details");
				System.out.println("==========================");
				System.out.println("Student Id: "+student.getSid());
				System.out.println("Student Name: "+student.getSname());
				System.out.println("Student Address: "+student.getSaddr());

			}
			*/
			//update student data in table
			student = new Student();
			student.setSid("S111");
			student.setSname("ZZZ");
			student.setSaddr("Chennai");
			status = studentDao.update(student);
			System.out.println("Status: "+status);
			
			//delete student data in table
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
