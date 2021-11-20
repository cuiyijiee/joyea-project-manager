package me.cuiyijie.joyea.util;

/**
 * @Author: yjcui3
 * @Date: 2021/11/19 15:40
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String regEx_html = "<[^>]+>";
    private static final String regEx_space = "\\s*|\t|\r|\n";
    private static final String regEx_other = "&nbsp;|&emsp;";

    public HtmlUtil() {
    }

    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        Pattern p_html = Pattern.compile("<[^>]+>", 2);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        Pattern p_space = Pattern.compile("\\s*|\t|\r|\n", 2);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        Pattern p_other = Pattern.compile("&nbsp;|&emsp;", 2);
        Matcher m_other = p_other.matcher(htmlStr);
        htmlStr = m_other.replaceAll("");
        return htmlStr.trim();
    }

    public static String delScriptTag(String htmlStr) {
        Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        return htmlStr.trim();
    }

    public static String getTextFromHtml(String htmlStr) {
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr;
    }

    public static String getMarksFromHtml(String htmlStr) {
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&ldquo;", "“").replaceAll("&rdquo;", "”");
        return htmlStr;
    }

    public static void main(String[] args) {
        String str = "<p style='text-align:center;'> &nbsp;整&nbsp;治&emsp;“四风”   &emsp;清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></p>";
        System.out.println(str);
        System.out.println(delHTMLTag(str));
    }
}
