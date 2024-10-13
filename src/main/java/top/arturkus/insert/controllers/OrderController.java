package top.arturkus.insert.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.arturkus.insert.enums.OrderStatus;
import top.arturkus.insert.exceptions.NotFoundException;
import top.arturkus.insert.helpers.OrderHelper;
import top.arturkus.insert.services.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OrderHelper order) {
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @PostMapping("/confirmed/{id}")
    public ResponseEntity<?> confirmed(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(orderService.changeStatus(id, OrderStatus.ACTIVE), HttpStatus.OK);
    }

    @PostMapping("/canceled/{id}")
    public ResponseEntity<?> cancelled(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(orderService.changeStatus(id, OrderStatus.CANCELED), HttpStatus.OK);
    }

    @PostMapping("/delivered/{id}")
    public ResponseEntity<?> delivered(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(orderService.changeStatus(id, OrderStatus.DELIVERED), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody OrderHelper helper) throws NotFoundException {
        return new ResponseEntity<>(orderService.update(id, helper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
}