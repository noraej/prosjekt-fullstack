package idatt2105.hamsterGroup.fullstackProject.Controller;


import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.SectionDTO;
import idatt2105.hamsterGroup.fullstackProject.Service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the sections, to connect from frontend
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/sections")
public class SectionController
{
    @Autowired
    private SectionService sectionService;

    @GetMapping("/{section_id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable("section_id") long sectionId) {
        SectionDTO returnSection = sectionService.getSection(sectionId);
        if (returnSection == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnSection, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SectionDTO>> getSections() {
        List<SectionDTO> sections = sectionService.getSections();
        if (sections == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

    @PostMapping("/alternatives")
    public ResponseEntity<List<SectionDTO>> getSectionWithFilter(@RequestBody FilterSortDTO filter){
        return new ResponseEntity<>(sectionService.getSectionsByRoom(filter),HttpStatus.OK);
    }

    /* //Admins should be able to create sections in the app at one point, but down prioritized for now
    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionCreationDTO section) {
    }
     */

    /* //Same here
    @DeleteMapping("/{section_id}")
    @PreAuthorize("...")
    public ResponseEntity<String> deleteSection(@PathVariable("section_id") long sectionId) {
    }
     */
}

