package com.sinse.electroshop.model.store;

import com.sinse.electroshop.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaStoreDAO implements StoreDAO {

    private final JpaStoreRepository jpaStoreRepository;

    @Override
    public Store save(Store store) {
        return jpaStoreRepository.save(store);
    }

    @Override
    public Store login(Store store) {
        return jpaStoreRepository.findByBusinessIdAndPassword(store.getBusinessId(), store.getPassword());
    }

    @Override
    public Store findById(int store_id) {
        return null;
    }

    @Override
    public List<Store> findAll() {
        return List.of();
    }

    @Override
    public Store update(Store store) {
        return null;
    }

    @Override
    public void delete(int store_id) {

    }
}
