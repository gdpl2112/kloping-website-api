package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.PathValue;
import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import org.jsoup.nodes.Document;

/**
 * @author github.kloping
 */
@HttpClient("https://so.iqiyi.com/")
public interface Iqiyi {
    /**
     * search
     * https://so.iqiyi.com/so/q_%E6%96%97%E7%BD%97%E5%A4%A7%E9%99%86_ctg__t_0_page_1_p_1_qc_0_rd__site_iqiyi_m_1_bitrate__af_0
     * @param name
     * @return
     */
    @GetPath("so/q_{name}_ctg__t_0_page_1_p_1_qc_0_rd__site_iqiyi_m_1_bitrate__af_0")
    Document so(@PathValue("name") String name);
}
