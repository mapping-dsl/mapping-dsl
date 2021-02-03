package io.mappingdsl.core.config;

import lombok.Getter;
import lombok.Setter;

public class MappingConfiguration {

    @Getter
    private final BeanFactoryConfiguration beanFactoryConfiguration = new BeanFactoryConfiguration();

    @Getter
    @Setter
    private NullHandlingMode nullHandlingMode = NullHandlingMode.PROCEED;

    @Getter
    @Setter
    private MissingMappingHandlingMode missingMappingHandlingMode = MissingMappingHandlingMode.TERMINATE;

    @Getter
    private final HintConfiguration hintConfiguration = new HintConfiguration();

}
