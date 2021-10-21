package upc.edu.pe.restcodebackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Comment;
import upc.edu.pe.restcodebackend.domain.service.CommentService;
import upc.edu.pe.restcodebackend.resource.CommentResource;
import upc.edu.pe.restcodebackend.resource.save.SaveCommentResource;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Get All Comments", description = "Get all comments", tags = {"comments"})
    @GetMapping("/comments")
    public Page<CommentResource> getAllComments(Pageable pageable){
        Page<Comment> commentPage = commentService.getAllComments(pageable);

        List<CommentResource> resources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Comment By Id", description = "Get Comment By Id", tags = {"comments"})
    @GetMapping("/comments/{commentId}")
    public CommentResource getCommentById(@PathVariable Long commentId){
        return convertToResource(commentService.getCommentById(commentId));
    }

    @Operation(summary = "Create Comment", description = "Create a new comment", tags = {"comments"})
    @PostMapping("publication/{publicationId}/comments")
    public CommentResource createComment(@Valid @RequestBody SaveCommentResource resource,@PathVariable Long publicationId,
                                         @RequestParam(value = "owner", required = false) Long ownerId,
                                         @RequestParam(value = "consultant", required = false)  Long consultantId){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.createComment(comment,publicationId,ownerId,consultantId));
    }

    @Operation(summary = "Update Comment", description = "Update a comment", tags = {"comments"})
    @PutMapping("/comments/{commentId}")
    public CommentResource updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid SaveCommentResource resource){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.updateComment(commentId, comment));
    }

    @Operation(summary = "Delete a comment", description = "Delete a comment", tags = {"comments"})
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }

    private  Comment convertToEntity(SaveCommentResource resource){return mapper.map(resource,Comment.class);}
    private  CommentResource convertToResource(Comment entity){return mapper.map(entity,CommentResource.class);}
}
