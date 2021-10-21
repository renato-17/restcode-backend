package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Comment;
import upc.edu.pe.restcodebackend.domain.model.Consultant;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.model.Publication;
import upc.edu.pe.restcodebackend.domain.repository.CommentRepository;
import upc.edu.pe.restcodebackend.domain.repository.ConsultantRepository;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.repository.PublicationRepository;
import upc.edu.pe.restcodebackend.domain.service.CommentService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Page<Comment> getAllByPublicationId(Long publicationId, Pageable pageable) {
        return commentRepository.findAllByPublicationId(publicationId,pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
    }

    @Override
    public Comment createComment(Comment comment, Long publicationId, Long ownerId, Long consultantId) {
        Publication publication =  publicationRepository.findById(publicationId)
                .orElseThrow(()->new ResourceNotFoundException("Publication","Id",publicationId));

        comment.setPublication(publication);

        if(ownerId != null){
            Owner owner =  ownerRepository.findById(ownerId)
                    .orElseThrow(()->new ResourceNotFoundException("Owner","Id",ownerId));
            comment.setOwner(owner);
        }
        if(consultantId != null){
            Consultant consultant =  consultantRepository.findById(consultantId)
                    .orElseThrow(()->new ResourceNotFoundException("Consultant","Id",consultantId));
            comment.setConsultant(consultant);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));

        comment.setDescription(commentRequest.getDescription());
        comment.setPublishedDate(commentRequest.getPublishedDate());
        return commentRepository.save(comment);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}
