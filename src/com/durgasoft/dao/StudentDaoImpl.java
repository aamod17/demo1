package com.durgasoft.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.durgasoft.beans.Student;

public class StudentDaoImpl implements StudentDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	String status;
	Student student;
	Map<String, Object> paramMap;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public String add(Student addStudentArg) {
		student = search(addStudentArg.getSid());
		try {
			if (student != null) {
				status = "Student Already Existed";
			} else {
				String addQuery= "insert into student values(:sid, :sname, :saddr)";
				paramMap = new HashMap<String, Object>();
				paramMap.put("sid", addStudentArg.getSid());
				paramMap.put("sname", addStudentArg.getSname());
				paramMap.put("saddr", addStudentArg.getSaddr());
				int rowCount = namedParameterJdbcTemplate.update(addQuery, paramMap);
				
				if (rowCount == 1) {
					status = "Student Inserted Successfully";
				} else {
					status = "Student Insertion Failure";

				}

			}
			
		} catch (Exception e) {
			status = "Student Insertion Failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String searchSidArg) {
		
		try {
		    String searchQuery = "select * from student where sid= :sid";
		    paramMap = new HashMap<String, Object>();
		    paramMap.put("sid", searchSidArg);
		    List<Student> listStudent = namedParameterJdbcTemplate.query(searchQuery, paramMap, (rs, rowNum)->{
		    	Student student1 = new Student();
		    	student1.setSid(rs.getString("sid"));
		    	student1.setSname(rs.getString("sname"));
		    	student1.setSaddr(rs.getString("saddr"));
		    	return student1;
		    });
		    
		    if (listStudent.isEmpty() == true) {
				student = null;
			} else {
				student = listStudent.get(0);

			}
			
			
		} catch (Exception e) {
			student = null;
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public String update(Student updateStudentArg) {
		
		student = search(updateStudentArg.getSid());
		try {
			if (student != null) {
				String updateQuery = "update student set sname= :sname, saddr= :saddr where sid = :sid";
				paramMap = new HashMap<String, Object>();
				paramMap.put("sid", updateStudentArg.getSid());
				paramMap.put("sname", updateStudentArg.getSname());
				paramMap.put("saddr", updateStudentArg.getSaddr());
				int rowCount = namedParameterJdbcTemplate.update(updateQuery, paramMap);
				if (rowCount == 1) {
					status = "Student Updated Successfully";
				} else {
					status = "Student Updation Failure";

				}
				
			} else {
				status = "Student Not Existed";

			}
		} catch (Exception e) {
			status = "Student Not Existed";
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public String delete(String delSidArg) {
		
		return null;
	}

}
