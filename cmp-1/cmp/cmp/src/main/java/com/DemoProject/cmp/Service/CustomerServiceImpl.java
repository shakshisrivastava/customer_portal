package com.DemoProject.cmp.Service;

import com.DemoProject.cmp.DTO.CustomerDto;
import com.DemoProject.cmp.DTO.TodoDto;
import com.DemoProject.cmp.Entity.Customer;
import com.DemoProject.cmp.Exception.TodoException;
import com.DemoProject.cmp.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Value("${base.url}")
    private String url;

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
   // private RestTemplate restTemplate = new RestTemplate();




    @Override
    public List<TodoDto> getAllTodos() {
        ResponseEntity<List<TodoDto>> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TodoDto>>() {
                    });

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException("Wrong Path ", "Please Check the url Before Typing",
                        HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity.getBody();
    }



    @Override
    public TodoDto getTodoById(Long id) {
        String apiUrl = url + "/" + id;

        ResponseEntity<TodoDto> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    TodoDto.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException("Todo not found with ID: " + id,
                        "Please check the ID and try again",
                        HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity.getBody();
    }

    @Override
    public List<TodoDto> getByStatus(Boolean status) {
        String apiUrl = url + "?completed=" + status;

        ResponseEntity<List<TodoDto>> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TodoDto>>() {
                    });
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException("Todos not found with status: " + status,
                        "Please check the status and try again",
                        HttpStatus.NOT_FOUND);
            }
        }

        return responseEntity.getBody();
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(Customer::toCustomerDto)
                .collect(Collectors.toList());
    }


}
