package pl.mwrobel.journalmvccrudapp.service;

import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {

    void addEntry(JournalEntryDto entryDto);

    List<JournalEntryDto> getAllEntries();

    List<JournalEntryDto> getEntriesForHomePage();

    JournalEntryDto getEntry(Integer id);

    Optional<List<JournalEntryDto>> findEntries(String text);

    void update(JournalEntryDto entryDto);

    void delete(Integer entryId);
}
