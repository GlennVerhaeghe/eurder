package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.domain.dtos.CustomerDto;
import org.springframework.stereotype.Service;

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
        return new CustomerDto(customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }

    public CustomerDto createDtoToDto(CreateCustomerDto dto) {
        return customerToDto(createDtoToCustomer(dto));
    }
}
