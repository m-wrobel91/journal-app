package pl.mwrobel.journalmvccrudapp.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryDao extends JpaRepository<JournalEntry, Integer> {
    @Query("SELECT e FROM JournalEntry e WHERE e.title LIKE %:phrase% OR e.content LIKE %:phrase%") //TODO case sensitive to neutralise
    List<JournalEntry> findByTitleOrContentContaining(@Param("phrase")String phrase, Pageable pageable);

//    @Query("SELECT e FROM JournalEntry e WHERE e.title LIKE %:phrase% OR e.content LIKE %:phrase%")
//    List<JournalEntry> findByTitleOrContentContaining(@Param("text")String phrase);

    @Query("SELECT COUNT(e) FROM JournalEntry e WHERE e.title LIKE %:phrase% OR e.content LIKE %:phrase%")
    long countFindByTitleOrContentContaining(@Param("phrase")String phrase);
/*
    void saveEntry(JournalEntry entry);

    List<JournalEntry> getAll();

    JournalEntry getEntry(Integer id);

    Optional<List<JournalEntry>> findEntries(String text);

    void update(JournalEntry entry);

    void delete(Integer id);

 */
}
