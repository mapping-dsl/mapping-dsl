package io.mappingdsl.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappingContext<SRC_ROOT, TRG_ROOT> {

    @Getter
    @Delegate
    private MappingConfiguration configuration;

    @Getter
    private MappingKey<SRC_ROOT, TRG_ROOT> mappingKey;

    @Getter
    private MappingRules mappingRules;

    public void addMappingRule(MappingRule<SRC_ROOT, TRG_ROOT> mappingRule) {
        this.mappingRules.addMappingRule(this.mappingKey, mappingRule);
    }

    public MappingContext<SRC_ROOT, TRG_ROOT> withConfiguration(MappingConfiguration configuration) {
        return MappingContext.<SRC_ROOT, TRG_ROOT>builder()
                .configuration(configuration)
                .mappingKey(this.mappingKey)
                .mappingRules(this.mappingRules).build();
    }

    public <NEW_SRC_ROOT, NEW_TRG_ROOT> MappingContext<NEW_SRC_ROOT, NEW_TRG_ROOT> withMappingKey(
            MappingKey<NEW_SRC_ROOT, NEW_TRG_ROOT> mappingKey) {

        return MappingContext.<NEW_SRC_ROOT, NEW_TRG_ROOT>builder()
                .configuration(this.configuration)
                .mappingKey(mappingKey)
                .mappingRules(mappingRules).build();
    }

    public MappingContext<SRC_ROOT, TRG_ROOT> withMappingRules(MappingRules mappingRules) {
        return MappingContext.<SRC_ROOT, TRG_ROOT>builder()
                .configuration(this.configuration)
                .mappingKey(this.mappingKey)
                .mappingRules(mappingRules).build();
    }

}
