package pl.mwrobel.journalmvccrudapp.utility;

import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;
import pl.mwrobel.journalmvccrudapp.repository.JournalEntryRepository.PaginationConstants;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntryUtility {

    private JournalEntryUtility() {
    }

    public static JournalEntry buildJournalEntry(JournalEntryDto entryDto) {
        return new JournalEntry.JournalEntryBuilder()
                .buildId(entryDto.getId())
                .buildTimestamp(JournalEntryUtility.parseStringToDateTimeElseReturnNow(entryDto.getTimestamp()))
                .buildTitle(entryDto.getTitle())
                .buildCategory(entryDto.getCategory())
                .buildContent(entryDto.getContent())
                .buildLocale("Poland") //todo: where the entry was added from
                .build();
    }

    public static JournalEntryDto buildJournalEntryDto(JournalEntry entry) {
        return new JournalEntryDto.JournalEntryDtoBuilder()
                .buildId(entry.getId())
                .buildTimestamp(formatDateTimeToString(entry.getTimestamp()))
                .buildTitle(entry.getTitle())
                .buildCategory(entry.getCategory())
                .buildContent(entry.getContent())
                .buildLocale("Poland") //todo: analyse1
                .build();
    }

    public static String formatDateTimeToString(LocalDateTime timeStamp){
        return timeStamp.format(Constants.DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseStringToDateTimeElseReturnNow(String timeStamp) {
        if(timeStamp.equals("")){
            return LocalDateTime.now();
        }
        return LocalDateTime.parse(timeStamp, Constants.DATE_TIME_FORMATTER);
    }
    public static Integer getNoOfPages(Integer noOfEntries){
        if(noOfEntries % PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW != 0){
            return noOfEntries / PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW + 1;
        }
        return noOfEntries / PaginationConstants.NO_OF_ELEMENTS_FOR_HOME_VIEW;
    }

    interface Constants{
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    }
}
