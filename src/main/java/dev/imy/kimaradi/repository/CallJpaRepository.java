package dev.imy.kimaradi.repository;

import dev.imy.kimaradi.models.MissedCall;
import dev.imy.kimaradi.services.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface CallJpaRepository extends JpaRepository<Call, Long> {

    @Query("select e from Call as e where e.callerPhoneNumber = ?1")
    List<String> getMissedCallNumber(String loggedInPhoneNumber);
    //Burada online olup masaj almaya uygun olan kisi numarasi loggedInPhoneNumber
    //yani tabloda recipientPhoneNumber'a denk dusmekte

    @Query(value = "SELECT c FROM Call c WHERE c.recipientPhoneNumber = ?1")
    List<Call> getMissedCallNumberByRecipientPhoneNumber(String loggedInPhoneNumber);

}
