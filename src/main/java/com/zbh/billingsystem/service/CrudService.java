package com.zbh.billingsystem.service;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author ZinBhoneHtut
 *
 * @param <Entity>
 */
public interface CrudService<E> {
	Optional<E> findById(long id);
	List<E> findAll();
	E save(E entity);
	void update(long id, E entity) throws Exception;
	void deleteById(long id);
	long count();

}
