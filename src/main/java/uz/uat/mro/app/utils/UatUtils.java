package uz.uat.mro.app.utils;

import com.vaadin.flow.server.VaadinSession;

public class UatUtils {
        public static Object getAttribute(String name) {
        return VaadinSession.getCurrent().getAttribute(name);
    }

    public static void setAttribute(String key, Object value) {
        VaadinSession.getCurrent().setAttribute(key, value);
    }

}
