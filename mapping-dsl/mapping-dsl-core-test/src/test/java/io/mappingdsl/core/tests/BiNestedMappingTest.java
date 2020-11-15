package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
import io.mappingdsl.core.builder.BiConverter;
import io.mappingdsl.core.builder.MappingDslBuilder;
import io.mappingdsl.core.tests.fixtures.Geolocation;
import io.mappingdsl.core.tests.fixtures.HouseNumberDto;
import io.mappingdsl.core.tests.fixtures.HouseNumberDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntity;
import io.mappingdsl.core.tests.fixtures.HouseNumberEntityMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetDto;
import io.mappingdsl.core.tests.fixtures.StreetDtoMappingDsl;
import io.mappingdsl.core.tests.fixtures.StreetEntity;
import io.mappingdsl.core.tests.fixtures.StreetEntityMappingDsl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BiNestedMappingTest {

    @Test
    void shouldMapNestedFieldsViaFlatConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber.number)
                .with(StreetDtoMappingDsl.$this.houseNumber.number)
                .bind(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .with(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .with(StreetDtoMappingDsl.$this.houseNumber)

                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .bind(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .with(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConverter() {
        BiConverter<HouseNumberEntity, HouseNumberDto> converter = new BiConverter<HouseNumberEntity, HouseNumberDto>() {

            @Override
            public HouseNumberDto convertForward(HouseNumberEntity source) {
                return convertHouseNumberEntity(source);
            }

            @Override
            public HouseNumberEntity convertBackward(HouseNumberDto target) {
                return convertHouseNumberDto(target);
            }

        };

        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber)
                .usingConverter(converter)
                .with(StreetDtoMappingDsl.$this.houseNumber)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConverters() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber)
                .usingConverters(this::convertHouseNumberEntity, this::convertHouseNumberDto)
                .with(StreetDtoMappingDsl.$this.houseNumber)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetEntity.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaFlatConfigWithDifferentDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .consume(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .from(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B"));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isNull();

        // restore missing value to get a full object for the next mapping iteration
        streetDto.getHouseNumber().setSuffix("B");

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isNull();
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConfigWithProducerDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.houseNumber)

                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .bind(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .with(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber()).isNull();
    }

    @Test
    void shouldMapNestedFieldsAsIsWithProducerDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .bind(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .with(StreetDtoMappingDsl.$this.houseNumber)

                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .supply(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetEntity.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetEntity.getHouseNumber().getGeolocation()).isNull();
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConverterWithProducerDirectionality() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .biMapping()
                .between(StreetEntity.class).and(StreetDto.class)
                .bind(StreetEntityMappingDsl.$this.name)
                .with(StreetDtoMappingDsl.$this.name)
                .supply(StreetEntityMappingDsl.$this.houseNumber)
                .usingConverter(this::convertHouseNumberEntity)
                .to(StreetDtoMappingDsl.$this.houseNumber)

                .biMapping()
                .between(HouseNumberEntity.class).and(HouseNumberDto.class)
                .bind(HouseNumberEntityMappingDsl.$this.number)
                .with(HouseNumberDtoMappingDsl.$this.number)
                .bind(HouseNumberEntityMappingDsl.$this.suffix)
                .with(HouseNumberDtoMappingDsl.$this.suffix)
                .bind(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .with(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        // forward mapping
        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);

        // backward mapping
        streetEntity = mappingDsl.map(streetDto, StreetEntity.class);

        assertThat(streetEntity.getName()).isEqualTo("Baker Street");
        assertThat(streetEntity.getHouseNumber()).isNull();
    }

    private HouseNumberDto convertHouseNumberEntity(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

    private HouseNumberEntity convertHouseNumberDto(HouseNumberDto houseNumberDto) {
        HouseNumberEntity houseNumberEntity = new HouseNumberEntity();
        houseNumberEntity.setNumber(houseNumberDto.getNumber());
        houseNumberEntity.setSuffix(houseNumberDto.getSuffix());
        houseNumberEntity.setGeolocation(houseNumberDto.getGeolocation());
        return houseNumberEntity;
    }

}
