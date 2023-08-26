package com.durgasoft.dao;

import com.durgasoft.beans.Student;

public interface StudentDao {
	
	public String add(Student addStudentArg);
	public Student search(String searchIdArg);
	public String update(Student updateStudentArg);
	public String delete(String delSidArg);

}
