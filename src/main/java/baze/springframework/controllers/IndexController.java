package baze.springframework.controllers;

import baze.springframework.domain.Category;
import baze.springframework.domain.UnitOfMeassure;
import baze.springframework.repositories.CategoryRepository;
import baze.springframework.repositories.UnitOfMeassureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeassureRepository unitOfMeassureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeassureRepository unitOfMeassureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeassureRepository = unitOfMeassureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeassure> unitOfMeassureOptional = unitOfMeassureRepository.findByDescription("Tablespoon");

        System.out.println("Category id: " + categoryOptional.get().getId());
        System.out.println("UOM id: " + unitOfMeassureOptional.get().getId());

        return "index";
    }

}