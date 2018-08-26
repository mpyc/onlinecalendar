package pl.pycela.onlinecalendar.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pycela.onlinecalendar.Models.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {


    @Query(value = "SELECT * FROM event join permalink on permalink.id = event.permalink_id where permalink.name = :permlink", nativeQuery = true)
    List<Event> findAllByPermlink(@Param("permlink") String permlink);

    @Query(value = "SELECT * FROM Event ", nativeQuery = true)
    List<Event> findAllByMyOwn();

    Event findById(Long id);

    @Query(value = "DELETE from event where event.id = :aLong", nativeQuery = true)
    void deleteById(@Param("aLong") Long id);

    @Modifying
    @Transactional
    void deleteExpiredEvents(int id);
}
