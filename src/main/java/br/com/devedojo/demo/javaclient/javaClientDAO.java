package br.com.devedojo.demo.javaclient;

import br.com.devedojo.demo.model.PageableResponse;
import br.com.devedojo.demo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class javaClientDAO {
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/protected/students")
            .basicAuthentication("paulo", "123456789")
            .build();
    private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/admin/students")
            .basicAuthentication("paulo", "123456789")
            .build();

    public Student findById(long id) {
        return restTemplate.getForObject("/{id}", Student.class, id);
//        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
    }
    public List<Student> listAll(){
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("http://localhost:8080/v1/protected/students", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        return exchange.getBody().getContent();
    }
    public Student save(Student student){
        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("http://localhost:8080/v1/admin/students",
                HttpMethod.POST, new HttpEntity<>(student, createJsonHeader()), Student.class);
        return exchangePost.getBody();
    }

    public void update(Student student){
        restTemplate.put("http://localhost:8080/v1/protected/students", student);

    }

    public void delete(long id){
        restTemplateAdmin.delete("http://localhost:8080/v1/protected/students/{id}", id);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
