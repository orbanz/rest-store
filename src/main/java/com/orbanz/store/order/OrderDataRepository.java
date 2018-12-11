package com.orbanz.store.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderData, Long> {
    public List<OrderData> findByOrderPlacedBetween(Date fromDate, Date toDate);
    public List<OrderData> findByOrderPlacedLessThanEqual(Date toDate);
    public List<OrderData> findByOrderPlacedGreaterThanEqual(Date fromDate);
}
