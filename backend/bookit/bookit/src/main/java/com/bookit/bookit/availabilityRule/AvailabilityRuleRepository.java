package com.bookit.bookit.availabilityRule;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRuleRepository extends CrudRepository<AvailabilityRule, Integer> {
    List<AvailabilityRule> findAll();
    List<AvailabilityRule> findByStaffmemberId(Integer staffmemberId);
}
