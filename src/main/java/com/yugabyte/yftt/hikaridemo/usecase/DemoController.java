package com.yugabyte.yftt.hikaridemo.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoEntity> findById(@PathVariable UUID id) {
        return ResponseEntity.of(demoService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<DemoEntity> save(@RequestBody DemoEntity entity) {
        entity.setCreatedDate(OffsetDateTime.now());
        entity.setUpdatedDate(OffsetDateTime.now());
        var response = demoService.save(entity);
        return ResponseEntity.of(response);
    }
}
