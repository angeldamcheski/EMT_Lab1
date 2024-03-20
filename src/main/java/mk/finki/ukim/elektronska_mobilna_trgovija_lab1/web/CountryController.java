package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.web;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Country;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto.CountryDTO;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.BookRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.CountryRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.AuthorService;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CountryController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public CountryController(BookService bookService, AuthorService authorService, BookRepository bookRepository, CountryRepository countryRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }
    @GetMapping()
    public List<Country> getCountries(){
        List<Country> countries =  this.countryRepository.findAll();
        return countries;
    }
    @GetMapping("/{countryId}")
    public Country getCountryById(@PathVariable Long countryId){
        Country country = this.countryRepository.findById(countryId).orElse(null);
        return country;
    }
    @PostMapping("/create")
    public Country saveCountry(@RequestBody CountryDTO countryDTO){
        return this.countryRepository.save(new Country(countryDTO.getName(), countryDTO.getContinent()));
    }

    @DeleteMapping("/{countryId}/delete")
    public Country deleteCountry(@PathVariable Long countryId){
        Country country = this.countryRepository.findById(countryId).orElse(null);
        this.countryRepository.delete(country);
        return country;
    }

}
