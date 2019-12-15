package Rest.Repositories;

import Rest.data.RTopic2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RT2Repository extends JpaRepository<RTopic2, Integer> {

    public static final String FIND_REVENUES = "SELECT revenues FROM resultstopic2";
    public static final String FIND_EXPENSES = "SELECT expenses FROM resultstopic2";
    public static final String FIND_PROFIT = "SELECT profit FROM resultstopic2";
    public static final String FIND_AVERAGE = "SELECT average_purchase FROM resultstopic2";

    @Query(value = FIND_REVENUES, nativeQuery = true)
    public List<RTopic2> findRevenues();

    @Query(value = FIND_EXPENSES, nativeQuery = true)
    public List<RTopic2> findExpenses();

    @Query(value = FIND_PROFIT, nativeQuery = true)
    public List<RTopic2> findProfit();

    @Query(value = FIND_AVERAGE, nativeQuery = true)
    public List<RTopic2> findAverage();


}
