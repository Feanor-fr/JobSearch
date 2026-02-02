package kg.attractor.job_search.utils;

import jakarta.servlet.http.HttpServletRequest;

public class Utilities {

    private Utilities() {}

    public static String getSiteUrl(HttpServletRequest request){
        String siteUrl = request.getRequestURL().toString();

        return siteUrl.replace(request.getServletPath(), "");
    }
}
