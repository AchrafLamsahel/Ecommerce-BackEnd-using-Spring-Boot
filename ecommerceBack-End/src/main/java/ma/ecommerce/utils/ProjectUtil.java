package ma.ecommerce.utils;

import java.net.URI;

public class ProjectUtil {
    public static final String IMAGE_FORMAT="a[href$=.jpg], a[href$=.png], a[href$=.jpeg], a[href$=.webp]";
    public static final String PDF_FORMAT="a[href$=.pdf]";
    public static final String LOCALHOST= "localhost";
    public static String domainName(URI uri){
        if (uri.getHost().equals(LOCALHOST)) {
            return "http://" + uri.getHost() + (uri.getPort() != -1 ?":"+ uri.getPort() : "");
        }
        return "https://"+uri.getHost() + (uri.getPort() != -1 ?":"+ uri.getPort() : "");
    }

}
