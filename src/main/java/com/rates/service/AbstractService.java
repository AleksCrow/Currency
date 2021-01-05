package com.rates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rates.model.AbstractEntity;
import com.rates.repository.CommonRepository;

public abstract class AbstractService<T extends AbstractEntity, R extends CommonRepository<T>> implements CommonService<T> {

	protected final R repository;

    @Autowired
    protected AbstractService(R repository) {
        this.repository = repository;
    }

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(List<T> entity) {
		List<T> ratesListFromDb = repository.findAll();
		if (ratesListFromDb.stream().noneMatch(entity::contains)) {
			entity.forEach(repository::save);
		}
	}
}
