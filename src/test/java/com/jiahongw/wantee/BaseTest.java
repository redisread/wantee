package com.jiahongw.wantee;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = WanteeApplication.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
}
