package br.com.devedojo.demo.javaclient;

import br.com.devedojo.demo.model.Student;

public class JavaSpringClientTest {
    public static void main(String[] args) {

        Student studentPost = new Student();
        studentPost.setName("John Wick 2");
        studentPost.setEmail("john@wick.com");
//        studentPost.setId(24L);
        javaClientDAO dao = new javaClientDAO();
//        System.out.println(dao.findById(1));
//        List<Student> students = dao.listAll();
//        System.out.println(students);
//        System.out.println(dao.save(studentPost));
        dao.update(studentPost);
        dao.delete(29);
    }
}
