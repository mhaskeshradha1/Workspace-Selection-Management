package montclairstateuniversity.ppmtoool.repositories;

import montclairstateuniversity.ppmtoool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Backlog findByMyprojectidentifier(String Identifier);
}
