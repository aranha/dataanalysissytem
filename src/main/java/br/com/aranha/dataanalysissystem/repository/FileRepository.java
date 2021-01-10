package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.File;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FileRepository extends ReactiveMongoRepository<File, String> {
}
