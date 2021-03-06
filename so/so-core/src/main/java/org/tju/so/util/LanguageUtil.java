package org.tju.so.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Utility for nature language processing
 * 
 * @author Tianyi HE <hty0807@gmail.com>
 */
public class LanguageUtil {

    private static HanyuPinyinOutputFormat pinyinOutputFormat;

    static {
        pinyinOutputFormat = new HanyuPinyinOutputFormat();
        pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * Determine whether input contains any unicode character
     * 
     * @param input
     * @return
     */
    public static boolean hasUnicode(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isUnicode(c))
                return true;
        }
        return false;
    }

    /**
     * Determine whether a character is unicode
     * 
     * @param c
     * @return
     */
    public static boolean isUnicode(char c) {
        if (c > 0xFF)
            return true;
        return false;
    }

    /**
     * Convert chinese characters to HanyuPinyin sequence. Non-chinese
     * characters will remain its position.
     * 
     * @param input
     * @return
     */
    public static String chinese2Pinyin(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isUnicode(c)) {
                String[] alternates;
                try {
                    alternates = PinyinHelper.toHanyuPinyinStringArray(c,
                            pinyinOutputFormat);
                    if (alternates != null && alternates.length > 0)
                        output += alternates[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {}
            } else {
                output += c;
            }
        }
        return output;
    }

}
