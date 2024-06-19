package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateMessageCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.MessageRepository;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository messageRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final ExternalFarmService externalFarmService;

    public MessageCommandServiceImpl(MessageRepository messageRepository, EmployeeRepository employeeRepository, UserRepository userRepository, ExternalFarmService externalFarmService)
    {
        this.messageRepository = messageRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.externalFarmService = externalFarmService;
    }


    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean isFarmer = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_FARMER"));
        boolean isWorker = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_FARMWORKER"));

        if (isFarmer) {
            var employee = employeeRepository.findById(command.collaboratorId());

            if(employee.get().getFarmId() == externalFarmService.fetchFarmIdByUserId(userDetails.getId())){

                var userEmployeeId= userRepository.findByUsername(employee.get().getUsername());

                var newMessage = new Message(command.description(), userEmployeeId.get().getId(), userDetails.getId(), userDetails.getId() );

                var createNewMessage = messageRepository.save(newMessage);
                createNewMessage.setTransmitterId(userDetails.getId());
                return Optional.of(createNewMessage);
            }

            throw new SecurityException("You can only send messages to your workers");

        } else if (isWorker) {

            var farmer = userRepository.findById(command.farmerId());



            if(externalFarmService.fetchFarmIdByUserId(farmer.get().getId()) == employeeRepository.findFarmIdByUsername(userDetails.getUsername()).get()){

                var newMessage = new Message(command.description(), userDetails.getId(), farmer.get().getId(), userDetails.getId() );

                var createNewMessage = messageRepository.save(newMessage);
                createNewMessage.setTransmitterId(userDetails.getId());
                return Optional.of(createNewMessage);
            }

            throw new SecurityException("You can only send messages to your workers");

        } else {
            throw new SecurityException("Only farmers or workers can create a message");
        }
    }
}
