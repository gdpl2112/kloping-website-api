package io.github.kloping.mywebsite.plugins;

import io.github.kloping.MySpringTool.annotations.Controller;
import io.github.kloping.MySpringTool.annotations.Schedule;
import io.github.kloping.mywebsite.controller.ApiSearchController;

/**
 * @author github.kloping
 */
@Controller
public class TimeController {
    @Schedule("00:00:00")
    public void m0() {
        ApiSearchController.SONGS_HASH_MAP.clear();
    }
}
