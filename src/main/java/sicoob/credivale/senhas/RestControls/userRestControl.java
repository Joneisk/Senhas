package sicoob.credivale.senhas.RestControls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="apis/citizen/")
public class    userRestControl {
    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }
    /*



    /////////////////////////////////////// ORDEM PARA INSTANCIAR //////////////////////////////
    //            CONTROL -> ENTITY -> SERVICES -> DAOS ( REPOSITORIES )                      //
    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////
    //  FAZER O SINGLETON NO JAVASCRIPT E NO BACKEND FAZER O SINGLETON PARA ACESSAR O BANCO  //
    ///////////////////////////////////////////////////////////////////////////////////////////

    //Gender
    @Autowired
    private br.sicoob.credivale.senhas.Services.genderService genderService;

    @GetMapping("/get-all-genders")
    public ResponseEntity<Object> getAllGenders() {
        return new ResponseEntity<>(genderService.getAll(),HttpStatus.OK);
    }

    //CategoryProduct
    @Autowired
    private categoryProductService categoryProductService;

    @GetMapping("/get-category-product")
    public ResponseEntity<Object> getCategoryProduct (@RequestParam(value="cat_id") Long cat_id) {
        return new ResponseEntity<>(categoryProductService.getById(cat_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-categories-product")
    public ResponseEntity<Object> getAllCategoriesProduct() {
        return new ResponseEntity<>(categoryProductService.getAll(),HttpStatus.OK);
    }
    //---



    //City
    @Autowired
    private cityService cityService;

    @GetMapping("/get-city")
    public ResponseEntity<Object> getCity (@RequestParam(value="cid_id") Long cid_id) {
        return new ResponseEntity<>(cityService.getById(cid_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-cities")
    public ResponseEntity<Object> getAllCities() {
        return new ResponseEntity<>(cityService.getAll(),HttpStatus.OK);
    }
    //---

    //FisicalPerson
    @Autowired
    private br.sicoob.credivale.senhas.Services.fisicalPersonService fisicalPersonService;

    @GetMapping("/get-fisical-person")
    public ResponseEntity<Object> getFisicalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        return new ResponseEntity<>(fisicalPersonService.getById(pessoa_pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-fisical-persons")
    public ResponseEntity<Object> getAllFisicalPersons() {
        return new ResponseEntity<>(fisicalPersonService.getAll(),HttpStatus.OK);
    }
    //---

    //LegalPerson
    @Autowired
    private br.sicoob.credivale.senhas.Services.legalPersonService legalPersonService;

    @GetMapping("/get-legal-person")
    public ResponseEntity<Object> getLegalPerson (@RequestParam(value="pessoa_pes_id") Long pessoa_pes_id) {
        return new ResponseEntity<>(legalPersonService.getById(pessoa_pes_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-legal-persons")
    public ResponseEntity<Object> getAllLegalPersons() {
        return new ResponseEntity<>(legalPersonService.getAll(),HttpStatus.OK);
    }
    //---


    //Product
    @Autowired
    private br.sicoob.credivale.senhas.Services.productService productService;
    @GetMapping("/get-product")
    public ResponseEntity<Object> getProduct(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(productService.getById(pro_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getALlProducts() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }

    //State
    @Autowired
    private stateService stateService;

    @GetMapping("/get-state")
    public ResponseEntity<Object> getState (@RequestParam(value="est_id") Long est_id) {
        return new ResponseEntity<>(stateService.getById(est_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-states")
    public ResponseEntity<Object> getAllStates() {
        return new ResponseEntity<>(stateService.getAll(),HttpStatus.OK);
    }
    //---

    //Storage
    @Autowired
    private br.sicoob.credivale.senhas.Services.storageService storageService;

    @GetMapping("/get-storage")
    public ResponseEntity<Object> getStorage(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(storageService.getById(pro_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-storages")
    public ResponseEntity<Object> getAllStorages() {
        return new ResponseEntity<>(storageService.getAll(),HttpStatus.OK);
    }
    //---

    //User
    @Autowired
    private userService userService;

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam(value="pro_id") Long pro_id) {
        return new ResponseEntity<>(userService.getById(pro_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }
    //---
    */
}