

import Prj01Exeptions.ValueAboveMaxIneger;
import Prj01Exeptions.ValueLessThanZero;

public class LightBulb {
    private int lightPower;

    public LightBulb(int lightPower) throws ValueLessThanZero, ValueAboveMaxIneger {

        if (lightPower > 0) {
            if (lightPower < Integer.MAX_VALUE) {
                this.lightPower = lightPower;
            } else {
                throw new ValueAboveMaxIneger("Value of illuminance is too large.\nPlease enter less value.");
            }
        } else {
            throw new ValueLessThanZero("Value of illuminance is less than 0.\nValue you entered is " + lightPower + "\nPlease enter value above 0.");
        }
    }

    public int getLightPower() {
        return lightPower;
    }
}
