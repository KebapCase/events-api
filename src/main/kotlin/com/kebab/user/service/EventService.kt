package com.kebab.user.service

import com.kebab.core.exception.EntityNotFoundException
import com.kebab.core.jms.JmsMessageSender
import com.kebab.core.util.mergeWith
import com.kebab.core.util.validate
import com.kebab.user.model.Event
import com.kebab.user.repository.EventRepository
import org.springframework.data.domain.PageRequest
import org.springframework.jms.annotation.JmsListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository,
                   private val sender: JmsMessageSender) {


    fun createEvent(event: Event) = eventRepository.save(event.validate())!!

    fun updateEventById(id: Long, model: Event) =
            eventRepository.save(model.mergeWith(eventRepository.findOne(id)!!).validate())!!

    fun findEventById(id: Long) = eventRepository.findOne(id) ?: throw EntityNotFoundException()

    fun deleteEventById(id: Long) = eventRepository.delete(id)
            .also { sender.sendMessage("deleteUser", id) }

    fun findAllEvents(page: Int,
                      limit: Int) =
            eventRepository.findAll(PageRequest(page, limit)) ?: throw EntityNotFoundException()

    @JmsListener(destination = "deleteUser")
    fun deleteByCreatorId(creatorId: Long) = eventRepository.deleteByCreatorId(creatorId)

}
