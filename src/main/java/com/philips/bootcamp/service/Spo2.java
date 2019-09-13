/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.service;

public class Spo2 implements PatientVitalsService {
  private static final float LOWER_RANGE = 40;
  private static final float UPPER_RANGE = 220;

  @Override
  public boolean checkRange(float value) {
    boolean isCritical = false;
    if (value < 70 || (value >= 70 && value <= 90)) {
      isCritical = true;
    }
    return isCritical;
  }

  @Override
  public String alertMessage(float value) {
    String alertMessage = "";
    if (value >= 70 && value <= 90) {
      alertMessage = "Critical Spo2 reading: Hypoxemia. Unhealthy and unsafe level.";
    } else if (value < 70) {
      alertMessage = "Critical Spo2 reading: Extreme lack of oxygen, ischemic diseases may occur.";
    }
    return alertMessage;
  }
}
