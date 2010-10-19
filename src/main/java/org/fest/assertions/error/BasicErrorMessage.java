/*
 * Created on Oct 18, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions.error;

import static java.lang.String.format;
import static org.fest.util.Arrays.format;
import static org.fest.util.Objects.*;
import static org.fest.util.Strings.quote;

import java.util.Arrays;

import org.fest.assertions.description.Description;

/**
 * A factory of error messages typically shown when an assertion fails.
 *
 * @author Alex Ruiz
 */
public class BasicErrorMessage {

  private final String format;
  private final Object[] arguments;

  /**
   * Creates a new </code>{@link BasicErrorMessage}</code>.
   * @param format the format string.
   * @param arguments arguments referenced by the format specifiers in the format string.
   */
  public BasicErrorMessage(String format, Object... arguments) {
    this.format = format;
    this.arguments = arguments;
  }

  /**
   * Creates a new error message as a result of a failed assertion.
   * @param d the description of the failed assertion.
   * @return the created error message.
   */
  public String create(Description d) {
    return Formatter.instance().formatMessage(format, d, arguments);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    BasicErrorMessage other = (BasicErrorMessage) obj;
    if (!areEqual(format, other.format)) return false;
    return Arrays.equals(arguments, other.arguments);
  }

  @Override public int hashCode() {
    int result = 1;
    result = HASH_CODE_PRIME * result + hashCodeFor(format);
    result = HASH_CODE_PRIME * result + Arrays.hashCode(arguments);
    return result;
  }

  @Override public String toString() {
    return format("%s[format=%s, arguments=%s]", getClass().getName(), quote(format), format(arguments));
  }
}