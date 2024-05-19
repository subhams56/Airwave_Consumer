package com.airwave.consumer.repository;

import com.airwave.consumer.model.GeofenceRecords;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GeofenceRepository extends JpaRepository<GeofenceRecords, String> {


}
