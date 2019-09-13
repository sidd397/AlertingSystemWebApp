/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.service;

public class Temperature implements PatientVitalsService {
  private static final float LOWER_RANGE = 97;
  private static final float UPPER_RANGE = 106;

  @Override
  public boolean checkRange(float value) {
    boolean isCritical = false;
    if (value >= 99.5 && value < 106) {
      isCritical = true;
    }
    return isCritical;
  }

  @Override
  public String alertMessage(float value) {
    String alertMessage = "";
    if (value > 99.5 && value < 104) {
      alertMessage = "Critical Temperature value: Fever";
    } else if (value >= 104 && value < 106) {
      alertMessage = "Critical Temperature value: Hyperpyrexia";
    }
    return alertMessage;
  }
}
