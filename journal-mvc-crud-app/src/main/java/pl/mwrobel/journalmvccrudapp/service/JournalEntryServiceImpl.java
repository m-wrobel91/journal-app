package pl.mwrobel.journalmvccrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;
import pl.mwrobel.journalmvccrudapp.repository.JournalEntryRepository;
import pl.mwrobel.journalmvccrudapp.utility.JournalEntryUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    @Autowired
    private final JournalEntryRepository repository;

    public JournalEntryServiceImpl(JournalEntryRepository repository) {
        this.repository = repository;
    }

    public void add(JournalEntryDto entryDto){
        JournalEntry entry = JournalEntryUtility.buildJournalEntry(entryDto);
        repository.add(entry);
    }

    @Override
    public JournalEntryDto get(Integer id) {
        JournalEntry entry = repository.get(id);
        JournalEntryDto entryDto = JournalEntryUtility.buildJournalEntryDto(entry);

        return entryDto;
    }

    @Override
    public void update(JournalEntryDto entryDto) {
        JournalEntry entry = JournalEntryUtility.buildJournalEntry(entryDto);

        repository.update(entry);
    }

    @Override
    public void delete(Integer entryId) {
        repository.delete(entryId);
    }

    @Override
    public List<JournalEntryDto> getAll(Integer page, String sortColumn, Boolean ascendingOrder){

        Iterable<JournalEntry> iterable = this.repository.getAll(page, sortColumn, ascendingOrder);
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());

        return result;

    }
    @Override
    public List<JournalEntryDto> getEntriesForHomePage(Integer page){
        Iterable<JournalEntry> iterable = this.repository.findByTimestampForHome(page);
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<JournalEntryDto> findByTitleOrContentContaining(String phrase, Integer page) {
        Iterable<JournalEntry> iterable = repository.findByTitleOrContentContaining(phrase, page);
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public long countFoundByTitleOrContentContaining(String phrase) {
        return repository.countFoundByTitleOrContentContaining(phrase);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
