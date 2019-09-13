/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.bootcamp.service.Spo2;

public class Spo2Test {
  Spo2 spo2 = new Spo2();

  @Test
  public void spo2Value96() {
    final boolean actual = spo2.checkRange(96);
    assertEquals(false, actual);
  }

  @Test
  public void spo2Value95() {
    final boolean actual = spo2.checkRange(95);
    assertEquals(false, actual);
  }

  @Test
  public void spo2Value94() {
    final boolean actual = spo2.checkRange(94);
    assertEquals(false, actual);
  }

  @Test
  public void spo2Value91() {
    final boolean actual = spo2.checkRange(91);
    assertEquals(false, actual);
  }

  @Test
  public void spo2Value90() {
    final boolean actual = spo2.checkRange(90);
    assertEquals(true, actual);
  }

  @Test
  public void spo2Value89() {
    final boolean actual = spo2.checkRange(89);
    assertEquals(true, actual);
  }

  @Test
  public void spo2Value71() {
    final boolean actual = spo2.checkRange(71);
    assertEquals(true, actual);
  }

  @Test
  public void spo2Value70() {
    final boolean actual = spo2.checkRange(70);
    assertEquals(true, actual);
  }

  @Test
  public void spo2Value69() {
    final boolean actual = spo2.checkRange(69);
    assertEquals(true, actual);
  }

  @Test
  public void alertMessageValue69() {
    final String actual = spo2.alertMessage(69);
    assertEquals("Critical Spo2 reading: Extreme lack of oxygen, ischemic diseases may occur.", actual);
  }

  @Test
  public void alertMessageValue70() {
    final String actual = spo2.alertMessage(70);
    assertEquals("Critical Spo2 reading: Hypoxemia. Unhealthy and unsafe level.", actual);
  }

  @Test
  public void alertMessageValue71() {
    final String actual = spo2.alertMessage(71);
    assertEquals("Critical Spo2 reading: Hypoxemia. Unhealthy and unsafe level.", actual);
  }

  @Test
  public void alertMessageValue89() {
    final String actual = spo2.alertMessage(89);
    assertEquals("Critical Spo2 reading: Hypoxemia. Unhealthy and unsafe level.", actual);
  }

  @Test
  public void alertMessageValue90() {
    final String actual = spo2.alertMessage(90);
    assertEquals("Critical Spo2 reading: Hypoxemia. Unhealthy and unsafe level.", actual);
  }

  @Test
  public void alertMessageValue91() {
    final String actual = spo2.alertMessage(91);
    assertEquals("", actual);
  }
}
