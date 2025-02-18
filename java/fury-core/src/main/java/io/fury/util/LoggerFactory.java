/*
 * Copyright 2023 The Fury Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fury.util;

import org.slf4j.Logger;
import org.slf4j.helpers.NOPLogger;

/**
 * A logger factory which can be used to disable fury logging more easily than configure logging.
 *
 * @author chaokunyang
 */
public class LoggerFactory {
  private static boolean disableLogging;

  public static void disableLogging() {
    disableLogging = true;
  }

  public static void enableLogging() {
    disableLogging = false;
  }

  public static Logger getLogger(Class<?> clazz) {
    if (disableLogging) {
      return NOPLogger.NOP_LOGGER;
    } else {
      return org.slf4j.LoggerFactory.getLogger(clazz);
    }
  }
}
