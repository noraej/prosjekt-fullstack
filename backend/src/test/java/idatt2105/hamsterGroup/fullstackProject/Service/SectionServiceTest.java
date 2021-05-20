package idatt2105.hamsterGroup.fullstackProject.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import idatt2105.hamsterGroup.fullstackProject.Model.*;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.SectionDTO;
import idatt2105.hamsterGroup.fullstackProject.Repository.SectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class SectionServiceTest {
    @InjectMocks
    private SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    @BeforeEach
    public void setup() {
        Set<Section> sections = new HashSet<>();

        Room room1 = new Room("Room1", sections);

        Section section1 = new Section("Section1", "Description", 5, 10.5, room1);
        Section section2 = new Section("Section2", "Description", 10, 10.5, room1);

        sections.add(section1);
        sections.add(section2);

        section1.setSectionId(0L);

        Mockito.lenient()
                .when(sectionRepository.findById(section1.getSectionId()))
                .thenReturn(java.util.Optional.of(section1));
        Mockito.lenient()
                .when(sectionRepository.existsById(section1.getSectionId()))
                .thenReturn(true);
        Mockito.lenient()
                .when(sectionRepository.existsById(section2.getSectionId()))
                .thenReturn(false);

        List<Section> sectionsList = new ArrayList<>();
        sectionsList.add(section1);
        sectionsList.add(section2);
        Mockito.lenient()
                .when(sectionRepository.getSections())
                .thenReturn(sectionsList);
    }

    @Test
    public void getSection_IdExists_SectionIsCorrect()
    {
        long sectionId = 0L;
        SectionDTO sectionDTO = sectionService.getSection(sectionId);
        assertThat(sectionDTO.getSectionName()).isEqualTo("Section1");
    }

    @Test
    public void getSection_IdDoesNotExist_ReturnNull() {
        long sectionId = -1L;
        SectionDTO sectionDTO = sectionService.getSection(sectionId);
        assertNull(sectionDTO);
    }

    @Test
    public void getSections_ReturnListAndStatus_ListNotNull() {
        List<SectionDTO> sections = sectionService.getSections();
        assertNotNull(sections);

        assertThat(sections.get(0).getSectionName()).isEqualTo("Section1");
    }
}
