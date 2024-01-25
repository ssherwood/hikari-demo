package com.yugabyte.yftt.hikaridemo.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class DemoService {
    private final DemoRepo demoRepo;

    public DemoService(DemoRepo demoRepo) {
        this.demoRepo = demoRepo;
    }

    // NOTE for a given service it is possible to adjust the transaction isolation level directly
    //@Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Optional<DemoEntity> findById(UUID id) {
        return demoRepo.findById(id);
    }
}
