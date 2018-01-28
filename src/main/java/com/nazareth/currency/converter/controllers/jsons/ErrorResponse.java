package com.nazareth.currency.converter.controllers.jsons;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(
  value = "ErrorResponse",
  description = "Represents error information that will be returned by the API"
)
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = -1677346290852609123L;

  @ApiModelProperty(
    value = "URL that was called during error",
    dataType = "string",
    required = true
  )
  public final String url;

  @ApiModelProperty(value = "Exception message", dataType = "string", required = true)
  public final String ex;

  public ErrorResponse(String url, Exception ex) {
    this.url = url;
    this.ex = ex.getLocalizedMessage();
  }

  public String getEx() {
    return ex;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("url", url).add("ex", ex).toString();
  }
}
