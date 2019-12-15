package Rest.Repositories;

import Rest.data.RTopic1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RT1Repository extends JpaRepository<RTopic1, Integer> {

    public static final String FIND_REVENUES = "SELECT item_id, revenues FROM resultstopic1 GROUP BY item_id";
    public static final String FIND_EXPENSES = "SELECT item_id, expenses FROM resultstopic1 GROUP BY item_id";
    public static final String FIND_PROFIT = "SELECT item_id, profit FROM resultstopic1 GROUP BY item_id";
    public static final String FIND_AVERAGE = "SELECT item_id, average_purchase FROM resultstopic1 GROUP BY item_id";

    @Query(value = FIND_REVENUES, nativeQuery = true)
    public List<RTopic1> findRevenues();

    @Query(value = FIND_EXPENSES, nativeQuery = true)
    public List<RTopic1> findExpenses();

    @Query(value = FIND_PROFIT, nativeQuery = true)
    public List<RTopic1> findProfit();

    @Query(value = FIND_AVERAGE, nativeQuery = true)
    public List<RTopic1> findAverage();


}
