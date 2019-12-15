package Rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Rest.data.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
