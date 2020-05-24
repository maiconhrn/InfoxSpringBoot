package br.com.infox.service;

import br.com.infox.model.entity.ServiceOrder;
import br.com.infox.repository.ServiceOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@Service
@Transactional
public class ServiceOrderService {
    private ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    public ServiceOrder saveServiceOrder(@NotNull ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    public ServiceOrder findById(@NotNull Long id) {
        return serviceOrderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No service order found with ID: " + id));
    }

    public Page<ServiceOrder> findAll(Pageable pageable) {
        return serviceOrderRepository.findAll(pageable);
    }
}
