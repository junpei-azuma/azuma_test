/*
 * Task Creation API
 * タスクを操作するAPI
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.example.test.model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.*;


/**
 * GetTaskResponse
 */
@JsonPropertyOrder({
  GetTaskResponse.JSON_PROPERTY_ID,
  GetTaskResponse.JSON_PROPERTY_TITLE,
  GetTaskResponse.JSON_PROPERTY_COMPLETE_CONDITION,
  GetTaskResponse.JSON_PROPERTY_START_DATE,
  GetTaskResponse.JSON_PROPERTY_DUE_DATE,
  GetTaskResponse.JSON_PROPERTY_STATUS,
  GetTaskResponse.JSON_PROPERTY_IS_POSTPONED
})
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class GetTaskResponse {
  public static final String JSON_PROPERTY_ID = "id";
  @jakarta.annotation.Nonnull
  private String id;

  public static final String JSON_PROPERTY_TITLE = "title";
  @jakarta.annotation.Nonnull
  private String title;

  public static final String JSON_PROPERTY_COMPLETE_CONDITION = "completeCondition";
  @jakarta.annotation.Nullable
  private String completeCondition;

  public static final String JSON_PROPERTY_START_DATE = "startDate";
  @jakarta.annotation.Nonnull
  private LocalDate startDate;

  public static final String JSON_PROPERTY_DUE_DATE = "dueDate";
  @jakarta.annotation.Nonnull
  private LocalDate dueDate;

  public static final String JSON_PROPERTY_STATUS = "status";
  @jakarta.annotation.Nonnull
  private String status;

  public static final String JSON_PROPERTY_IS_POSTPONED = "isPostponed";
  @jakarta.annotation.Nonnull
  private Boolean isPostponed;

  public GetTaskResponse() { 
  }

  public GetTaskResponse id(@jakarta.annotation.Nonnull String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @jakarta.annotation.Nonnull
  @NotNull

  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setId(@jakarta.annotation.Nonnull String id) {
    this.id = id;
  }


  public GetTaskResponse title(@jakarta.annotation.Nonnull String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @jakarta.annotation.Nonnull
  @NotNull

  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getTitle() {
    return title;
  }


  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTitle(@jakarta.annotation.Nonnull String title) {
    this.title = title;
  }


  public GetTaskResponse completeCondition(@jakarta.annotation.Nullable String completeCondition) {
    this.completeCondition = completeCondition;
    return this;
  }

  /**
   * Get completeCondition
   * @return completeCondition
   */
  @jakarta.annotation.Nullable

  @JsonProperty(JSON_PROPERTY_COMPLETE_CONDITION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getCompleteCondition() {
    return completeCondition;
  }


  @JsonProperty(JSON_PROPERTY_COMPLETE_CONDITION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCompleteCondition(@jakarta.annotation.Nullable String completeCondition) {
    this.completeCondition = completeCondition;
  }


  public GetTaskResponse startDate(@jakarta.annotation.Nonnull LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   */
  @jakarta.annotation.Nonnull
  @NotNull
  @Valid

  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public LocalDate getStartDate() {
    return startDate;
  }


  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStartDate(@jakarta.annotation.Nonnull LocalDate startDate) {
    this.startDate = startDate;
  }


  public GetTaskResponse dueDate(@jakarta.annotation.Nonnull LocalDate dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
   */
  @jakarta.annotation.Nonnull
  @NotNull
  @Valid

  @JsonProperty(JSON_PROPERTY_DUE_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public LocalDate getDueDate() {
    return dueDate;
  }


  @JsonProperty(JSON_PROPERTY_DUE_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDueDate(@jakarta.annotation.Nonnull LocalDate dueDate) {
    this.dueDate = dueDate;
  }


  public GetTaskResponse status(@jakarta.annotation.Nonnull String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @jakarta.annotation.Nonnull
  @NotNull

  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getStatus() {
    return status;
  }


  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStatus(@jakarta.annotation.Nonnull String status) {
    this.status = status;
  }


  public GetTaskResponse isPostponed(@jakarta.annotation.Nonnull Boolean isPostponed) {
    this.isPostponed = isPostponed;
    return this;
  }

  /**
   * Get isPostponed
   * @return isPostponed
   */
  @jakarta.annotation.Nonnull
  @NotNull

  @JsonProperty(JSON_PROPERTY_IS_POSTPONED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public Boolean getIsPostponed() {
    return isPostponed;
  }


  @JsonProperty(JSON_PROPERTY_IS_POSTPONED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIsPostponed(@jakarta.annotation.Nonnull Boolean isPostponed) {
    this.isPostponed = isPostponed;
  }


  /**
   * Return true if this GetTaskResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetTaskResponse getTaskResponse = (GetTaskResponse) o;
    return Objects.equals(this.id, getTaskResponse.id) &&
        Objects.equals(this.title, getTaskResponse.title) &&
        Objects.equals(this.completeCondition, getTaskResponse.completeCondition) &&
        Objects.equals(this.startDate, getTaskResponse.startDate) &&
        Objects.equals(this.dueDate, getTaskResponse.dueDate) &&
        Objects.equals(this.status, getTaskResponse.status) &&
        Objects.equals(this.isPostponed, getTaskResponse.isPostponed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, completeCondition, startDate, dueDate, status, isPostponed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetTaskResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    completeCondition: ").append(toIndentedString(completeCondition)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    isPostponed: ").append(toIndentedString(isPostponed)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

