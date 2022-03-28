package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Payment;
@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer > {

}
