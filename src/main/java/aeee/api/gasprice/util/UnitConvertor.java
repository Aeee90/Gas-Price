package aeee.api.gasprice.util;

import aeee.api.gasprice.define.Unit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitConvertor {

    private static Pattern HexReg = Pattern.compile("^0x([0-9a-f]+)");

    private static String getHexStr(String hex){
        Matcher matcher = HexReg.matcher(hex);
        return matcher.find()? matcher.group(1) : "0";
    }

    public static BigDecimal converthexStrToDecimalBigDecimal(String hex){ return new BigDecimal(new BigInteger(getHexStr(hex), 16)); }
    public static Long converthexStrToDecimalLong(String hex){ return Long.valueOf(getHexStr(hex), 16); }

    public static BigDecimal convertUnit(BigDecimal value, Unit from , Unit to){ return value.multiply(from.getUnit()).divide(to.getUnit()); }
    public static BigDecimal convertUnitWithRoundDown(BigDecimal value, Unit from , Unit to, int point){ return value.multiply(from.getUnit()).divide(to.getUnit(), point, RoundingMode.DOWN); }
    public static BigDecimal convertUnitWithRoundUp(BigDecimal value, Unit from , Unit to, int point){ return value.multiply(from.getUnit()).divide(to.getUnit(), point, RoundingMode.UP); }
    public static BigDecimal convertUnitWithRoundHalf(BigDecimal value, Unit from , Unit to, int point){ return value.multiply(from.getUnit()).divide(to.getUnit(), point, RoundingMode.HALF_UP); }

}
