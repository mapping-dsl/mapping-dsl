package io.mappingdsl.core.tests;

import io.mappingdsl.core.MappingDsl;
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

class UniNestedMappingTest {

    @Test
    void shouldMapNestedFieldsViaFlatConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .produce(StreetEntityMappingDsl.$this.houseNumber.number)
                .to(StreetDtoMappingDsl.$this.houseNumber.number)
                .produce(StreetEntityMappingDsl.$this.houseNumber.suffix)
                .to(StreetDtoMappingDsl.$this.houseNumber.suffix)
                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.longitude)
                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.longitude)
                .produce(StreetEntityMappingDsl.$this.houseNumber.geolocation.latitude)
                .to(StreetDtoMappingDsl.$this.houseNumber.geolocation.latitude)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .produce(StreetEntityMappingDsl.$this.houseNumber)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.houseNumber)

                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .produce(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaMethodsAndDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .produce(StreetEntityMappingDsl.$this.getHouseNumber)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.setHouseNumber)

                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .produce(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaPropertiesAndDedicatedConfig() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .produce(StreetEntityMappingDsl.$this.houseNumberProperty)
                .usingMapping()
                .to(StreetDtoMappingDsl.$this.houseNumberProperty)

                .uniMapping()
                .from(HouseNumberEntity.class).to(HouseNumberDto.class)
                .produce(HouseNumberEntityMappingDsl.$this.number)
                .to(HouseNumberDtoMappingDsl.$this.number)
                .produce(HouseNumberEntityMappingDsl.$this.suffix)
                .to(HouseNumberDtoMappingDsl.$this.suffix)
                .produce(HouseNumberEntityMappingDsl.$this.geolocation)
                .asIs()
                .to(HouseNumberDtoMappingDsl.$this.geolocation)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    @Test
    void shouldMapNestedFieldsViaDedicatedConverter() {
        MappingDsl mappingDsl = new MappingDslBuilder()
                .uniMapping()
                .from(StreetEntity.class).to(StreetDto.class)
                .produce(StreetEntityMappingDsl.$this.name)
                .to(StreetDtoMappingDsl.$this.name)
                .produce(StreetEntityMappingDsl.$this.houseNumber)
                .usingConverter(this::convertHouseNumber)
                .to(StreetDtoMappingDsl.$this.houseNumber)
                .build();

        StreetEntity streetEntity = new StreetEntity();
        streetEntity.setName("Baker Street");
        streetEntity.setHouseNumber(new HouseNumberEntity(221, "B", new Geolocation(51.523772, -0.158539)));

        StreetDto streetDto = mappingDsl.map(streetEntity, StreetDto.class);

        assertThat(streetDto.getName()).isEqualTo("Baker Street");
        assertThat(streetDto.getHouseNumber().getNumber()).isEqualTo(221);
        assertThat(streetDto.getHouseNumber().getSuffix()).isEqualTo("B");
        assertThat(streetDto.getHouseNumber().getGeolocation().getLatitude()).isEqualTo(51.523772);
        assertThat(streetDto.getHouseNumber().getGeolocation().getLongitude()).isEqualTo(-0.158539);
    }

    private HouseNumberDto convertHouseNumber(HouseNumberEntity houseNumberEntity) {
        HouseNumberDto houseNumberDto = new HouseNumberDto();
        houseNumberDto.setNumber(houseNumberEntity.getNumber());
        houseNumberDto.setSuffix(houseNumberEntity.getSuffix());
        houseNumberDto.setGeolocation(houseNumberEntity.getGeolocation());
        return houseNumberDto;
    }

}
