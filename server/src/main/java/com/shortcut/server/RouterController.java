package com.shortcut.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

  @GetMapping(value = "/")
  String hello() {
    return "Hello world!";
  }

  // @GetMapping(value = "/path")
}