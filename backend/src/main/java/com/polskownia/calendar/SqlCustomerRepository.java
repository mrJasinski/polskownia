package com.polskownia.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer>
{

}
