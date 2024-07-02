package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Entity.reportDetails;


@Repository

public interface reportDetailsDao extends JpaRepository<reportDetails, Long> {

}
