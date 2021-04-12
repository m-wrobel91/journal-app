package pl.mwrobel.journalmvccrudapp.service;

import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {

    void addEntry(JournalEntryDto entryDto);

    List<JournalEntryDto> getAllEntries(Integer page);

    List<JournalEntryDto> getAllOrderByColumn(Integer page, String sortColumn, Boolean ascendingOrder);

    List<JournalEntryDto> getEntriesForHomePage(Integer page);

    JournalEntryDto getEntry(Integer id);

    List<JournalEntryDto> findEntries(String phrase, Integer page);

    long countFindEntries(String phrase);

    void update(JournalEntryDto entryDto);

    void delete(Integer entryId);

    long count();
}
