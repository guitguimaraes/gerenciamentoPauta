# Gradle Configuration for Nexo Services with querydsl

This folder contains the following gradle configurations:

* base gradle configuration for nexo services
* [checkstyle](http://checkstyle.sourceforge.net/)
* [codenarc](http://codenarc.sourceforge.net/)
* [pmd](https://pmd.github.io/)
* [Error Prone](http://errorprone.info/)
* [NullAway](https://github.com/uber/NullAway)

All files under this folder are standard Nexo settings. And thus you should not override any of these settings.

Instead, if you want to get the latest configuration, please copy configurations from [https://bitbucket.es.ad.adp.com/projects/NEXO/repos/nexo-projects-config/browse/service-conf/config](https://bitbucket.es.ad.adp.com/projects/NEXO/repos/nexo-projects-config/browse/service-conf/config). If you want to make any change, please send PR to [https://bitbucket.es.ad.adp.com/projects/NEXO/repos/nexo-projects-config/browse](https://bitbucket.es.ad.adp.com/projects/NEXO/repos/nexo-projects-config/browse).

If com.adp.nexo.commons.annotation.ExternalInitialized is not in your classpath, please add the following implementation to your source.

``` Java
package com.adp.nexo.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark the target class as external initialized.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExternalInitialized {
}
```
