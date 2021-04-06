package pl.mwrobel.journalmvccrudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;
import pl.mwrobel.journalmvccrudapp.service.JournalEntryService;
import pl.mwrobel.journalmvccrudapp.utility.JournalEntryUtility;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @RequestMapping(value = RequestMappingConstants.HOME)
    public ModelAndView home(){
        List<JournalEntryDto> entries = journalEntryService.getEntriesForHomePage();
        Integer noOfPages = JournalEntryUtility.getNoOfPages(entries.size());
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRIES, entries);
        mv.addObject(ModelConstants.NO_OF_PAGES, noOfPages);
        mv.setViewName(ViewConstants.HOME_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.ENTRY)
    public ModelAndView entry(@ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto){
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADD);
        mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_ADD_ENTRY);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());
        mv.setViewName(ViewConstants.ENTRY_FORM_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.ADD_ENTRY)
    public ModelAndView addEntry(@Valid @ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());

        if(bindingResult.hasErrors()){
            mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADD);
            mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_ADD_ENTRY);
            mv.setViewName(ViewConstants.ENTRY_FORM_JSP);
            return mv;
        }

        journalEntryService.addEntry(entryDto);

        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADDED);
        mv.setViewName(ViewConstants.ENTRY_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.GET_ENTRY)
    public ModelAndView getEntry(Integer id){
        JournalEntryDto entryDto = journalEntryService.getEntry(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_UPDATE);
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());
        mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_UPDATE_ENTRY);
        mv.setViewName(ViewConstants.ENTRY_FORM_JSP);
        return mv;
    }
    @RequestMapping(value = RequestMappingConstants.UPDATE_ENTRY)
    public ModelAndView updateEntry(@Valid @ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());

        if(bindingResult.hasErrors()){
            mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_UPDATE);
            mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_UPDATE_ENTRY);
            mv.setViewName(ViewConstants.ENTRY_FORM_JSP);
            return mv;
        }
        journalEntryService.update(entryDto);

        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_UPDATED);
        mv.setViewName(ViewConstants.ENTRY_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.DELETE_ENTRY, method = RequestMethod.GET)
    public ModelAndView deleteEntry(@ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto) {

        entryDto = journalEntryService.getEntry(entryDto.getId());
        journalEntryService.delete(entryDto.getId());

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_DELETED);
        mv.setViewName(ViewConstants.ENTRY_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.GET_ALL_ENTRIES)
    public ModelAndView getAllEntries(){

        List<JournalEntryDto> entries = journalEntryService.getAllEntries();

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRIES, entries);
        mv.setViewName(ViewConstants.ENTRIES_JSP);
        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.SHOW_SEARCH)
    public String showSearch(){
        return ViewConstants.SEARCH_FORM_JSP;
    }

    @RequestMapping(value = RequestMappingConstants.SEARCH_ENTRIES)
    public ModelAndView findEntries(@ModelAttribute("text") String text){
        Optional<List<JournalEntryDto>> preResult = journalEntryService.findEntries(text);

        ModelAndView mv = new ModelAndView();
        if(preResult.get().size() == 0 || !preResult.isPresent()){ //todo : condition to be verified with db
            mv.setViewName(ViewConstants.SEARCH_RESULTS_EMPTY_JSP);
            return mv;
        }
        List<JournalEntryDto> result = preResult.get();
        mv.addObject(ModelConstants.ENTRIES, result);
        mv.setViewName(ViewConstants.SEARCH_RESULTS_JSP);
        return mv;
    }

    interface ViewConstants{

        String HOME_JSP = "home";
        String ENTRY_JSP = "entry";
        String ENTRIES_JSP = "entries";
        String ENTRY_FORM_JSP = "entry-form";
        String SEARCH_FORM_JSP = "search-form";
        String SEARCH_RESULTS_JSP = "search-results";
        String SEARCH_RESULTS_EMPTY_JSP = "search-results-empty";

    }
    interface ModelConstants{
        String ENTRIES = "entries";
        String ENTRY_DTO = "entryDto";
        String CATEGORIES = "categories";
        String MESSAGE = "message";
        String FUNCTION_NAME = "functionName";

        String NO_OF_PAGES = "noOfPages";
    }

    interface FunctionNamesConstants{
        String FUN_ADD_ENTRY = "addEntry";
        String FUN_UPDATE_ENTRY = "/updateEntry"; //must be with slash
    }

    interface UiMessagesConstants{
        String MSG_ADD = "Define new the entry below:";
        String MSG_UPDATE = "Update the entry below:";

        String MSG_ADDED = "You added new entry.";
        String MSG_UPDATED = "You updated an entry.";
        String MSG_DELETED = "You deleted an entry.";
    }

    interface RequestMappingConstants{
        String HOME = "/";
        String ENTRY = "/entry";
        String ADD_ENTRY = "/addEntry";
        String GET_ENTRY = "/getEntry{id}";
        String UPDATE_ENTRY = "/updateEntry";
        String DELETE_ENTRY = "/deleteEntry{id}";
        String GET_ALL_ENTRIES = "/getAllEntries";
        String SHOW_SEARCH = "/showSearch";
        String SEARCH_ENTRIES = "/searchEntries";
    }

}
