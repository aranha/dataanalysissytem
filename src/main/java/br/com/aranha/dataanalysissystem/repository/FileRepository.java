package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.FileReaded;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileReaded, String> {
}
