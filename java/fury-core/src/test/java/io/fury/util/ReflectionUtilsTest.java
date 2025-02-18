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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.google.common.collect.ImmutableList;
import io.fury.test.bean.BeanA;
import io.fury.type.Descriptor;
import java.util.List;
import org.testng.annotations.Test;

public class ReflectionUtilsTest {

  @Test
  public void getReturnType() {
    assertEquals(
        ReflectionUtils.getReturnType(ReflectionUtils.class, "getReturnType"), Class.class);
    assertFalse(ReflectionUtils.hasException(List.class, "getClass"));
  }

  class A {
    String f1;
  }

  @Test
  public void testGetClassNameWithoutPackage() {
    assertEquals(
        ReflectionUtils.getClassNameWithoutPackage(A.class),
        ReflectionUtilsTest.class.getSimpleName() + "$" + A.class.getSimpleName());
  }

  @Test
  public void testObjectEqual() {
    BeanA beanA = BeanA.createBeanA(1);
    assertTrue(ReflectionUtils.objectFieldsEquals(beanA, beanA));
    assertTrue(ReflectionUtils.objectCommonFieldsEquals(beanA, beanA));
  }

  static class GetFieldValuesTestClass {
    Object f1;
    int f2; // test primitive field
  }

  @Test
  public void testGetFieldValues() {
    GetFieldValuesTestClass o = new GetFieldValuesTestClass();
    o.f1 = "str";
    o.f2 = 10;
    List<Object> fieldValues =
        ReflectionUtils.getFieldValues(Descriptor.getFields(GetFieldValuesTestClass.class), o);
    assertEquals(fieldValues, ImmutableList.of("str", 10));
  }
}
