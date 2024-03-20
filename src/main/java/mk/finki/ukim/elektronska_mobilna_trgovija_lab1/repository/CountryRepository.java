package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
