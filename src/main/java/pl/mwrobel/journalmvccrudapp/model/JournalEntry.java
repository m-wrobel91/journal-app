package pl.mwrobel.journalmvccrudapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class JournalEntry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timestamp;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String content;
    private String locale; //not exposed

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = Category.getCategory(category.getDisplayName());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public JournalEntry(Integer id, LocalDateTime timestamp, String title, Category category, String content, String locale) {
        this.id = id;
        this.timestamp = timestamp;
        this.title = title;
        this.category = category;
        this.content = content;
        this.locale = locale;
    }

    public JournalEntry() {
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static class JournalEntryBuilder{
        private Integer id;
        private LocalDateTime timestamp;
        private String title;
        private Category category;
        private String content;
        private String locale;

        public JournalEntryBuilder buildId(Integer id){
            this.id = id;
            return this;
        }

        public JournalEntryBuilder buildTimestamp(LocalDateTime timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public JournalEntryBuilder buildTitle(String title){
            this.title = title;
            return this;
        }

        public JournalEntryBuilder buildCategory(String category){
            this.category = Category.getCategory(category);
            return this;
        }
        public JournalEntryBuilder buildContent(String content){
            this.content = content;
            return this;
        }
        public JournalEntryBuilder buildLocale(String locale){
            this.locale = locale;
            return this;
        }

        public JournalEntry build(){
            JournalEntry entry = new JournalEntry();
            entry.id = this.id;
            entry.timestamp = this.timestamp;
            entry.title = this.title;
            entry.category = this.category;
            entry.content = this.content;
            entry.locale = this.locale;

            return entry;
        }
    }
    public enum Category{
        GENERAL("General"),
        PERSONAL("Personal"),
        RECIPE("Recipe");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName(){
            return displayName;
        }

        public static Category getCategory(String category){
            for(Category c : Category.values()){
                if(c.getDisplayName().equals(category)){
                    return c;
                }
            }
            throw new IllegalArgumentException();
            //return Category.GENERAL;
        }

    }
}
