package addproject.repositories;

import addproject.pojo.Programme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeRepository extends CrudRepository<Programme, String> {

    @Override
    List<Programme> findAll();
}
