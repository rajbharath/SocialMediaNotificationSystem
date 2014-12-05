package test;

import java.util.ArrayList;
import java.util.List;

public class Properties {
    public static final String MARITAL_STATUS = "marital-status";
    public static final String MARITAL_STATUS_CODE = "status-code";
    public static final String PHONE_NUMBER = "phone-number";

    private static ArrayList<String> props = new ArrayList<String>();

    static{
        props.add(Properties.MARITAL_STATUS);
        props.add(Properties.MARITAL_STATUS_CODE);
        props.add(Properties.PHONE_NUMBER);
    }
    public static List<String> getAll() {
        return props;
    }
}
