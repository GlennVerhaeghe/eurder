package be.glenn.eurder.services;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.mappers.CustomerMapper;
import be.glenn.eurder.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerService(CustomerRepo repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Customer createCustomer(CreateCustomerDto dto) {
        if(dto.allInputIsValid()) {
            repo.add(mapper.createDtoToCustomer(dto));
            return mapper.createDtoToCustomer(dto);
        }

        throw new IllegalArgumentException("Not all necessary information to create a new customer was provided");
    }
}
