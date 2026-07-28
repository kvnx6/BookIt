package com.bookit.bookit.staffmember;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffmemberRepository extends CrudRepository<Staffmember, Integer> {
    List<Staffmember> findAll();
    List<Staffmember> findStaffmemberByBusinessId(Integer businessId);
}
