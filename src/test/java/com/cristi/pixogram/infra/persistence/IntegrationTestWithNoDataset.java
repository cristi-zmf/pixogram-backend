package com.cristi.pixogram.infra.persistence;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ImportAutoConfiguration({RibbonAutoConfiguration.class})
public abstract class IntegrationTestWithNoDataset {
}
