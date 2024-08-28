package addproject.services;

import addproject.pojo.CNA;
import addproject.pojo.CXP;
import addproject.pojo.Product;
import addproject.pojo.Programme;
import addproject.repositories.CnaRepository;
import addproject.repositories.CxpRepository;
import addproject.repositories.ProductRepository;
import addproject.repositories.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CnaRepository cnaRepository;

    @Autowired
    private CxpRepository cxpRepository;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public void insertEnmProject(Programme newProgramme) {
        try {
            programmeRepository.save(newProgramme);
            for (Product product : newProgramme.getProductList()) {
                product.setProgrammeNumber(newProgramme.getProgrammeNumber());

                productRepository.save(product);

                for (CNA cna : product.getCnaList()) {
                    cna.setProductNumber(product.getProductNumber());

                    cnaRepository.save(cna);

                    for (CXP cxp : cna.getCxpList()) {
                        cxp.setCnaNumber(cna.getCnaNumber());

                        cxpRepository.save(cxp);
                    }
                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public List<Programme> getAllENMProjects() {
        List<CXP> cxps = (List<CXP>) cxpRepository.findAll();
        List<CNA> cnas = (List<CNA>) cnaRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        List<Programme> programmes = programmeRepository.findAll();

        addCxpsToCnas(cnas, cxps);
        addCnasToProducts(products, cnas);
        addProductsToProgrammes(programmes, products);

        return programmes;
    }

    private List<CNA> addCxpsToCnas(List<CNA> cnas, List<CXP> cxps) {
        for(CNA cna: cnas) {
            List<CXP> cnaListOfCxps = new ArrayList<>();
            for (CXP cxp : cxps) {
                if (cxp.getCnaNumber().equals(cna.getCnaNumber())) {
                    cnaListOfCxps.add(cxp);
                }
            }
            cna.setCxpList(cnaListOfCxps);
        }
        return cnas;
    }

    private List<Product> addCnasToProducts(List<Product> products, List<CNA> cnas) {
        for (Product product : products) {
            List<CNA> productListOfCnas = new ArrayList<>();
            for (CNA cna : cnas)
                if (product.getProductNumber().equals(cna.getProductNumber())) {
                    productListOfCnas.add(cna);
                }

            product.setCnaList(productListOfCnas);
        }
        return products;
    }

    private List<Programme> addProductsToProgrammes(List<Programme> programmes, List<Product> products) {
        for (Programme programme : programmes) {
            List<Product> programmesListOfProducts = new ArrayList<>();
            for (Product product : products)
                if (product.getProgrammeNumber().equals(programme.getProgrammeNumber())) {
                    programmesListOfProducts.add(product);
                }

            programme.setProductList(programmesListOfProducts);
        }
        return programmes;
    }
}