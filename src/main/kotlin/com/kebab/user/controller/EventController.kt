package com.kebab.user.controller

import com.kebab.core.exception.EntityNotFoundException
import com.kebab.core.exception.MalformedRequestDataException
import com.kebab.core.exception.ModelValidationException
import com.kebab.user.model.Event
import com.kebab.user.service.EventService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.http.HttpStatus.SC_BAD_REQUEST
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Class contains all necessary methods to work and modify [Event] records.
 *
 * @author Valentin Trusevich
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/events")
@Api(value = "Event Management", description = "Endpoints for managing Events entities")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"], methods = [GET, POST, PUT, DELETE])
class EventController(private val eventService: EventService) {

    @ApiOperation(value = "Retrieves a list of User records (supports pagination and ordering)",
            response = Event::class, responseContainer = "List")
    @ApiResponses(ApiResponse(code = SC_BAD_REQUEST, message = MalformedRequestDataException.REASON))
    @GetMapping(produces = [APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(ApiImplicitParam("Authorization", name = "Authorization", paramType = "header", required = true, type = "string"))
    fun findAllEvents(@ApiParam("0", allowEmptyValue = true) @RequestParam("page", required = false, defaultValue = "0") page: Int,
                     @ApiParam("0", allowEmptyValue = true) @RequestParam("limit", required = false, defaultValue = "0") limit: Int) =
            eventService.findAllEvents(page, limit)

    @ApiOperation(value = "Gets User record for a given Id", response = Event::class)
    @ApiResponses(ApiResponse(code = SC_NOT_FOUND, message = EntityNotFoundException.REASON))
    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(ApiImplicitParam("Authorization", name = "Authorization", paramType = "header", required = true, type = "string"))
    fun findEventById(@ApiParam("1") @PathVariable("id") id: Long) =
            eventService.findEventById(id)

    @ApiOperation(value = "Updates an existing User by Id", response = Event::class)
    @ApiResponses(
            ApiResponse(code = SC_BAD_REQUEST, message = MalformedRequestDataException.REASON),
            ApiResponse(code = SC_NOT_FOUND, message = EntityNotFoundException.REASON),
            ApiResponse(code = SC_UNPROCESSABLE_ENTITY, message = ModelValidationException.REASON)
    )
    @PutMapping("/{id}", consumes = [APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE],
            produces = [APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(ApiImplicitParam("Authorization", name = "Authorization", paramType = "header", required = true, type = "string"))
    fun updateEventById(@ApiParam("1") @PathVariable("id") id: Long,
                       @RequestBody event: Event) =
            eventService.updateEventById(id, event)

    @ApiOperation(value = "Deletes a User by Id", response = String::class)
    @ApiResponses(ApiResponse(code = SC_NOT_FOUND, message = EntityNotFoundException.REASON))
    @DeleteMapping("/{id}", produces = [APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(ApiImplicitParam("Authorization", name = "Authorization", paramType = "header", required = true, type = "string"))
    fun deleteEventById(@ApiParam("1") @PathVariable("id") id: Long) =
            eventService.deleteEventById(id)
}
