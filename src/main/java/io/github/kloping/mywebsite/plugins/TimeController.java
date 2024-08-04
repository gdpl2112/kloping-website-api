package io.github.kloping.mywebsite.plugins;

import io.github.kloping.MySpringTool.annotations.Controller;
import io.github.kloping.MySpringTool.annotations.Schedule;

/**
 * @author github.kloping
 */
@Controller
public class TimeController {

    @Schedule("00:00:00")
    public void m0() {
    }
}
