package pl.mwrobel.journalmvccrudapp.dto;

import org.springframework.stereotype.Component;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

//TODO: - show what was deleted
//TODO: - validation errors on update?
//TODO: - H2 db
//TODO: - prepopulating the db
//TODO: - login

/*
TODO:
- JournalEntryUtility:
             TODO: - add private constructor; DONE
- TODO: 'unbean' JournalEntryDto class; DONE
- getters & setters -> consider Lombok;
- JournalEntryDaoImpl
              - this.entries.removeIf(e-> e.getId().equals(entry.getId()));
              - right away to return statement, no point assigning to variable;
              - Optional won’t check if list is empty but if object is null (line 46),
              Collector.toList() always returns list even if empty;
- css files to be put in separate files;
- add appropriate http method for example @RequestMapping(value="/addEntry", method = RequestMethod.POST) or PostMapping(value="/addEntry");
- TODO: all String literals for example: “message”, viewNames -> final static String in controller class or in inner interface, or public interface; DONE
- TODO: mapping ”home” to ”/”; DONE
- TODO: service into journalEntryService (JournalEntryController.java:22); DONE
- TODO: public in interface is needless; DONE
- isEmpty() JournalEntryService.java:39
- generalnie gdzie mozna używac nawet w zmiennych lokalnych final zeby bylo wiadomo ze referencja do tego obiektu nie powinna byc zmieniona.
- DateConverter
*/


public class JournalEntryDto {

    private Integer id;
    private String timestamp;
    @NotEmpty(message="Title is mandatory.")
    private String title;
    @NotEmpty(message="Choose the category.")
    private String category;
    @NotEmpty(message="Content is mandatory.")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JournalEntryDto{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static class JournalEntryDtoBuilder{
        private Integer id;
        private String timestamp;
        private String title;
        private String category;
        private String content;
        private String locale;

        public JournalEntryDto.JournalEntryDtoBuilder buildId(Integer id){
            this.id = id;
            return this;
        }

        public JournalEntryDto.JournalEntryDtoBuilder buildTimestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public JournalEntryDto.JournalEntryDtoBuilder buildTitle(String title){
            this.title = title;
            return this;
        }

        public JournalEntryDto.JournalEntryDtoBuilder buildCategory(JournalEntry.Category category){
            this.category = category.getDisplayName();
            return this;
        }
        public JournalEntryDto.JournalEntryDtoBuilder buildContent(String content){
            this.content = content.replaceAll("(\r\n|\n\r|\r|\n)", "<br />");//TODO!!!
            return this;
        }
        public JournalEntryDto.JournalEntryDtoBuilder buildLocale(String locale){
            this.locale = locale;
            return this;
        }

        public JournalEntryDto build(){
            JournalEntryDto entryDto = new JournalEntryDto();
            entryDto.id = this.id;
            entryDto.timestamp = this.timestamp;
            entryDto.title = this.title;
            entryDto.category = this.category;
            entryDto.content = this.content;
            //entryDto.locale = this.locale;

            return entryDto;
        }
    }
}
