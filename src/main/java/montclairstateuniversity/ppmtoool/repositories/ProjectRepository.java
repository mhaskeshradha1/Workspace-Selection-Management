package montclairstateuniversity.ppmtoool.repositories;

import montclairstateuniversity.ppmtoool.domain.myproject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<myproject, Long> {
    myproject findByMyprojectidentifier(String ProjectId);
    @Override
    Iterable<myproject> findAll();
}
