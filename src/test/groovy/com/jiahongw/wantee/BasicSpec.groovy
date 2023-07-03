package com.jiahongw.wantee

import org.mybatis.spring.annotation.MapperScan
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification


@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
class BasicSpec extends Specification {

    protected final Logger logger = LoggerFactory.getLogger(getClass())

}
