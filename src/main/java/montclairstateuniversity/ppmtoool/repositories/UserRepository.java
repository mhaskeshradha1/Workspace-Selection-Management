package montclairstateuniversity.ppmtoool.repositories;


import montclairstateuniversity.ppmtoool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
