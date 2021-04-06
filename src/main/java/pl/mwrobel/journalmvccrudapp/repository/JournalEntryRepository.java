package pl.mwrobel.journalmvccrudapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.mwrobel.journalmvccrudapp.dao.JournalEntryDao;
import pl.mwrobel.journalmvccrudapp.dao.JournalEntryPaginableDao;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JournalEntryRepository {
    @Autowired
    private JournalEntryDao journalEntryDao;
    //@Autowired
    //private JournalEntryPaginableDao journalEntryPaginableDao;

    public void add(JournalEntry entry){
        journalEntryDao.save(entry);
    }

    public Optional<List<JournalEntry>> getAll(){
        return Optional.ofNullable(journalEntryDao.findAll());
    }

    public JournalEntry getEntry(Integer id){
        return journalEntryDao.getOne(id);
    }

    public Optional<List<JournalEntry>> findEntries(String text){
        List<JournalEntry> entries = journalEntryDao.findAll();

        List<JournalEntry> list = entries.stream()
                .filter(entry -> entry.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        entry.getContent().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());

        Optional<List<JournalEntry>> result = Optional.ofNullable(list);

        return result;
    }

    public void update(JournalEntry entry) {
        JournalEntry entryFromDb = journalEntryDao.findById(entry.getId()).orElse(new JournalEntry()); //TODO: what if empty?
        entryFromDb.setTitle(entry.getTitle());
        entryFromDb.setCategory(entry.getCategory());
        entryFromDb.setContent(entry.getContent());
        journalEntryDao.save(entryFromDb);
    }

    public void delete(Integer entryId) {
        journalEntryDao.deleteById(entryId);
    }

    //PAGINATION
    public Iterable<JournalEntry> findByTimestampForHome(){
        Pageable sortedByTimestampForHomePage = PageRequest.of(0, PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW, Sort.by("timestamp").descending());
        Pageable homePage = PageRequest.of(0, PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW);

        return journalEntryDao.findAll(sortedByTimestampForHomePage);
    }


    public interface PaginationConstants{
        Integer NO_OF_ELEMENTS_FOR_HOME_VIEW = 5;
        Integer NO_OF_ELEMENTS_FOR_COLLECTIVE_VIEW = 10;

    }

}
