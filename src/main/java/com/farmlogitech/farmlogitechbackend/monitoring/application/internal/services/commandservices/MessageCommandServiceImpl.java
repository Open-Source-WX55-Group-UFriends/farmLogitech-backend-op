package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.MessageRepository;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageResource command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean isFarmer = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_FARMER"));
        boolean isWorker = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_FARMWORKER"));

        if (isFarmer) {
            var newMessage = new Message(command.description(), command.collaboratorId(), userDetails.getId());
            var createNewMessage = messageRepository.save(newMessage);
            return Optional.of(createNewMessage);
        } else if (isWorker) {
            var newMessage = new Message(command.description(), userDetails.getId(), command.farmerId());
            var createNewMessage = messageRepository.save(newMessage);
            return Optional.of(createNewMessage);
        } else {
            throw new SecurityException("Only farmers or workers can create a message");
        }
    }
}
