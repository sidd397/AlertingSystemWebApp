/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.service;

public class PulseRate implements PatientVitalsService {
  private static final float LOWER_RANGE = 70;
  private static final float UPPER_RANGE = 95;

  @Override
  public boolean checkRange(float value) {
    boolean isCritical = false;
    if (value < 40 || value > 100) {
      isCritical = true;
    }
    return isCritical;
  }

  @Override
  public String alertMessage(float value) {
    String alertMessage = "";
    if (value < 40) {
      alertMessage  = "Critical PulseRate value: Below healthy resting heart rate.";
    } else if (value > 100 && value <= 220) {
      alertMessage = "Critical PulseRate value: Acceptable if measured during exercise. Not acceptable if resting heartrate.";
    } else if (value > 220) {
      alertMessage = "Critical PulseRate level: Abnormally high heart rate.";
    }
    return alertMessage;
  }
}