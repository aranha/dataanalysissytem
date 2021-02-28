package br.com.aranha.dataanalysissystem.repository;

import br.com.aranha.dataanalysissystem.domain.input.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    Sale findFirstByOrderBySalePriceAllItemsDesc();
}
