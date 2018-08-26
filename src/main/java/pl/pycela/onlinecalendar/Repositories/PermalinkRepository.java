package pl.pycela.onlinecalendar.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pycela.onlinecalendar.Models.Permalink;

import java.util.List;


@Repository
public interface PermalinkRepository extends JpaRepository<Permalink, Integer> {

    Permalink findByName(String name);

    @Query(value = "SELECT * FROM permalink where permalink.name = :name", nativeQuery = true)
    List<Permalink> findByNameList(@Param("name") String name);

    @Modifying
    @Transactional
    void deleteExpiredPermalink(int id);

    @Query(value = "select password from permalink where permalink.name = :name", nativeQuery = true)
    String findPasswordByName(@Param("name") String name);
}

