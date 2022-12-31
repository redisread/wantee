package com.jiahongw.wantee.util

import org.junit.platform.commons.util.StringUtils
import spock.lang.Specification


class JsoupUtilsSpec extends Specification {

    def "test getTitleByUrl"() {
        given:
        String url = "https://hugo.jiahongw.com/zh/posts/hugo/markdown-lint/"
        when:
        String title = JsoupUtils.getTitleByUrl(url)
        then:
        StringUtils.isNotBlank(title)
    }

}