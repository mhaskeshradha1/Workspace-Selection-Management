package montclairstateuniversity.ppmtoool.repositories;

import montclairstateuniversity.ppmtoool.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

    public  List<Task>findByMyprojectidentifierOrderByPriority(String id);
}
