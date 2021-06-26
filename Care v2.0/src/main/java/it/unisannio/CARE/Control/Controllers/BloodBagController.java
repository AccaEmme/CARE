package it.unisannio.CARE.Control.Controllers;

import it.unisannio.CARE.Control.Beans.BloodBagBean;
import it.unisannio.CARE.Control.Interfaces.BloodBagRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class BloodBagController implements BloodBagRepository {
    private final BloodBagRepository bagRepository;

    public BloodBagController(BloodBagRepository bagRepository){
        this.bagRepository = bagRepository;
    }


    @Override
    @GetMapping("/bloodbags_byserial/{serial}")
    public Iterable<BloodBagBean> findBySerial(@PathVariable String serial) {
        return this.bagRepository.findBySerial(serial);
    }

    @Override
    @GetMapping("/bloodbags_all")
    public List<BloodBagBean> findAll() {
        return bagRepository.findAll();
    }

    @Override
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

    @Override
    public long count() {
        return this.bagRepository.count();
    }



    @Override
    @DeleteMapping("/bloodbags/{serial}")
    public void deleteById(@PathVariable String serial) {
        try {
            bagRepository.delete((BloodBagBean) bagRepository.findBySerial(serial));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @DeleteMapping("bloodbags/{entity}")
    public void delete(@PathVariable BloodBagBean entity) {
        bagRepository.delete(entity);
    }



    @Override
    @DeleteMapping("bloodbags/{entities}")
    public void deleteAll(Iterable<? extends BloodBagBean> entities) {
        bagRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        bagRepository.deleteAll();
    }

    @Override
    public <S extends BloodBagBean> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BloodBagBean> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BloodBagBean> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return (BloodBagBean) bagRepository.findBySerial(s) != null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BloodBagBean> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BloodBagBean> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BloodBagBean getOne(String s) {
        return null;
    }

    @Override
    public <S extends BloodBagBean> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BloodBagBean> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BloodBagBean> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BloodBagBean> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BloodBagBean> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BloodBagBean> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<BloodBagBean> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BloodBagBean> findAll(Pageable pageable) {
        return null;
    }
}
