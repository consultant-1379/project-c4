package addproject.repositories;

import addproject.pojo.CNA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnaRepository extends CrudRepository<CNA, String> {
}
