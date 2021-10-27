package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer createDtoToCustomer(CreateCustomerDto dto) {
        return new Customer().setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setAddress(dto.getAddress())
                .setPhoneNumber(dto.getPhoneNumber());
    }
}
