package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    	System.out.println(extract("3[xyz]4[xy]z"));	//xyzxyzxyzxyxyxyxyz
	System.out.println(extract("2[3[x]y]"));	//xxxyxxxy
    	System.out.println(extract("2[3[x2[z]]y]"));	//xzzxzzxzzyxzzxzzxzzy
	System.out.println(extract("12[A]b"));		//AAAAAAAAAAAAb

	System.out.println(validate("3[xyz]4[xy]z"));	//true
	System.out.println(validate("3[xw]_2[q]"));	//false т.к. присутствует недопустимый символ
	System.out.println(validate("3[2]4[xy]z"));	//false т.к. в скобках только число
	System.out.println(validate("3[xy4][xy]z"));	//false т.к. перед скобками нет числа
	System.out.println(validate("3[xyz]4[]z"));	//false т.к. в скобках ничего нет
    }

    //Основное задание
    public static String extract(String toExtract){
    	String result = toExtract;

	Pattern pattern = Pattern.compile("\\d+\\[[a-zA-Z]+\\]");
	Matcher matcher = pattern.matcher(result);

	while (matcher.find()) {
	    int start = matcher.start();
	    int end = matcher.end();
	    String parsed_num = toExtract.substring(start,toExtract.indexOf("[",start));
	    String replacement = toExtract.substring(start + 1 + parsed_num.length(), end - 1);
	    int count = Integer.parseInt(parsed_num);
	    result = result.replace(toExtract.substring(start, end), replacement.repeat(count));
	}

	if (result.substring(0,1).matches("\\d")){
	    result = extract(result);
	}

	return result;
    }

    //Дополнительное задание
    public static boolean validate(String toValidate){
    	return toValidate.matches("[a-zA-Z0-9\\[\\]]+") && !toValidate.matches(".+\\d\\].+")
		&& !toValidate.matches(".+\\][a-zA-Z]*\\[.+") && !toValidate.matches(".+\\[\\].+");
	}
}
