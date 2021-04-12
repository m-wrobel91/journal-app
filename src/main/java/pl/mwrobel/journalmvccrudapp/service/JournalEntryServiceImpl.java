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
    private JournalEntryRepository repository;

    public void addEntry(JournalEntryDto entryDto){
        JournalEntry entry = JournalEntryUtility.buildJournalEntry(entryDto);
        repository.add(entry);
    }

    @Override
    public JournalEntryDto getEntry(Integer id) {
        JournalEntry entry = repository.getEntry(id);
        JournalEntryDto entryDto = JournalEntryUtility.buildJournalEntryDto(entry);
        return entryDto;
    }

    public List<JournalEntryDto> getAllEntries(Integer page){

        Iterable<JournalEntry> iterable = this.repository.findById(page);
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());

        return result;

    }
    @Override
    public List<JournalEntryDto> getAllOrderByColumn(Integer page, String sortColumn, Boolean ascendingOrder){

        Iterable<JournalEntry> iterable = this.repository.findAllOrderByColumn(page, sortColumn, ascendingOrder);
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
    public List<JournalEntryDto> findEntries(String phrase, Integer page) {
        Iterable<JournalEntry> iterable = repository.findEntries(phrase, page);
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());
//        ///////////////////////////////////////
//        iterable = repository.findAllOrderByColumn(page, JournalEntryRepository.SortByConstants.BY_TITLE, true);
//        List<JournalEntry> tempResult1 = new ArrayList<>();
//        iterable.forEach(tempResult1::add);
//        List<JournalEntryDto> result1 = tempResult1.stream()
//                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
//                .collect(Collectors.toList());
//        System.out.println("ASC " + result1);
//        /////////////////////////////////////////////
//        iterable = repository.findAllOrderByColumn(page, JournalEntryRepository.SortByConstants.BY_TITLE, false);
//        List<JournalEntry> tempResult2 = new ArrayList<>();
//        iterable.forEach(tempResult2::add);
//        List<JournalEntryDto> result2 = tempResult2.stream()
//                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
//                .collect(Collectors.toList());
//        System.out.println("DESC " + result2);

        return result;
    }

    @Override
    public long countFindEntries(String phrase) {
        return repository.countFindEntries(phrase);
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
    public long count() {
        return repository.count();
    }
}
