package com.jiahongw.wantee

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification


@SpringBootTest
@DirtiesContext
class BasicSpec extends Specification {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
