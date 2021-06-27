package it.unisannio.CARE.Control.Controllers;

import it.unisannio.CARE.Control.Beans.BloodBagBean;
import it.unisannio.CARE.Control.Interfaces.BloodBagRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

@CrossOrigin("*")
@RestController
public class BloodBagController implements ContainerResponseFilter {
    private final BloodBagRepository bagRepository;

    public BloodBagController(BloodBagRepository bagRepository){
        this.bagRepository = bagRepository;
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
    
    
    //@GetMapping("/getBloodbags/{serial}") => cerca in base al seriale
    //@GetMapping("/getBloodbags/") 		=> stampa tutte
    @GetMapping("/bloodbags_byserial/{serial}")
    public Iterable<BloodBagBean> findBySerial(@PathVariable String serial) {
        return this.bagRepository.findBySerial(serial);
    }
	
    
    
    @GetMapping("/bloodbags_all")
    public List<BloodBagBean> findAll() {
        return bagRepository.findAll();
    }
	

    
    @GetMapping("bloodbags_all_byserial/{serial}")
    public List<BloodBagBean> findAllById(Iterable<String> serials) {
        List<BloodBagBean> bags = new ArrayList<>();
        for (String serial : serials){
            Iterable<BloodBagBean> beans = null;
            beans = this.findBySerial(serial);

            //if something's found...
            if (beans != null){
                bags.addAll((Collection<? extends BloodBagBean>) beans);
            }
        }

        //if nothing is found, return null.
        if (bags.size()!=0)
            return bags;
        return null;
    }

    
    public long count() {
        return this.bagRepository.count();
    }



    @DeleteMapping("/bloodbags/{serial}")
    public void deleteById(@PathVariable String serial) {
        try {
            bagRepository.delete((BloodBagBean) bagRepository.findBySerial(serial));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    @DeleteMapping("bloodbags/{entity}")
    public void delete(@PathVariable BloodBagBean entity) {
        bagRepository.delete(entity);
    }



  
    @DeleteMapping("bloodbags/{entities}")
    public void deleteAll(Iterable<? extends BloodBagBean> entities) {
        bagRepository.deleteAll(entities);
    }

  
    public void deleteAll() {
        bagRepository.deleteAll();
    }

  
    public <S extends BloodBagBean> S save(S entity) {
        return null;
    }

  
    public <S extends BloodBagBean> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

  
    public Optional<BloodBagBean> findById(String s) {
        return Optional.empty();
    }

  
    public boolean existsById(String s) {
        return (BloodBagBean) bagRepository.findBySerial(s) != null;
    }

  
    public void flush() {

    }

  
    public <S extends BloodBagBean> S saveAndFlush(S entity) {
        return null;
    }

  
    public void deleteInBatch(Iterable<BloodBagBean> entities) {

    }

  
    public void deleteAllInBatch() {

    }

  
    public BloodBagBean getOne(String s) {
        return null;
    }

  
    public <S extends BloodBagBean> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

   
    public <S extends BloodBagBean> List<S> findAll(Example<S> example) {
        return null;
    }

   
    public <S extends BloodBagBean> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

   
    public <S extends BloodBagBean> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

   
    public <S extends BloodBagBean> long count(Example<S> example) {
        return 0;
    }

    
    public <S extends BloodBagBean> boolean exists(Example<S> example) {
        return false;
    }

    
    public List<BloodBagBean> findAll(Sort sort) {
        return null;
    }

    
    public Page<BloodBagBean> findAll(Pageable pageable) {
        return null;
    }
}
