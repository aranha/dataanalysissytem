package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.Salesman;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configurable
public interface SalesmanRepository extends MongoRepository<Salesman, String> {

}
