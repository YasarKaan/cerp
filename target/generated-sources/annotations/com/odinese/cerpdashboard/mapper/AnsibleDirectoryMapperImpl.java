package com.odinese.cerpdashboard.mapper;

import com.odinese.cerpdashboard.dtos.AnsibleDirectoryDTO;
import com.odinese.cerpdashboard.model.AnsibleDirectory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-07T13:46:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class AnsibleDirectoryMapperImpl implements AnsibleDirectoryMapper {

    @Override
    public AnsibleDirectoryDTO toDto(AnsibleDirectory ansibleDirectory) {
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
}
