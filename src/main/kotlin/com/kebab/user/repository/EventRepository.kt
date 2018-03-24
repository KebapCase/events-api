package com.kebab.user.repository

import com.kebab.core.repository.BaseEntityRepository
import com.kebab.user.model.Event

/**
 * @author Valentin Trusevich
 */
interface EventRepository : BaseEntityRepository<Event> {

    fun deleteByCreatorId(id: Long)
}
