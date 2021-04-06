package pl.mwrobel.journalmvccrudapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mwrobel.journalmvccrudapp.dao.JournalEntryDao;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryRepository_Backup {
    /*
    @Autowired
    private JournalEntryDao journalEntryDao;

    public void add(JournalEntry entry){
        this.journalEntryDao.saveEntry(entry);
    }

    public Optional<List<JournalEntry>> getAll(){
        return Optional.ofNullable(this.journalEntryDao.getAll());
    }

    public JournalEntry getEntry(Integer id){
        return this.journalEntryDao.getEntry(id);
    }

    public Optional<List<JournalEntry>> findEntries(String text) {
        return journalEntryDao.findEntries(text);
    }

    public void update(JournalEntry entry) {
        journalEntryDao.update(entry);
    }

    public void delete(Integer entryId) {
        journalEntryDao.delete(entryId);
    }

     */

}
