package com.yugabyte.yftt.hikaridemo.usecase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemoRepo extends JpaRepository<DemoEntity, UUID> {
}
