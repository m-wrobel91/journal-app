package pl.mwrobel.journalmvccrudapp.service;

import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {

    void add(JournalEntryDto entryDto);

    JournalEntryDto get(Integer id);

    void update(JournalEntryDto entryDto);

    void delete(Integer entryId);

    List<JournalEntryDto> getAll(Integer page, String sortColumn, Boolean ascendingOrder);

    List<JournalEntryDto> getEntriesForHomePage(Integer page);

    List<JournalEntryDto> findByTitleOrContentContaining(String phrase, Integer page);

    long countFoundByTitleOrContentContaining(String phrase);

    long count();
}
