package com.odinese.cerpdashboard.mapper;

import com.odinese.cerpdashboard.dtos.VendorDto;
import com.odinese.cerpdashboard.model.Vendor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-07T13:46:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class VendorMapperImpl implements VendorMapper {

    @Override
    public VendorDto toDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorDto.VendorDtoBuilder vendorDto = VendorDto.builder();

        vendorDto.id( vendor.getId() );
        vendorDto.name( vendor.getName() );

        return vendorDto.build();
    }

    @Override
    public List<VendorDto> toDtoList(List<Vendor> vendors) {
        if ( vendors == null ) {
            return null;
        }

        List<VendorDto> list = new ArrayList<VendorDto>( vendors.size() );
        for ( Vendor vendor : vendors ) {
            list.add( toDto( vendor ) );
        }

        return list;
    }
}
