package Rest.Repositories;

import Rest.data.RTopic3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RT3Repository extends JpaRepository<RTopic3, Integer> {


}
