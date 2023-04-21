package com.shopping.shoppingcartmodule.Controller;

import com.shopping.shoppingcartmodule.DTO.CustomerCommentDTO;
import com.shopping.shoppingcartmodule.Entity.CustomerComment;
import com.shopping.shoppingcartmodule.Service.CustomerCommentService;
import com.shopping.shoppingcartmodule.Service.DTO.CustomerCommentConverter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CustomerCommentController {
    private final CustomerCommentService commentService;
    private final CustomerCommentConverter commentConverter;
    @GetMapping("/product/{id}")
    public ResponseEntity<Page<CustomerCommentDTO>> getAllForProducts(@PathVariable Long id,
                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<CustomerComment> commentPage = commentService.getAllCommentsForProduct(id, pageNumber, pageSize);
            Page<CustomerCommentDTO> commentDtoPage = commentPage.map(comment -> commentConverter.toDTO(comment) );
            return ResponseEntity.ok(commentDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Page<CustomerCommentDTO>> getAllForCustomer(@PathVariable Long id,
                                                                   @RequestParam(defaultValue = "0") int pageNumber,
                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<CustomerComment> commentPage = commentService.getAllCommentsForCustomer(id, pageNumber, pageSize);
            Page<CustomerCommentDTO> commentDtoPage = commentPage.map(comment -> commentConverter.toDTO(comment) );
            return ResponseEntity.ok(commentDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<CustomerCommentDTO> addComment(@RequestBody CustomerCommentDTO addCommentDTO) {
        try {
            CustomerCommentDTO commentDTO = commentService.addComment(addCommentDTO);
            return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteAllForProducts(@PathVariable Long id) {
        commentService.deleteAllCommentForProduct(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteAllForCustomer(@PathVariable Long id) {
        commentService.deleteAllCommentForCustomer(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
