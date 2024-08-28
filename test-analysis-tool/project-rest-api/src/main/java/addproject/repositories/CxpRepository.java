package addproject.repositories;

import addproject.pojo.CXP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CxpRepository extends CrudRepository<CXP, String> {
}
