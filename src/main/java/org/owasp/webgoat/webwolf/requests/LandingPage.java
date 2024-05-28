/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2019 Bruce Mayhew
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * Getting Source ==============
 *
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software projects.
 */

package org.owasp.webgoat.webwolf.requests;

import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.owasp.encoder.Encode;
import org.owasp.webgoat.webwolf.requests.EncoderUtil;


@Controller
@Slf4j
@RequestMapping("/landing/**")
public class LandingPage {

  @RequestMapping(
      method = {
        RequestMethod.POST,
        RequestMethod.GET,
        RequestMethod.DELETE,
        RequestMethod.PATCH,
        RequestMethod.PUT
      })
  public Callable<ResponseEntity<?>> ok(HttpServletRequest request) {
    return () -> {      
      /*
       * Initial Repro
       */
      log.trace("Incoming request for: {}", request.getRequestURL().toString());

      /* 
       * Directly using the Encode.forJava method
       */
      log.trace("Incoming request for: {}", Encode.forJava(request.getRequestURL().toString()));
      
      /*
       * Using the encodeForJava method wrapper
       */
      log.trace("Incoming request for: {}", encodeForJava(request.getRequestURL().toString()));

      /*
       * Using EncoderUtil.encodeForJava method wrapper
       */
      log.trace("Incoming request for: {}", EncoderUtil.encodeForJava(request.getRequestURL().toString()));

      return ResponseEntity.ok().build();
    };
  }

  // Create a private method called encodeForJava that takes a string and returns a string.
  // Use the Encode.forJava method to encode the input string and return the result.
  private String encodeForJava(String input) {
    return Encode.forJava(input);
  }
}

