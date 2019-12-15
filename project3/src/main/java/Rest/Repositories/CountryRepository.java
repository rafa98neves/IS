package Rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Rest.data.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {


}
