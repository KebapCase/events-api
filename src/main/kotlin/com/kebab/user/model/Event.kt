package com.kebab.user.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
import com.kebab.core.model.BaseEntity
import com.kebab.core.validation.Email
import com.kebab.core.validation.PasswordMatch
import com.kebab.core.validation.UrlValid
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@PasswordMatch
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Object contains all required information for a user")
@Table(name = "user", uniqueConstraints = [(UniqueConstraint(name = "unique_user_guid", columnNames = arrayOf("guid")))])
data class Event(

        @get:NotBlank
        @get:Length(min = 4, max = 32)
        @ApiModelProperty("User's login", example = "m.ivanov", required = true)
        var title: String? = null,

        @get:NotBlank
        @get:Length(min = 4, max = 65)
        @ApiModelProperty("User's password", example = "kekCheburek", required = true)
        var description: String? = null,

        @get:NotBlank
        @get:Length(min = 4, max = 65)
        @ApiModelProperty("Confirm password", example = "10.10.2018 15:00", required = true)
        @JsonFormat(shape = STRING, pattern = "dd.MM.yyyy HH:mm")
        var startTime: Date? = null,

        @get:NotBlank
        @get:Length(min = 2, max = 40)
        @ApiModelProperty("User's first name", example = "2", required = true)
        var duration: Int? = null,

        @get:NotBlank
        @get:Length(min = 2, max = 60)
        @ApiModelProperty("User's last name", example = "1", required = true)
        var creatorId: Long? = null,

        @get:NotBlank
        @get:Length(min = 5, max = 65)
        @get:Email
        @ApiModelProperty("User's login", example = "m.ivanov@example.com", required = true)
        var requirements: String? = null,

        @get:NotBlank
        @get:UrlValid
        @ApiModelProperty("User's avatar link", example = "10", required = true)
        var maxParticipantsCount: Int? = null


) : BaseEntity() {

    companion object {

        private const val serialVersionUID = 92870011100564936L

    }
}
