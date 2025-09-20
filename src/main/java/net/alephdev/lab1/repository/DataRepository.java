package net.alephdev.lab1.repository;

import net.alephdev.lab1.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByAdmin(boolean admin);
}
