package validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Validator<O> {
    O getValue(BufferedReader reader, Integer index) throws IOException;

    String getFlag(BufferedReader reader, List<O> objects) throws IOException;

    boolean verifyAndBreak(List<O> objects, O object);

    boolean verifyAndContinue(List<O> objects, O object);
}
