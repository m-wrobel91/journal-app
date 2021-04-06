package pl.mwrobel.journalmvccrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;
import pl.mwrobel.journalmvccrudapp.repository.JournalEntryRepository;
import pl.mwrobel.journalmvccrudapp.utility.JournalEntryUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        JournalEntry entry = repository.getEntry(id); //todo:dlen ?
        JournalEntryDto entryDto = JournalEntryUtility.buildJournalEntryDto(entry);
        return entryDto;
    }

    public List<JournalEntryDto> getAllEntries(){
        List<JournalEntry> list = repository.getAll().orElse(new ArrayList<JournalEntry>());
        List<JournalEntryDto> result = new ArrayList<>();
        if(list.size()>0){
            result = list.stream()
                    .map(JournalEntryUtility::buildJournalEntryDto)
                    .collect(Collectors.toList());
        }
        return result;
    }
    @Override
    public List<JournalEntryDto> getEntriesForHomePage(){
//        List<JournalEntryDto> tempResult = this.getAllEntries();
//        List<JournalEntryDto> result = new ArrayList<>();
//        if(tempResult.size()>0){
//            result = tempResult.stream()
//                    .limit(5)
//                    .collect(Collectors.toList());
//        }
//        return result;
        // = new ArrayList<>();
        //TODO: PAGINATION + SORTING
        Iterable<JournalEntry> iterable = this.repository.findByTimestampForHome();
        List<JournalEntry> tempResult = new ArrayList<>();
        iterable.forEach(tempResult::add);
        List<JournalEntryDto> result = tempResult.stream()
                .map(e -> JournalEntryUtility.buildJournalEntryDto(e))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Optional<List<JournalEntryDto>> findEntries(String text) {
        Optional<List<JournalEntry>> preResult = repository.findEntries(text);
        List<JournalEntryDto> result = new ArrayList<>();
        if(preResult.isPresent()){
            List<JournalEntry> tempResult = preResult.get();
            for(JournalEntry entry : tempResult){
                JournalEntryDto entryDto = JournalEntryUtility.buildJournalEntryDto(entry);
                result.add(entryDto);
            }
            return Optional.of(result);
        }
        return Optional.empty();
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




}
