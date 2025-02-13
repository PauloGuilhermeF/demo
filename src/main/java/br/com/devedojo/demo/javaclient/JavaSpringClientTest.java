package br.com.devedojo.demo.javaclient;

import br.com.devedojo.demo.model.PageableResponse;
import br.com.devedojo.demo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthentication("paulo", "123456789")
                .build();
        RestTemplate restTemplateAdmin = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthentication("paulo", "123456789")
                .build();
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
//        Student[] students = restTemplate.getForObject("http://localhost:8080/v1/protected/students", Student[].class);
//        ResponseEntity<List<Student>> exchange = restTemplate.exchange("http://localhost:8080/v1/protected/students", HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Student>>() {
//                });

//        System.out.println(exchange.getBody());
        System.out.println(student);
        System.out.println(forEntity.getBody());
//        System.out.println(Arrays.toString(students));

        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("http://localhost:8080/v1/protected/students", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        System.out.println(exchange);

        Student studentPost = new Student();
        studentPost.setName("John Wick");
        studentPost.setEmail("john@wick.com");
        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("http://localhost:8080/v1/admin/students",
                HttpMethod.POST, new HttpEntity<>(studentPost, createJsonHeader()), Student.class);
        Student studentPostForObject = restTemplateAdmin.postForObject("http://localhost:8080/v1/admin/students", studentPost, Student.class);
        ResponseEntity<Student> studentResponseEntity = restTemplateAdmin.postForEntity("http://localhost:8080/v1/admin/students", studentPost, Student.class);
        System.out.println(exchangePost);
        System.out.println(studentPostForObject);
        System.out.println(studentResponseEntity);


    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
