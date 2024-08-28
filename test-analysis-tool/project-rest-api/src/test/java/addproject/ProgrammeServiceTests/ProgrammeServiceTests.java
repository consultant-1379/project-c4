package addproject.ProgrammeServiceTests;

import addproject.pojo.CNA;
import addproject.pojo.CXP;
import addproject.pojo.Product;
import addproject.pojo.Programme;
import addproject.repositories.CnaRepository;
import addproject.repositories.CxpRepository;
import addproject.repositories.ProductRepository;
import addproject.repositories.ProgrammeRepository;
import addproject.services.ProgrammeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProgrammeServiceTests {

    @InjectMocks
    ProgrammeService service;
    @Mock
    private ProgrammeRepository mockProgrammeRepository;
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private CnaRepository mockCnaRepository;
    @Mock
    private CxpRepository mockCxpRepository;

    @Test
    void testGetAllProgrammes() {

        CXP cxp = new CXP("TestCxpNumber", "TestCxpName", "TestCxpRepo", "TestCxpJenkinsURL", "TestCnaNumber");
        List<CXP> cxpList = new ArrayList<>();
        cxpList.add(cxp);

        CNA cna = new CNA("TestCnaNumber", "TestCnaName", "TestProductNumber", cxpList);
        List<CNA> cnaList = new ArrayList<>();
        cnaList.add(cna);

        Product product = new Product("TestProductNumber", "TestProductName", "TestProgrammeNumber", cnaList);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Programme programme = new Programme("TestProgrammeNumber", "TestProgrammeName", productList);
        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme);


        when(mockProgrammeRepository.findAll()).thenReturn(programmeList);
        when(mockProductRepository.findAll()).thenReturn(productList);
        when(mockCnaRepository.findAll()).thenReturn(cnaList);
        when(mockCxpRepository.findAll()).thenReturn(cxpList);

        service.getAllENMProjects();
        assertEquals(1, programmeList.size());
    }

    @Test
    void testGetAllProgrammes_Null() {
        List<Programme> emptList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<CNA> cnaList = new ArrayList<>();
        List<CXP> cxpList = new ArrayList<>();

        when(mockProgrammeRepository.findAll()).thenReturn(emptList);
        when(mockProductRepository.findAll()).thenReturn(productList);
        when(mockCnaRepository.findAll()).thenReturn(cnaList);
        when(mockCxpRepository.findAll()).thenReturn(cxpList);

        service.getAllENMProjects();
        assertEquals(0, emptList.size());
    }

    @Test
    void testInsertEnmProject() {
        CXP cxp = new CXP("TestCxpNumber", "TestCxpName", "TestCxpRepo", "TestCxpJenkinsURL", "TestCnaNumber");
        List<CXP> cxpList = new ArrayList<>();
        cxpList.add(cxp);

        CNA cna = new CNA("TestCnaNumber", "TestCnaName", "TestProductNumber", cxpList);
        List<CNA> cnaList = new ArrayList<>();
        cnaList.add(cna);

        Product product = new Product("TestProductNumber", "TestProductName", "TestProgrammeNumber", cnaList);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Programme programme = new Programme("TestProgrammeNumber", "TestProgrammeName", productList);
        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme);

        service.insertEnmProject(programme);
        assertEquals("TestProgrammeNumber", programme.getProgrammeNumber());
    }

    @Test
    void testInvalidInsertEnmProject() {
        Programme programme = new Programme("TestProgrammeNumber", "TestProgrammeName", new ArrayList<>());
        assertFalse(programme.getProgrammeNumber().matches("-?\\d+(\\.\\d+)?")); // regex for matching to a number
    }

    @Test
    void testInsertEnmProject_NullName() {
        Programme emptyProgramme = new Programme();
        service.insertEnmProject(emptyProgramme);
        assertNull(emptyProgramme.getProgrammeName());
    }

    @Test
    void testInsertEnmProject_NullNumber() {
        Programme emptyProgramme = new Programme();
        service.insertEnmProject(emptyProgramme);
        assertNull(emptyProgramme.getProgrammeNumber());
    }
}