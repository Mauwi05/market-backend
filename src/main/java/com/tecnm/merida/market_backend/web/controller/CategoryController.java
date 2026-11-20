package com.tecnm.merida.market_backend.web.controller;

import com.tecnm.merida.market_backend.domain.Category;
import com.tecnm.merida.market_backend.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Manage your categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @Operation(summary = "Get all categories", description = "Return a list of all categories")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Return a category by its ID")
    @ApiResponse(responseCode = "200", description = "Category found")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> getCategory(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(summary = "Create a new category", description = "Register a new category")
    @ApiResponse(responseCode = "201", description = "Category created")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update an existing category by ID")
    @ApiResponse(responseCode = "200", description = "Category updated")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> update(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId,
            @RequestBody Category category) {
        return categoryService.update(categoryId, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by ID")
    @ApiResponse(responseCode = "200", description = "Category deleted")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return categoryService.delete(categoryId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}