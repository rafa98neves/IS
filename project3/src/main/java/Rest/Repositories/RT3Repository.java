package Rest.Repositories;

import Rest.data.RTopic2;
import Rest.data.RTopic3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RT3Repository extends JpaRepository<RTopic3, Integer> {
    public static final String FIND = "SELECT revenues, expenses, profit FROM resultstopic3 WHERE id=1";

    @Query(value = FIND, nativeQuery = true)
    public RTopic3 find();
}
