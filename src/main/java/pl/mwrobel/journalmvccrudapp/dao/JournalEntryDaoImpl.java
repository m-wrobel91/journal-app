package pl.mwrobel.journalmvccrudapp.dao;

import org.springframework.stereotype.Component;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//@Component
public class JournalEntryDaoImpl /* implements JournalEntryDao */ {
/*
    private List<JournalEntry> entries;

    @Override
    public void saveEntry(JournalEntry entry) {
        this.entries.add(entry);
        System.out.println("==> JournalEntryDaoImpl method add(): " + entry);
    }

    @Override
    public List<JournalEntry> getAll() {
        return entries;
    }

    @Override
    public JournalEntry getEntry(Integer id) {

        JournalEntry entry = this.entries.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElse(new JournalEntry());
        return entry;
    }

    @Override
    public Optional<List<JournalEntry>> findEntries(String text) {

        List<JournalEntry> list = this.entries.stream()
                .filter(entry -> entry.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        entry.getContent().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println(list);
        Optional<List<JournalEntry>> result = Optional.ofNullable(list);

        System.out.println("==> DAO method findEntries: " + result.isPresent());

        return result;
    }

    @PostConstruct
    private void prepopulateDb() {
        this.entries = new ArrayList<>();
        this.entries.add(new JournalEntry(0, LocalDateTime.now(), "title1", JournalEntry.Category.GENERAL, "content1", ""));
        this.entries.add(new JournalEntry(1, LocalDateTime.now(), "title2", JournalEntry.Category.GENERAL, "content2", ""));
        this.entries.add(new JournalEntry(2, LocalDateTime.now(), "title3", JournalEntry.Category.GENERAL, "content3", ""));
        this.entries.add(new JournalEntry(3, LocalDateTime.now(), "title4", JournalEntry.Category.GENERAL, "content4", ""));
        this.entries.add(new JournalEntry(4, LocalDateTime.now(), "title5", JournalEntry.Category.GENERAL, "content5", ""));
        this.entries.add(new JournalEntry(5, LocalDateTime.now(), "title6", JournalEntry.Category.GENERAL, "content6", ""));

    }

    @Override
    public String toString() {
        return "JournalEntryDaoImpl{" +
                "entries=" + entries +
                '}';
    }

    @Override
    public void update(JournalEntry entry) {

        JournalEntry entryToBeUpdated = getEntry(entry.getId());
        Collections.replaceAll(this.entries, entryToBeUpdated, entry);
    }

    @Override
    public void delete(Integer id) {

        Iterator<JournalEntry> itr = this.entries.iterator();
        while(itr.hasNext()){
            JournalEntry e = itr.next();
            if(e.getId().equals(id)){
                this.entries.remove(e);
                break;
            }
        }
    }

 */
}
