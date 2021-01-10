package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
