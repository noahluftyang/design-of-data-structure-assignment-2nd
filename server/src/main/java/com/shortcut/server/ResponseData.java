package com.shortcut.server;

import java.util.Vector;

public class ResponseData {
  private Vector<String> move;
  private int time;
  private Vector<String> path;

  public Vector<String> getMove() {
    return move;
  }

  public int getTime() {
    return time;
  }

  public Vector<String> getPath() {
    return path;
  }

  public ResponseData(Vector<String> move, int time, Vector<String> path) {
    this.move = move;
    this.time = time;
    this.path = path;
  }
}