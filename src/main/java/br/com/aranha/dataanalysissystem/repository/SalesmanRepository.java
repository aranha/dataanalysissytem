package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepository extends MongoRepository<Salesman, String> {

}
