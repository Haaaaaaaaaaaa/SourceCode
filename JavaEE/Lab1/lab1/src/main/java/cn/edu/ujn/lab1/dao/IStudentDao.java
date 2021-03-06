package cn.edu.ujn.lab1.dao;

import java.util.List;

import cn.edu.ujn.lab1.model.Student;

public interface IStudentDao {
	// 添加
	public int addStudent(Student stu);

	// 列出所有学生
	public List<Student> findAllStudent();

	// 修改学生信息
	public int updateStudent(Student stu);

	// 删除学生信息
	public int deleteStudent(int uid);
}
