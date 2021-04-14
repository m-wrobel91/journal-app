package pl.mwrobel.journalmvccrudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mwrobel.journalmvccrudapp.dto.JournalEntryDto;
import pl.mwrobel.journalmvccrudapp.model.JournalEntry;
import pl.mwrobel.journalmvccrudapp.service.JournalEntryService;
import pl.mwrobel.journalmvccrudapp.utility.JournalEntryUtility;

import javax.validation.Valid;
import java.util.List;

@Controller
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    private String sortColumnChosen = "id";
    private Boolean isAscendingOrderChosen = true;

    private void setSortColumnChosen(String sortColumnChosen) {
        this.sortColumnChosen = sortColumnChosen;
    }

    private void setAscendingOrderChosen(Boolean ascendingOrderChosen) {
        this.isAscendingOrderChosen = ascendingOrderChosen;
    }

    @RequestMapping(value = RequestMappingConstants.ADD_ENTRY, method =RequestMethod.POST)
    public ModelAndView add(@Valid @ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());

        if(bindingResult.hasErrors()){
            mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADD);
            mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_ADD_ENTRY);
            mv.setViewName(ViewConstants.ENTRY_FORM_JSP);

            return mv;
        }

        journalEntryService.add(entryDto);

        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADDED);
        mv.setViewName(ViewConstants.ENTRY_JSP);

        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.GET_ENTRY, method =RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id){
        JournalEntryDto entryDto = journalEntryService.get(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_UPDATE);
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());
        mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_UPDATE_ENTRY);
        mv.setViewName(ViewConstants.ENTRY_FORM_JSP);

        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.UPDATE_ENTRY, method =RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto, BindingResult bindingResult){

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
    public ModelAndView delete(@ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto) {

        entryDto = journalEntryService.get(entryDto.getId());
        journalEntryService.delete(entryDto.getId());

        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRY_DTO, entryDto);
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_DELETED);
        mv.setViewName(ViewConstants.ENTRY_JSP);

        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.GET_ALL_ENTRIES, method = RequestMethod.GET)
    public ModelAndView getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "true") Boolean ascendingOrder){
        this.setSortColumnChosen(sortColumn);
        this.setAscendingOrderChosen(ascendingOrder);

        List<JournalEntryDto> entries = journalEntryService.getAll(page, this.sortColumnChosen, this.isAscendingOrderChosen);//TODO zmienic nazwe

        long noOfPages = JournalEntryUtility.getNoOfPages(journalEntryService.count());
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.NO_OF_PAGES, noOfPages);
        mv.addObject(ModelConstants.ENTRIES, entries);
        mv.addObject("page", page);
        mv.addObject("sortColumn", sortColumnChosen);
        mv.addObject("ascendingOrder", isAscendingOrderChosen);
        mv.setViewName(ViewConstants.ENTRIES_JSP);

        return mv;

    }

    @RequestMapping(value = RequestMappingConstants.HOME, method =RequestMethod.GET)
    public ModelAndView home(@RequestParam(defaultValue = "0") Integer page){
        List<JournalEntryDto> entries = journalEntryService.getEntriesForHomePage(page);
        long noOfPages = JournalEntryUtility.getNoOfPagesForHome(journalEntryService.count());
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.ENTRIES, entries);
        mv.addObject(ModelConstants.NO_OF_PAGES, noOfPages);
        mv.addObject("page", page);
        mv.setViewName(ViewConstants.HOME_JSP);

        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.ENTRY, method =RequestMethod.GET)
    public ModelAndView showEntry(@ModelAttribute(ModelConstants.ENTRY_DTO) JournalEntryDto entryDto){
        ModelAndView mv = new ModelAndView();
        mv.addObject(ModelConstants.MESSAGE, UiMessagesConstants.MSG_ADD);
        mv.addObject(ModelConstants.FUNCTION_NAME, FunctionNamesConstants.FUN_ADD_ENTRY);
        mv.addObject(ModelConstants.CATEGORIES, JournalEntry.Category.values());
        mv.setViewName(ViewConstants.ENTRY_FORM_JSP);

        return mv;
    }

    @RequestMapping(value = RequestMappingConstants.SHOW_SEARCH, method = RequestMethod.GET)
    public String showSearch(){
        return ViewConstants.SEARCH_FORM_JSP;
    }

    @RequestMapping(value = RequestMappingConstants.SEARCH_ENTRIES, method = RequestMethod.GET)
    public ModelAndView findByTitleOrContentContaining(@ModelAttribute(ModelConstants.PHRASE) String phrase, @RequestParam(defaultValue = "0") Integer page){
        long noOfPages = JournalEntryUtility.getNoOfPages(journalEntryService.countFoundByTitleOrContentContaining(phrase));
        List<JournalEntryDto> result = journalEntryService.findByTitleOrContentContaining(phrase, page);

        ModelAndView mv = new ModelAndView();
        if(result.size() == 0){
            mv.setViewName(ViewConstants.SEARCH_RESULTS_EMPTY_JSP);
            return mv;
        }
        mv.addObject(ModelConstants.PHRASE, phrase);
        mv.addObject(ModelConstants.NO_OF_PAGES, noOfPages);
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
        String PHRASE = "phrase";

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
        String GET_ENTRY = "/getEntry/{id}";
        String UPDATE_ENTRY = "/updateEntry";
        String DELETE_ENTRY = "/deleteEntry{id}";
        String GET_ALL_ENTRIES = "/getAllEntries";
        String SHOW_SEARCH = "/showSearch";
        String SEARCH_ENTRIES = "/searchEntries";
    }

}
