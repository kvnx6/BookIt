package com.bookit.bookit.business;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Integer> {
    List<Business> findAll();
    Optional<Business> findBusinessesByUrlName(String urlName);
    List<Business> findByOwnerId(Integer id);

    boolean existsBusinessByUrlName(String urlName);
}
