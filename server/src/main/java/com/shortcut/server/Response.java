package com.shortcut.server;

public class Response {
  private ResponseData data;
  private String message;

  public ResponseData getData() {
    return data;
  }

  public void setData(ResponseData data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Response() {
  }
}
