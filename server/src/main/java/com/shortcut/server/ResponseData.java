package com.shortcut.server;

public class ResponseData {
  private String move;
  private String time;
  private String[] path;

  public String getMove() {
    return move;
  }

  public String getTime() {
    return time;
  }

  public String[] getPath() {
    return path;
  }

  public ResponseData(String move, String time, String[] path) {
    this.move = move;
    this.time = time;
    this.path = path;
  }
}