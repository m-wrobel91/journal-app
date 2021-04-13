package pl.mwrobel.journalmvccrudapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.mwrobel.journalmvccrudapp.dao.JournalEntryDao;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.NoSuchElementException;

@Component
public class JournalEntryRepository {
    @Autowired
    private JournalEntryDao journalEntryDao;

    public void add(JournalEntry entry){
        journalEntryDao.save(entry);
    }

    public JournalEntry get(Integer id){
        return journalEntryDao.getOne(id);
    }

    public Iterable<JournalEntry> findByTitleOrContentContaining(String phrase, Integer page){

        Pageable pageRequest = PageRequest.of(page, PaginationConstants.NO_OF_ELEMENTS_FOR_COLLECTIVE_VIEW, Sort.by("id").ascending());
        Iterable<JournalEntry> result = journalEntryDao.findByTitleOrContentContaining(phrase, pageRequest);

        return result;
    }
    public long countFoundByTitleOrContentContaining(String phrase){
        return journalEntryDao.countFoundByTitleOrContentContaining(phrase);
    }

    public void update(JournalEntry entry) {
        JournalEntry entryFromDb = journalEntryDao.findById(entry.getId())
                .orElseThrow(()-> new NoSuchElementException("No element with specified ID in DB: " + entry.getId()));
        entryFromDb.setTitle(entry.getTitle());
        entryFromDb.setCategory(entry.getCategory());
        entryFromDb.setContent(entry.getContent());
        journalEntryDao.save(entryFromDb);
    }

    public void delete(Integer entryId) {
        journalEntryDao.deleteById(entryId);
    }

    public long count(){
        return journalEntryDao.count();
    }

    public Iterable<JournalEntry> findByTimestampForHome(Integer page){
        Pageable sortedByTimestampForHomePage = PageRequest.of(page, PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW, Sort.by("timestamp").descending());

        return journalEntryDao.findAll(sortedByTimestampForHomePage);
    }

    public Iterable<JournalEntry> getAll(Integer page, String sortColumn, Boolean ascendingOrder){
        if (ascendingOrder) {
            Pageable sortedById = PageRequest.of(page, PaginationConstants.NO_OF_ELEMENTS_FOR_COLLECTIVE_VIEW, Sort.by(sortColumn).ascending());

            return journalEntryDao.findAll(sortedById);
        }

        Pageable sortedById = PageRequest.of(page, PaginationConstants.NO_OF_ELEMENTS_FOR_COLLECTIVE_VIEW, Sort.by(sortColumn).descending());

        return journalEntryDao.findAll(sortedById);
    }

    public interface PaginationConstants{
        Integer NO_OF_ELEMENTS_FOR_HOME_VIEW = 5;
        Integer NO_OF_ELEMENTS_FOR_COLLECTIVE_VIEW = 10;
    }
}
