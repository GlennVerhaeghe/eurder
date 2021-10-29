package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.domain.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {

    public Customer createDtoToCustomer(CreateCustomerDto dto) {
        return new Customer().setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setAddress(dto.getAddress())
                .setPhoneNumber(dto.getPhoneNumber());
    }

    public CustomerDto customerToDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }

    public List<CustomerDto> customerListToCustomerDtoList(List<Customer> list) {
        return list.stream().map(this::customerToDto).toList();
    }

    public CustomerDto createDtoToDto(CreateCustomerDto dto) {
        return customerToDto(createDtoToCustomer(dto));
    }
}
