package com.cristi.mentool.mentool.infra.configurations;

import org.springframework.context.annotation.Profile;

@Profile("ENABLE-H2")
public @interface H2Profile {
}
