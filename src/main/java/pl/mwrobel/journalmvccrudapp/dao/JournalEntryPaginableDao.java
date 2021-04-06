package pl.mwrobel.journalmvccrudapp.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

@Repository
public interface JournalEntryPaginableDao extends PagingAndSortingRepository<JournalEntry, Integer> {
    //Iterable<JournalEntry> findByOrderByTitle(Pageable pageable);
}
