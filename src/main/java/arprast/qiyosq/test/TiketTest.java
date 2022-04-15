package arprast.qiyosq.test;

import org.junit.platform.commons.util.StringUtils;

public class TiketTest {
    public static void main(String[] strings) {
         binaryGap();
    }

    private static void binaryGap() {
        String split = "100001100";
        System.out.println(split.indexOf('0',8));

        String[] sas = split.split("1");
        for (int a = 0; a < sas.length; a++) {
            if (StringUtils.isBlank(sas[a])) {
                continue;
            }
            System.out.println(sas[a]);
        }
        System.out.println("Test");
    }

    private void OddOccurrencesInArray(){

    }
}
