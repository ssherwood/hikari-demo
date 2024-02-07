package com.yugabyte.yftt.hikaridemo.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface DemoRepo extends JpaRepository<DemoEntity, UUID> {
}
