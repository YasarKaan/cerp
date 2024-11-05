package com.odinese.cerpdashboard.mapper;

import com.odinese.cerpdashboard.dtos.AnsibleDirectoryDTO;
import com.odinese.cerpdashboard.dtos.MachineDto;
import com.odinese.cerpdashboard.dtos.VendorDto;
import com.odinese.cerpdashboard.model.AnsibleDirectory;
import com.odinese.cerpdashboard.model.Machine;
import com.odinese.cerpdashboard.model.MachineOvercloudAttributes;
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
public class MachineMapperImpl implements MachineMapper {

    @Override
    public MachineDto toDto(Machine machine) {
        if ( machine == null ) {
            return null;
        }

        MachineDto.MachineDtoBuilder machineDto = MachineDto.builder();

        machineDto.cpu( machineMachineOvercloudAttributesCpu( machine ) );
        machineDto.memory( machineMachineOvercloudAttributesMemory( machine ) );
        machineDto.disk( machineMachineOvercloudAttributesDisk( machine ) );
        machineDto.macAddress( machineMachineOvercloudAttributesMacAddress( machine ) );
        machineDto.hostname( machineMachineOvercloudAttributesHostname( machine ) );
        machineDto.ansibleDirectoryDTO( ansibleDirectoryToAnsibleDirectoryDTO( machineMachineOvercloudAttributesDirectory( machine ) ) );
        machineDto.id( machine.getId() );
        machineDto.ipAddress( machine.getIpAddress() );
        machineDto.vendor( vendorToVendorDto( machine.getVendor() ) );
        machineDto.createdAt( machine.getCreatedAt() );
        machineDto.username( machine.getUsername() );
        machineDto.password( machine.getPassword() );
        machineDto.location( machine.getLocation() );
        machineDto.overcloudEnable( machine.getOvercloudEnable() );

        return machineDto.build();
    }

    @Override
    public List<MachineDto> toDtoList(List<Machine> machines) {
        if ( machines == null ) {
            return null;
        }

        List<MachineDto> list = new ArrayList<MachineDto>( machines.size() );
        for ( Machine machine : machines ) {
            list.add( toDto( machine ) );
        }

        return list;
    }

    @Override
    public Machine toEntity(MachineDto machineDto) {
        if ( machineDto == null ) {
            return null;
        }

        Machine.MachineBuilder machine = Machine.builder();

        machine.machineOvercloudAttributes( machineDtoToMachineOvercloudAttributes( machineDto ) );
        machine.ipAddress( machineDto.getIpAddress() );
        machine.username( machineDto.getUsername() );
        machine.password( machineDto.getPassword() );
        machine.location( machineDto.getLocation() );
        machine.overcloudEnable( machineDto.getOvercloudEnable() );

        return machine.build();
    }

    private Integer machineMachineOvercloudAttributesCpu(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        Integer cpu = machineOvercloudAttributes.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu;
    }

    private Integer machineMachineOvercloudAttributesMemory(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        Integer memory = machineOvercloudAttributes.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory;
    }

    private Integer machineMachineOvercloudAttributesDisk(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        Integer disk = machineOvercloudAttributes.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk;
    }

    private String machineMachineOvercloudAttributesMacAddress(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        String macAddress = machineOvercloudAttributes.getMacAddress();
        if ( macAddress == null ) {
            return null;
        }
        return macAddress;
    }

    private String machineMachineOvercloudAttributesHostname(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        String hostname = machineOvercloudAttributes.getHostname();
        if ( hostname == null ) {
            return null;
        }
        return hostname;
    }

    private AnsibleDirectory machineMachineOvercloudAttributesDirectory(Machine machine) {
        if ( machine == null ) {
            return null;
        }
        MachineOvercloudAttributes machineOvercloudAttributes = machine.getMachineOvercloudAttributes();
        if ( machineOvercloudAttributes == null ) {
            return null;
        }
        AnsibleDirectory directory = machineOvercloudAttributes.getDirectory();
        if ( directory == null ) {
            return null;
        }
        return directory;
    }

    protected AnsibleDirectoryDTO ansibleDirectoryToAnsibleDirectoryDTO(AnsibleDirectory ansibleDirectory) {
        if ( ansibleDirectory == null ) {
            return null;
        }

        AnsibleDirectoryDTO ansibleDirectoryDTO = new AnsibleDirectoryDTO();

        ansibleDirectoryDTO.setId( ansibleDirectory.getId() );
        ansibleDirectoryDTO.setAuthType( ansibleDirectory.getAuthType() );
        ansibleDirectoryDTO.setPassword( ansibleDirectory.getPassword() );
        ansibleDirectoryDTO.setAuthURL( ansibleDirectory.getAuthURL() );
        ansibleDirectoryDTO.setUsername( ansibleDirectory.getUsername() );
        ansibleDirectoryDTO.setProjectName( ansibleDirectory.getProjectName() );
        ansibleDirectoryDTO.setDirectory( ansibleDirectory.getDirectory() );

        return ansibleDirectoryDTO;
    }

    protected VendorDto vendorToVendorDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorDto.VendorDtoBuilder vendorDto = VendorDto.builder();

        vendorDto.id( vendor.getId() );
        vendorDto.name( vendor.getName() );

        return vendorDto.build();
    }

    protected MachineOvercloudAttributes machineDtoToMachineOvercloudAttributes(MachineDto machineDto) {
        if ( machineDto == null ) {
            return null;
        }

        MachineOvercloudAttributes machineOvercloudAttributes = new MachineOvercloudAttributes();

        machineOvercloudAttributes.setMacAddress( machineDto.getMacAddress() );
        machineOvercloudAttributes.setHostname( machineDto.getHostname() );

        return machineOvercloudAttributes;
    }
}
