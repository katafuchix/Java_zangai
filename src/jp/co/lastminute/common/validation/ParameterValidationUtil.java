package jp.co.lastminute.common.validation;

//jp.co.lastMinute.common.validation.ParameterValidationUtil
/**
 * @author Ricardo Leon
 * Common validations for the forms
 */
public class ParameterValidationUtil {
	/**
	 * This return value indicates that the parameter approved all the requirements
	 */
	public final static int PARAMETER_VALID = 0;

	/**
	 * This means that the parameter was required but is null or empty. Spaces are eliminated before checking
	 */
	public final static int PARAMETER_NOT_FOUND = 1;

	/**
	 * This means that the parameter should be numeric, but a character different than [0-9] was found
	 */
	public final static int PARAMETER_NOT_NUMERIC = 2;

	/**
	 * This means that the parameter should be only Ascii Characters, but a Japanese character was found
	 */
	public final static int PARAMETER_IS_NOT_ASCII_ONLY = 3;

	/**
	 * This means that the parameter should be only Japanese Characters, but an ASCII character was found
	 */
	public final static int PARAMETER_IS_NOT_JAPANESE_ONLY = 4;

	/**
	 * This means that the parameter exceeds the Max Value specified is below the parameter value
	 */
	public final static int PARAMETER_IS_TOO_BIG = 5;

	/**
	 * This means that the parameter is below the Min Value specified. For example: The min value is 1 but the parameter value is 0
	 */
	public final static int PARAMETER_IS_TOO_LITTLE = 6;

	/**
	 * This represents the error code when a part of the date (year, month, or day) is too little (below 1)
	 */
	public final static int PARAMETER_DATE_TOO_LITTLE = 7;

	/**
	 * This represents the error code when a part of the date (year, month, or day) is too big (for example, a day that is 32, a month that is 13, or a year that is 10000
	 */
	public final static int PARAMETER_DATE_TOO_BIG = 8;

	/**
	 * This represents the error code when the date is within the normal ranges (0 < day < 32; 0 < month < 13; 0 < year < 10000), but the date is not valid (for example, February 31)
	 */
	public final static int PARAMETER_DATE_NOT_POSSIBLE = 9;

	/**
	 * This represents the error code when the Day Parameter of a Function is missing
	 */
	public final static int PARAMETER_DATE_MISSING_DAY = 10;

	/**
	 * This represents the error code when the Month Parameter of a Function is missing
	 */
	public final static int PARAMETER_DATE_MISSING_MONTH = 11;

	/**
	 * This represents the error code when the Year Parameter of a Function is missing
	 */
	public final static int PARAMETER_DATE_MISSING_YEAR = 12;

	// Error Strings for the Resource File
	/**
	 * This means that the parameter was required but is null or empty. Spaces are eliminated before checking
	 */
	public final static String PARAMETER_NOT_FOUND_STRING = "required";

	/**
	 * This means that the parameter should be numeric, but a character different than [0-9] was found
	 */
	public final static String PARAMETER_NOT_NUMERIC_STRING = "numeric";

	/**
	 * This means that the parameter should be only Ascii Characters, but a Japanese character was found
	 */
	public final static String PARAMETER_IS_NOT_ASCII_ONLY_STRING = "ascii";

	/**
	 * This means that the parameter should be only Japanese Characters, but an ASCII character was found
	 */
	public final static String PARAMETER_IS_NOT_JAPANESE_ONLY_STRING = "japanese";

	/**
	 * This means that the parameter exceeds the Max Value specified is below the parameter value
	 */
	public final static String PARAMETER_IS_TOO_BIG_STRING = "max";

	/**
	 * This means that the parameter is below the Min Value specified. For example: The min value is 1 but the parameter value is 0
	 */
	public final static String PARAMETER_IS_TOO_LITTLE_STRING = "min";

	/**
	 * This represents the error code when a part of the date (year, month, or day) is too little (below 1)
	 */
	public final static String PARAMETER_DATE_TOO_LITTLE_STRING = "date.little";

	/**
	 * This represents the error code when a part of the date (year, month, or day) is too big (for example, a day that is 32, a month that is 13, or a year that is 10000
	 */
	public final static String PARAMETER_DATE_TOO_BIG_STRING = "date.big";

	/**
	 * This represents the error code when the date is within the normal ranges (0 < day < 32; 0 < month < 13; 0 < year < 10000), but the date is not valid (for example, February 31)
	 */
	public final static String PARAMETER_DATE_NOT_POSSIBLE_STRING = "date.outrange";

	/**
	 * This represents the error code when the Day Parameter of a Function is missing
	 */
	public final static String PARAMETER_DATE_MISSING_DAY_STRING = "date.day.missing";

	/**
	 * This represents the error code when the Month Parameter of a Function is missing
	 */
	public final static String PARAMETER_DATE_MISSING_MONTH_STRING = "date.month.missing";

	/**
	 * This represents the error code when the Year Parameter of a Function is missing
	 */
	public final static String PARAMETER_DATE_MISSING_YEAR_STRING = "date.year.missing";

	/**
	 * Allowed digits (charcters) for Number Validation
	 */
	public final static String VALID_DIGITS = "0123456789";

	/**
	 * Converts an error value to the error string for the Resource File
	 */
	public static String getErrorString(int value) {
		// Please note that the following switch does not include "break", this can cause problems if a modification is done
		switch (value) {
			case PARAMETER_NOT_FOUND:
				return PARAMETER_NOT_FOUND_STRING;
			case PARAMETER_NOT_NUMERIC:
				return PARAMETER_NOT_NUMERIC_STRING;
			case PARAMETER_IS_NOT_ASCII_ONLY:
				return PARAMETER_IS_NOT_ASCII_ONLY_STRING;
			case PARAMETER_IS_NOT_JAPANESE_ONLY:
				return PARAMETER_IS_NOT_JAPANESE_ONLY_STRING;
			case PARAMETER_IS_TOO_BIG:
				return PARAMETER_IS_TOO_BIG_STRING;
			case PARAMETER_IS_TOO_LITTLE:
				return PARAMETER_IS_TOO_LITTLE_STRING;
			case PARAMETER_DATE_TOO_LITTLE:
				return PARAMETER_DATE_TOO_LITTLE_STRING;
			case PARAMETER_DATE_TOO_BIG:
				return PARAMETER_DATE_TOO_BIG_STRING;
			case PARAMETER_DATE_NOT_POSSIBLE:
				return PARAMETER_DATE_NOT_POSSIBLE_STRING;
			case PARAMETER_DATE_MISSING_DAY:
				return PARAMETER_DATE_MISSING_DAY_STRING;
			case PARAMETER_DATE_MISSING_MONTH:
				return PARAMETER_DATE_MISSING_MONTH_STRING;
			case PARAMETER_DATE_MISSING_YEAR:
				return PARAMETER_DATE_MISSING_YEAR_STRING;
			case PARAMETER_VALID:
				return null;
		}
		throw new java.lang.IllegalArgumentException("The given value is not within the expected values");
	}

	/**
	 * Checks if the Value is Numeric Only
	 * @return True if all the characters of the String is in the VALID_DIGITS list
	 */
	public static boolean isNumericOnly(String value) {
		for (int i = 0; i < value.length(); i += 1) {
			if (VALID_DIGITS.indexOf(value.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks all characters
	 * @return True if all the characters of the String is in the ASCII List
	 */
	public static boolean isAsciiOnly(String value) {
		for (int i = 0; i < value.length(); i += 1) {
			if (value.charAt(i) > 127) { // @todo Test this part!!!
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks all characters
	 * @return True if all the characters of the String are Japanese Only
	 */
	public static boolean isJapaneseOnly(String value) {
		for (int i = 0; i < value.length(); i += 1) {
			if (value.charAt(i) < 127) { // @todo Test this part!!!
				return false;
			}
		}
		return true;
	}

	//co.lastMinute.common.validation.ParameterValidationUtil.validateParameter(loggerName, parameterValue, isRequired, isNumeric, isAsciiOnly, isJapaneseOnly, maxValue, minValue);
	/**
	 * @param loggerName name of the Logger for Debugging
	 * @param minValue			Minimun value, this is checked if this value is not Null, the parameter is not Null, and the parameter should be Numeric Only
	 * @param isNumeric			boolean value for verifying if the parameter Value is inside
	 * @param isAsciiOnly		boolean value for verifying or not that the parameter contains Ascii characters only
	 * @param isJapaneseOnly	boolean value for verifying or not that the parameters contains Japanese Characters only
	 * @param maxValue			Integer value that determines the maximun value of the parameter (inlcusive)
	 * @param minValue			Integer value that determines tha minumun value of the parameter (inclusive)
	 * Validates in the following order: required, numeric, Ascii only, is Japanese only, max. value, min. value.
	 * @return error Code of the first error found
	 */
	public static int validateParameter(String loggerName, String parameterValue, boolean isRequired, boolean isNumeric, boolean isAsciiOnly, boolean isJapaneseOnly, Integer maxValue, Integer minValue) {
		if (isRequired && parameterValue == null) {
			return PARAMETER_NOT_FOUND;
		}
		else if (parameterValue == null) { // if the parameter is null and is not required, then return Valid Parameter
			return PARAMETER_VALID;
		}
		
		parameterValue = parameterValue.trim(); // Trim the string to avoid problems because of the spaces (a String full of spaces should be considered empty)
		if (isRequired && parameterValue.length() == 0) {
			return PARAMETER_NOT_FOUND;
		}
		else if (parameterValue.length() == 0) {// if the parameter is empty and is not required, then return Valid Parameter
			return PARAMETER_VALID;
		}

		if (isNumeric && !isNumericOnly(parameterValue)) {
			return PARAMETER_NOT_NUMERIC;
		}

		if (isAsciiOnly && !isAsciiOnly(parameterValue)) {
			return PARAMETER_IS_NOT_ASCII_ONLY;
		}

		if (isJapaneseOnly && !isJapaneseOnly(parameterValue)) {
			return PARAMETER_IS_NOT_JAPANESE_ONLY;
		}

		if (isNumeric && parameterValue != null) {
			Integer integerParameterValue = new Integer(parameterValue);
			if (maxValue != null && integerParameterValue.intValue() > maxValue.intValue()) {
				return PARAMETER_IS_TOO_BIG;
			}
			if (minValue != null && integerParameterValue.intValue() < minValue.intValue()) {
				return PARAMETER_IS_TOO_LITTLE;
			}
		}
		return PARAMETER_VALID;
	}
        public static int validateParameter( String parameterValue, boolean isRequired, boolean isNumeric, boolean isAsciiOnly, boolean isJapaneseOnly, Integer maxValue, Integer minValue) {
		if (isRequired && parameterValue == null) {
			return PARAMETER_NOT_FOUND;
		}
		else if (parameterValue == null) { // if the parameter is null and is not required, then return Valid Parameter
			return PARAMETER_VALID;
		}
		
		parameterValue = parameterValue.trim(); // Trim the string to avoid problems because of the spaces (a String full of spaces should be considered empty)
		if (isRequired && parameterValue.length() == 0) {
			return PARAMETER_NOT_FOUND;
		}
		else if (parameterValue.length() == 0) {// if the parameter is empty and is not required, then return Valid Parameter
			return PARAMETER_VALID;
		}

		if (isNumeric && !isNumericOnly(parameterValue)) {
			return PARAMETER_NOT_NUMERIC;
		}

		if (isAsciiOnly && !isAsciiOnly(parameterValue)) {
			return PARAMETER_IS_NOT_ASCII_ONLY;
		}

		if (isJapaneseOnly && !isJapaneseOnly(parameterValue)) {
			return PARAMETER_IS_NOT_JAPANESE_ONLY;
		}

		if (isNumeric && parameterValue != null) {
			Integer integerParameterValue = new Integer(parameterValue);
			if (maxValue != null && integerParameterValue.intValue() > maxValue.intValue()) {
				return PARAMETER_IS_TOO_BIG;
			}
			if (minValue != null && integerParameterValue.intValue() < minValue.intValue()) {
				return PARAMETER_IS_TOO_LITTLE;
			}
		}
		return PARAMETER_VALID;
	}
	
	/**
	 * Date validation, verifies that a Given Date is Valid
	 */
	public static int validateDateParameters(String logName, Integer day, Integer month, Integer year) {
		if (month == null && year == null && day == null) {
			return PARAMETER_VALID;
		}
		else if (month == null) {
			return PARAMETER_DATE_MISSING_MONTH;
		}
		else if (year == null) {
			return PARAMETER_DATE_MISSING_YEAR;
		}
		else if (day == null) {
			return PARAMETER_DATE_MISSING_DAY;
		}

		if (year.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (year.intValue() > 9999) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if (day.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (day.intValue() > 31) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if (month.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (month.intValue() > 12) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if ((month.intValue() == 4 || month.intValue() == 6 || month.intValue() == 9 || month.intValue() == 11) && (day.intValue() > 30)) {
			return PARAMETER_DATE_NOT_POSSIBLE;
		}

		if (month.intValue() == 02) {
			if (((year.intValue() % 400 != 0 && year.intValue() % 100 == 0) || year.intValue() % 4 != 0) && (day.intValue() < 1 || day.intValue() > 28)) {
				return PARAMETER_DATE_NOT_POSSIBLE;
			}
		    if ((year.intValue() % 400 == 0 || year.intValue() % 4 == 0) && (day.intValue() < 1 || day.intValue() > 29)) {
				return PARAMETER_DATE_NOT_POSSIBLE;
			}
		}
		return PARAMETER_VALID;
	}

	/**
	 * Date validation, verifies that a Given Date is Valid
	 * If the Month and year are null, then the parameter valid is returned as true
	 * @param logName the name (string) for getting the log
	 * @param month the Month of the date
	 * @param year the Year of the date
	 */
	public static int validateDateParameters(String logName, Integer month, Integer year) {
		if (month == null && year == null) {
			return PARAMETER_VALID;
		}
		else if (month == null) {
			return PARAMETER_DATE_MISSING_MONTH;
		}
		else if (year == null) {
			return PARAMETER_DATE_MISSING_YEAR;
		}

		if (year.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (year.intValue() > 9999) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if (month.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (month.intValue() > 12) {
			return PARAMETER_DATE_TOO_BIG;
		}
		return PARAMETER_VALID;
	}

	/**
	 * Date validation, verifies that a Given Date is Valid
	 */
	public static int validateDateParametersDayMonth(String logName, Integer day, Integer month) {
		if (month == null && day == null) {
			return PARAMETER_VALID;
		}
		else if (month == null) {
			return PARAMETER_DATE_MISSING_MONTH;
		}
		else if (day == null) {
			return PARAMETER_DATE_MISSING_DAY;
		}

		if (day.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (day.intValue() > 31) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if (month.intValue() < 1) {
			return PARAMETER_DATE_TOO_LITTLE;
		}
		else if (month.intValue() > 12) {
			return PARAMETER_DATE_TOO_BIG;
		}

		if ((month.intValue() == 4 || month.intValue() == 6 || month.intValue() == 9 || month.intValue() == 11) && (day.intValue() > 30)) {
			return PARAMETER_DATE_NOT_POSSIBLE;
		}

		if (month.intValue() == 2 && day.intValue() > 29) {
			return PARAMETER_DATE_NOT_POSSIBLE;
		}
		return PARAMETER_VALID;
	}

	
	public ParameterValidationUtil() {
	}
} // End of ParameterValidationUtil
