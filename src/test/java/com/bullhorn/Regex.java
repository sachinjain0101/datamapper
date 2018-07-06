package com.bullhorn;

import com.bullhorn.utils.FuncEnum;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(String[] args)
    {
        String stringToSearch = "SACHIN#100001#";

        Pattern p = Pattern.compile("(#.+#)");   // the pattern to search for
        Matcher m = p.matcher(stringToSearch);

        // if we find a match, get the group
        if (m.find())
        {
            // we're only looking for one group, so get it
            String theGroup = m.group(1);

            // print the group out for verification
            System.out.format("'%s'\n", theGroup);
        }

        DateTime _startDate = new DateTime(Long.valueOf("1487068914153"));

        System.out.println(_startDate.toDateTime().toString());
        System.out.println(_startDate.toDateTime(DateTimeZone.UTC).toString());

        System.out.println(FuncEnum.toList());


    }
}
