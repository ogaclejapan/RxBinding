/**
 * Copyright (C) 2015 ogaclejapan <ogaclejapan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ogaclejapan.rx.binding.tuple;

public abstract class Tuple {

  public static Tuple0 create() {
    return new Tuple0();
  }

  public static <T1> Tuple1<T1> create(T1 t1) {
    return new Tuple1<T1>(t1);
  }

  public static <T1, T2> Tuple2<T1, T2> create(T1 t1, T2 t2) {
    return new Tuple2<T1, T2>(t1, t2);
  }

  public static <T1, T2, T3> Tuple3<T1, T2, T3> create(T1 t1, T2 t2, T3 t3) {
    return new Tuple3<T1, T2, T3>(t1, t2, t3);
  }

  public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> create(T1 t1, T2 t2, T3 t3, T4 t4) {
    return new Tuple4<T1, T2, T3, T4>(t1, t2, t3, t4);
  }

  public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> create(T1 t1, T2 t2, T3 t3, T4 t4,
      T5 t5) {
    return new Tuple5<T1, T2, T3, T4, T5>(t1, t2, t3, t4, t5);
  }

  public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> create(T1 t1, T2 t2,
      T3 t3, T4 t4, T5 t5, T6 t6) {
    return new Tuple6<T1, T2, T3, T4, T5, T6>(t1, t2, t3, t4, t5, t6);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> create(T1 t1,
      T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    return new Tuple7<T1, T2, T3, T4, T5, T6, T7>(t1, t2, t3, t4, t5, t6, t7);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> create(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
    return new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>(t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> create(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
    return new Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  @SuppressWarnings("unchecked")
  public static <R extends Tuple> R of(Tuple tuple) {
    return (R) tuple;
  }

}
