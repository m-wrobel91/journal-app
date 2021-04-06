package pl.mwrobel.journalmvccrudapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryDao extends JpaRepository<JournalEntry, Integer> {
/*
    void saveEntry(JournalEntry entry);

    List<JournalEntry> getAll();

    JournalEntry getEntry(Integer id);

    Optional<List<JournalEntry>> findEntries(String text);

    void update(JournalEntry entry);

    void delete(Integer id);

 */
}
