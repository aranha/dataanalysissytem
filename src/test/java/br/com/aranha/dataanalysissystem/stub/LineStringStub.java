package br.com.aranha.dataanalysissystem.stub;

import java.util.Arrays;
import java.util.List;

public class LineStringStub {
    public static List<String> createLinesListString() {

        String line1 = "001ç1234567891234çDiegoç50000";
        String line2 = "001ç3245678865434çRenatoç40000.99";
        String line3 = "002ç2345675434544345çJosedaSilvaçRural";
        String line4 = "002ç2345675433444345çEduardoPereiraçRural";
        String line5 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        String line6 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";

        return Arrays.asList(line1, line2, line3, line4, line5, line6);
    }
}
